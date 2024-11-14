package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.dao.entities.Bloc;
import tn.esprit.spring.dao.entities.Foyer;
import tn.esprit.spring.dao.entities.Universite;
import tn.esprit.spring.dao.repositories.BlocRepository;
import tn.esprit.spring.dao.repositories.FoyerRepository;
import tn.esprit.spring.dao.repositories.UniversiteRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class FoyerServiceTest {

    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private FoyerService foyerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test method for addOrUpdate(Foyer f)
    @Test
     void testAddOrUpdate() {
        // Arrange
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Test Foyer");

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Act
        Foyer result = foyerService.addOrUpdate(foyer);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdFoyer());
        assertEquals("Test Foyer", result.getNomFoyer());
        verify(foyerRepository, times(1)).save(foyer);
    }

    // Test method for findById(long id)
    @Test
    void testFindById() {
        // Arrange
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Act
        Foyer result = foyerService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdFoyer());
        verify(foyerRepository, times(1)).findById(1L);
    }

    // Test method for deleteById(long id)
    @Test
    void testDeleteById() {
        // Arrange
        long id = 1L;
        doNothing().when(foyerRepository).deleteById(id);

        // Act
        foyerService.deleteById(id);

        // Assert
        verify(foyerRepository, times(1)).deleteById(id);
    }

    // Test method for affecterFoyerAUniversite(long idFoyer, String nomUniversite)
    @Test
    void testAffecterFoyerAUniversite() {
        // Arrange
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);

        Universite universite = new Universite();
        universite.setNomUniversite("Universite Test");

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversite("Universite Test")).thenReturn(universite);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = foyerService.affecterFoyerAUniversite(1L, "Universite Test");

        // Assert
        assertNotNull(result);
        assertEquals(foyer, result.getFoyer());
        verify(universiteRepository, times(1)).save(universite);
    }
    // Test method for desaffecterFoyerAUniversite(long idUniversite)
    @Test
     void testDesaffecterFoyerAUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setIdUniversite(1L);
        Foyer foyer = new Foyer();
        universite.setFoyer(foyer);

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Universite result = foyerService.desaffecterFoyerAUniversite(1L);

        // Assert
        assertNotNull(result);
        assertNull(result.getFoyer());  // The Foyer should be dissociated
        verify(universiteRepository, times(1)).save(universite);
    }

    // Test method for ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite)
    @Test
    void testAjouterFoyerEtAffecterAUniversite() {
        // Arrange
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L);

        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        foyer.getBlocs().add(bloc);

        Universite universite = new Universite();
        universite.setIdUniversite(1L);

        when(foyerRepository.save(foyer)).thenReturn(foyer);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Act
        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        // Assert
        assertNotNull(result);
        assertEquals(foyer.getIdFoyer(), result.getIdFoyer());
        verify(foyerRepository, times(1)).save(foyer);
        verify(blocRepository, times(1)).save(bloc); // Verify that bloc is saved
        verify(universiteRepository, times(1)).save(universite); // Verify that universite is updated with foyer
    }

    // Test method for ajoutFoyerEtBlocs(Foyer foyer)
    @Test
     void testAjoutFoyerEtBlocs() {
        // Arrange
        Foyer foyer = new Foyer();
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();
        foyer.getBlocs().add(bloc1);
        foyer.getBlocs().add(bloc2);

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        // Act
        Foyer result = foyerService.ajoutFoyerEtBlocs(foyer);

        // Assert
        assertNotNull(result);
        assertEquals(foyer, result);
        verify(foyerRepository, times(1)).save(foyer);
        verify(blocRepository, times(2)).save(any(Bloc.class)); // Verify that each bloc is saved
    }

}
