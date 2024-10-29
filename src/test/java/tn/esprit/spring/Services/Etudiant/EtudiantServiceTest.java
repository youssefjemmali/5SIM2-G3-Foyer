package tn.esprit.spring.Services.Etudiant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.Services.Etudiant.EtudiantService;
import org.springframework.transaction.annotation.Transactional;

public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testAddOrUpdateEtudiant() {
        // Créer un étudiant fictif
        Etudiant etudiant = Etudiant.builder()
                .nomEt("Dupont")
                .prenomEt("Jean")
                .cin(12345678L)
                .ecole("Ecole Centrale")
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .build();

        // Simuler le comportement du repository `save`
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Appeler la méthode d'insertion
        Etudiant savedEtudiant = etudiantService.addOrUpdate(etudiant);

        // Vérifier que l'étudiant est bien sauvegardé
        assertNotNull(savedEtudiant, "L'étudiant sauvegardé ne doit pas être nul");
        assertEquals(etudiant.getNomEt(), savedEtudiant.getNomEt(), "Le nom de l'étudiant doit correspondre");
    }
}

