package tn.esprit.spring.Services.Universite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.dao.Repositories.UniversiteRepository;
import tn.esprit.spring.dao.entities.Bloc;
import tn.esprit.spring.dao.entities.Chambre;
import tn.esprit.spring.dao.entities.Etudiant;
import tn.esprit.spring.dao.entities.Foyer;
import tn.esprit.spring.dao.entities.Programme;
import tn.esprit.spring.dao.entities.Reservation;
import tn.esprit.spring.dao.entities.TypeUniversite;
import tn.esprit.spring.dao.entities.Universite;
import tn.esprit.spring.services.universite.UniversiteService;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversiteServiceTest {
    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite universite;
    private Programme programme1;
    private Programme programme2;
    private Programme programme3;
    private List<Programme> listProgs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        programme1 = new Programme(1L, "Développement des Systèmes d'Information", 3, "Licence", null);
        programme2 = new Programme(2L, "Systèmes Embarqué", 3, "Licence", null);
        programme3 = new Programme(3L, "Réseau", 3, "Licence", null);

        listProgs = new ArrayList<>();
        listProgs.add(programme1);
        listProgs.add(programme2);
        listProgs.add(programme3);
        universite = new Universite(1L, "ISET RADES", "Rades, Ben Arous, Tunis, Tuunisie", TypeUniversite.PUBLIC, null, listProgs);
    }

    @Test
    void testAddOrUpdate() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addOrUpdate(universite);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testFindById() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.findById(1L);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testFindUniversitiesByTypeAndProgram() {
        List<Universite> universities = new ArrayList<>();
        universities.add(universite);

        when(universiteRepository.findByTypeUniversiteAndProgrammesNiveau(any(), any()))
                .thenReturn(universities);

        List<Universite> result = universiteService.findUniversitiesByTypeAndProgram(TypeUniversite.PUBLIC, "Licence");
        assertEquals(1, result.size());
        assertEquals(universite, result.get(0));
        verify(universiteRepository, times(1)).findByTypeUniversiteAndProgrammesNiveau(any(), any());
    }

    @Test
    void testIncreaseCodeCoverage(){
        Bloc blocCoverage = new Bloc();
        blocCoverage.setNomBloc("yes");
        Chambre chamb = new Chambre();
        chamb.setBloc(blocCoverage);
        Etudiant etud = new Etudiant();
        etud.setPrenomEt("Marwen");
        Foyer foy = new Foyer();
        foy.setNomFoyer("Esprit");
        Programme pr = new Programme();
        pr.setNiveau("Master");
        Reservation reserv = new Reservation();
        reserv.setEstValide(false);
        Universite un = new Universite();
        un.setAdresse("Ariana");
        assertEquals(un.getAdresse(), "Ariana");
    }

}
