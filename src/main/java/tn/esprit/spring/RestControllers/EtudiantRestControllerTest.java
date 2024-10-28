package tn.esprit.spring.RestControllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.RestControllers.EtudiantRestController;
import tn.esprit.spring.Services.Etudiant.IEtudiantService;

import java.util.Arrays;
import java.util.List;

public class EtudiantRestControllerTest {

    @InjectMocks
    private EtudiantRestController etudiantRestController;

    @Mock
    private IEtudiantService etudiantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddOrUpdate() {
        Etudiant etudiant = new Etudiant(/* initialisation */);
        when(etudiantService.addOrUpdate(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantRestController.addOrUpdate(etudiant);
        assertEquals(etudiant, result);
        verify(etudiantService, times(1)).addOrUpdate(etudiant);
    }

    @Test
    public void testFindAll() {
        Etudiant etudiant1 = new Etudiant(/* initialisation */);
        Etudiant etudiant2 = new Etudiant(/* initialisation */);
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);

        when(etudiantService.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantRestController.findAll();
        assertEquals(2, result.size());
        verify(etudiantService, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        long id = 1L;
        Etudiant etudiant = new Etudiant(/* initialisation */);

        when(etudiantService.findById(id)).thenReturn(etudiant);

        Etudiant result = etudiantRestController.findById(id);
        assertEquals(etudiant, result);
        verify(etudiantService, times(1)).findById(id);
    }

    @Test
    public void testDelete() {
        Etudiant etudiant = new Etudiant(/* initialisation */);

        etudiantRestController.delete(etudiant);
        verify(etudiantService, times(1)).delete(etudiant);
    }

    @Test
    public void testDeleteById() {
        long id = 1L;

        etudiantRestController.deleteById(id);
        verify(etudiantService, times(1)).deleteById(id);
    }
}
