package tn.esprit.spring.Services.Universite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Universite;
import tn.esprit.spring.DAO.Repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class testUniversiteService {
    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        programme1 = new Programme(1L, "Développement des Systèmes d'Information", 3, "Licence");
        programme2 = new Programme(2L, "Systèmes Embarqué", 3, "Licence");
        programme3 = new Programme(3L, "Réseau", 3, "Licence");
        listProgs = new ArrayList<>();
        listProgs.add(programme1);
        listProgs.add(programme2);
        listProgs.add(programme3);
        universite = new Universite(1L, "ISET RADES", "Rades, Ben Arous, Tunis, Tuunisie", TypeUniversite.PUBLIC, null, listProgs);
    }

    @Test
    public void testAddOrUpdate() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addOrUpdate(universite);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testFindById() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.findById(1L);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindUniversitiesByTypeAndProgram() {
        List<Universite> universities = new ArrayList<>();
        universities.add(universite);

        when(universiteRepository.findByTypeUniversiteLiketypeUnAndProgrammeNiveauLikeniveauProg(any(), any()))
                .thenReturn(universities);

        List<Universite> result = universiteService.findUniversitiesByTypeAndProgram(TypeUniversite.PRIVE, "Licence");
        assertEquals(1, result.size());
        assertEquals(universite, result.get(0));
        verify(universiteRepository, times(1)).findByTypeUniversiteLiketypeUnAndProgrammeNiveauLikeniveauProg(any(), any());
    }

}
