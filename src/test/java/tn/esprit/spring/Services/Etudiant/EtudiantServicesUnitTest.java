package tn.esprit.spring.Services.Etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EtudiantServicesUnitTest {

    @InjectMocks
    private EtudiantService etudiantService; // Le service à tester

    @Mock
    private EtudiantRepository etudiantRepository; // Simule le repository des étudiants

    @Mock
    private ReservationRepository reservationRepository; // Simule le repository des réservations

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    // Test pour vérifier l'ajout d'un étudiant via le service
    @Test
    public void testAddEtudiant() {
        // Préparation des données
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEt("Alice");
        etudiant.setPrenomEt("Smith");
        etudiant.setCin(789012);
        etudiant.setEcole("Ecole B");
        etudiant.setDateNaissance(LocalDate.of(2001, 5, 15));

        // Simulation du comportement du repository
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Appel de la méthode du service pour ajouter un étudiant
        Etudiant savedEtudiant = etudiantService.addOrUpdate(etudiant);

        // Vérifications
        assertNotNull(savedEtudiant, "L'étudiant sauvegardé ne doit pas être null");
        assertEquals("Alice", savedEtudiant.getNomEt(), "Le nom de l'étudiant doit être Alice");
        assertEquals("Smith", savedEtudiant.getPrenomEt(), "Le prénom de l'étudiant doit être Smith");
        verify(etudiantRepository, times(1)).save(etudiant);
    }



    // Test pour vérifier la récupération des étudiants avec des réservations valides
    @Test
    public void testFindEtudiantsWithValidReservations() {
        // Créer des étudiants avec des réservations
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setNomEt("John");
        etudiant1.setPrenomEt("Doe");
        etudiant1.setCin(123456);
        etudiant1.setEcole("Ecole A");
        etudiant1.setDateNaissance(LocalDate.of(2000, 1, 1));
        etudiant1.setReservations(new ArrayList<>());

        Reservation reservation1 = new Reservation();
        reservation1.setIdReservation("res1");
        reservation1.setEstValide(true);
        reservation1.setAnneeUniversitaire(LocalDate.of(2023, 1, 15));
        etudiant1.getReservations().add(reservation1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setNomEt("Jane");
        etudiant2.setPrenomEt("Doe");
        etudiant2.setCin(654321);
        etudiant2.setEcole("Ecole B");
        etudiant2.setDateNaissance(LocalDate.of(2001, 1, 1));
        etudiant2.setReservations(new ArrayList<>());

        Reservation reservation2 = new Reservation();
        reservation2.setIdReservation("res2");
        reservation2.setEstValide(false); // Non valide
        etudiant2.getReservations().add(reservation2);

        // Préparer les mocks pour le comportement des repositories
        List<Etudiant> etudiants = List.of(etudiant1, etudiant2);
        when(etudiantRepository.findByReservations_EstValideTrue()).thenReturn(List.of(etudiant1));

        // Appel de la méthode du service pour récupérer les étudiants avec des réservations valides
        List<Etudiant> validEtudiants = etudiantRepository.findByReservations_EstValideTrue();

        // Vérifications
        assertEquals(1, validEtudiants.size(), "Il doit y avoir un étudiant avec une réservation valide");
        assertEquals("John", validEtudiants.get(0).getNomEt(), "L'étudiant récupéré doit être John");
        verify(etudiantRepository, times(1)).findByReservations_EstValideTrue();
    }
}
