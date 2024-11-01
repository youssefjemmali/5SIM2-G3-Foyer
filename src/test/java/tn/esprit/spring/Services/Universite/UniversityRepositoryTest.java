package tn.esprit.spring.Services.Universite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.DAO.Entities.Programme;
import tn.esprit.spring.DAO.Entities.TypeUniversite;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// @ActiveProfiles("test")  // Si commené utilise MySQL sinon H2
@DataJpaTest // Charge uniquement le contexte de persistence
@Transactional // Rétablit l'état après chaque test

class UniversiteRepositoryTest {

    @Autowired
    private UniversiteRepository universiteRepository;

    @BeforeEach
    void setUp() {
        // Si vous avez besoin de données initiales, vous pouvez les ajouter ici
        Universite universite = Universite.builder()
                .nomUniversite("Université de Test")
                .adresse("Adresse de Test")
                .build();
        universiteRepository.save(universite);
    }

    @Test
    void testFindByNomUniversite() {
        // Test de la méthode findByNomUniversite
        Universite universite = universiteRepository.findByNomUniversite("Université de Test");
        assertThat(universite).isNotNull();
        assertThat(universite.getNomUniversite()).isEqualTo("Université de Test");
    }

    @Test
    void testAddOrUpdate() {
        // Test de l'ajout ou de la mise à jour d'une université
        Universite universite = Universite.builder()
                .nomUniversite("Nouvelle Université")
                .adresse("Nouvelle Adresse")
                .build();

        Universite savedUniversite = universiteRepository.save(universite);
        assertThat(savedUniversite).isNotNull();
        assertThat(savedUniversite.getIdUniversite()).isGreaterThan(0); // L'ID doit être généré
    }

    @Test
    void testDeleteById() {
        // Test de la suppression par ID
        Universite universite = universiteRepository.findByNomUniversite("Université de Test");
        long id = universite.getIdUniversite();

        universiteRepository.deleteById(id);
        Optional<Universite> deletedUniversite = universiteRepository.findById(id);
        assertThat(deletedUniversite).isEmpty(); // L'université doit être supprimée
    }

    @Test
    void testFindUniversitiesByTypeAndProgram() {
        Programme programme1 = new Programme(1L, "Développement des Systèmes d'Information", 3, "Licence", null);
        Programme programme2 = new Programme(2L, "Systèmes Embarqué", 3, "Licence", null);
        Programme programme3 = new Programme(3L, "Réseau", 3, "Licence", null);

        List listProgs = new ArrayList<>();
        listProgs.add(programme1);
        listProgs.add(programme2);
        listProgs.add(programme3);
        Universite universite = new Universite(1L, "ISET RADES", "Rades, Ben Arous, Tunis, Tuunisie", TypeUniversite.PUBLIC, null, listProgs);
        List<Universite> universities = new ArrayList();
        universities.add(universite);

        List<Universite> result = universiteRepository.findByTypeUniversiteAndProgrammesNiveau(TypeUniversite.PUBLIC, "Licence");
        assertThat(result).isNotNull();
    }
}

// Utiliser les services à la place des repository