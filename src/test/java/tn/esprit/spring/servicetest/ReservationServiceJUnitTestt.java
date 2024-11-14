package tn.esprit.spring.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.dao.entities.Bloc;
import tn.esprit.spring.dao.entities.Foyer;
import tn.esprit.spring.dao.entities.Universite;
import tn.esprit.spring.dao.repositories.BlocRepository;
import tn.esprit.spring.dao.repositories.FoyerRepository;
import tn.esprit.spring.dao.repositories.UniversiteRepository;
import tn.esprit.spring.services.Foyer.FoyerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ReservationServiceJUnitTestt {
    @InjectMocks
    private FoyerService foyerService;

    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private BlocRepository blocRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOrUpdate() {
        Foyer foyer = new Foyer();
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.addOrUpdate(foyer);

        assertEquals(foyer, result);
        verify(foyerRepository, times(1)).save(foyer);
    }

    @Test
    void testFindAll() {
        List<Foyer> foyers = new ArrayList<>();
        when(foyerRepository.findAll()).thenReturn(foyers);

        List<Foyer> result = foyerService.findAll();

        assertEquals(foyers, result);
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        long id = 1L;
        Foyer foyer = new Foyer();
        when(foyerRepository.findById(id)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.findById(id);

        assertEquals(foyer, result);
        verify(foyerRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteById() {
        long id = 1L;

        foyerService.deleteById(id);

        verify(foyerRepository, times(1)).deleteById(id);
    }

    @Test
    void testDelete() {
        Foyer foyer = new Foyer();

        foyerService.delete(foyer);

        verify(foyerRepository, times(1)).delete(foyer);
    }

    @Test
    void testAffecterFoyerAUniversite() {
        long idFoyer = 1L;
        String nomUniversite = "Universite Test";
        Foyer foyer = new Foyer();
        Universite universite = new Universite();

        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversite(nomUniversite)).thenReturn(universite);
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = foyerService.affecterFoyerAUniversite(idFoyer, nomUniversite);

        assertEquals(universite, result);
        assertEquals(foyer, universite.getFoyer());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testDesaffecterFoyerAUniversite() {
        long idUniversite = 1L;
        Universite universite = new Universite();
        universite.setFoyer(new Foyer());

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = foyerService.desaffecterFoyerAUniversite(idUniversite);

        assertEquals(universite, result);
        assertNull(universite.getFoyer());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testAjouterFoyerEtAffecterAUniversite() {
        Foyer foyer = new Foyer();
        long idUniversite = 1L;
        List<Bloc> blocs = new ArrayList<>();
        foyer.setBlocs(blocs);
        Universite universite = new Universite();

        when(foyerRepository.save(foyer)).thenReturn(foyer);
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);

        assertEquals(foyer, result);
        assertEquals(foyer, universite.getFoyer());
        verify(foyerRepository, times(1)).save(foyer);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testAjoutFoyerEtBlocs() {
        Foyer foyer = new Foyer();
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc = new Bloc();
        blocs.add(bloc);
        foyer.setBlocs(blocs);

        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.ajoutFoyerEtBlocs(foyer);

        assertEquals(foyer, result);
        verify(foyerRepository, times(1)).save(foyer);
        verify(blocRepository, times(1)).save(bloc);
    }
}
