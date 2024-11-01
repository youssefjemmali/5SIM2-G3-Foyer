package tn.esprit.spring.Services.Etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
class EtudiantServicesIntegTest {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    @Rollback(false)
    public void setUp() {
        etudiantRepository.deleteAll();
        reservationRepository.deleteAll();
    }

    // Test pour vérifier l'ajout d'un étudiant
    @Test
    @Transactional
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEt("John");
        etudiant.setPrenomEt("Doe");
        etudiant.setCin(123456);
        etudiant.setEcole("Ecole A");
        etudiant.setDateNaissance(LocalDate.of(2000, 1, 1));

        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        assertEquals("John", savedEtudiant.getNomEt());
        assertEquals("Doe", savedEtudiant.getPrenomEt());
        assertEquals(1, etudiantRepository.count(), "L'étudiant doit être enregistré dans la base de données");
    }

    // Test pour vérifier l'ajout d'une réservation à un étudiant existant
    @Test
    @Transactional
    void testAddReservationToEtudiant() {
        // Ajouter un étudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEt("John");
        etudiant.setPrenomEt("Doe");
        etudiant.setCin(123456);
        etudiant.setEcole("Ecole A");
        etudiant.setDateNaissance(LocalDate.of(2000, 1, 1));

        // Ajouter une réservation
        Reservation reservation = new Reservation();
        reservation.setIdReservation("res1");
        reservation.setEstValide(true);
        reservation.setAnneeUniversitaire(LocalDate.of(2023, 1, 15));

        // Associer la réservation à l'étudiant
        etudiant.getReservations().add(reservation);

        // Sauvegarder l'étudiant (ce qui sauvegardera aussi la réservation)
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);

        // Vérifier que l'étudiant et la réservation ont bien été enregistrés
        assertEquals(1, savedEtudiant.getReservations().size(), "L'étudiant doit avoir une réservation");

        // Charger l'étudiant et vérifier ses réservations
        Etudiant etudiantWithReservation = etudiantRepository.findById(savedEtudiant.getIdEtudiant()).orElse(null);
        assertNotNull(etudiantWithReservation, "L'étudiant doit exister dans la base de données");
        assertEquals(1, etudiantWithReservation.getReservations().size(), "L'étudiant doit avoir une réservation");




    }
    @Test
    @Transactional
    void testFindEtudiantsWithValidReservations() {
        // Ajouter un étudiant avec une réservation valide
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setNomEt("John");
        etudiant1.setPrenomEt("Doe");
        etudiant1.setCin(123456);
        etudiant1.setEcole("Ecole A");
        etudiant1.setDateNaissance(LocalDate.of(2000, 1, 1));

        Reservation reservation1 = new Reservation();
        reservation1.setIdReservation("res1");
        reservation1.setEstValide(true);
        reservation1.setAnneeUniversitaire(LocalDate.of(2023, 1, 15));

        // Association des deux côtés
        etudiant1.getReservations().add(reservation1);
        reservation1.getEtudiants().add(etudiant1);

        // Sauvegarder l'étudiant (ce qui sauvegardera aussi la réservation)
        etudiantRepository.save(etudiant1);
        reservationRepository.save(reservation1); // Assurez-vous que la réservation est également sauvegardée

        System.out.println("Étudiant 1 ajouté : " + etudiant1);
        System.out.println("Réservation 1 ajoutée : " + reservation1);

        // Ajouter un autre étudiant avec une réservation invalide
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setNomEt("Jane");
        etudiant2.setPrenomEt("Doe");
        etudiant2.setCin(654321);
        etudiant2.setEcole("Ecole B");
        etudiant2.setDateNaissance(LocalDate.of(2001, 1, 1));

        Reservation reservation2 = new Reservation();
        reservation2.setIdReservation("res2");
        reservation2.setEstValide(false); // Non valide
        reservation2.setAnneeUniversitaire(LocalDate.of(2023, 1, 15));

        // Association des deux côtés
        etudiant2.getReservations().add(reservation2);
        reservation2.getEtudiants().add(etudiant2);

        // Sauvegarder l'étudiant (ce qui sauvegardera aussi la réservation)
        etudiantRepository.save(etudiant2);
        reservationRepository.save(reservation2); // Assurez-vous que la réservation est également sauvegardée

        System.out.println("Étudiant 2 ajouté : " + etudiant2);
        System.out.println("Réservation 2 ajoutée : " + reservation2);

        // Récupérer les étudiants avec des réservations valides
        List<Etudiant> validEtudiants = etudiantRepository.findByReservations_EstValideTrue();

        // Afficher les étudiants valides
        System.out.println("Étudiants avec réservations valides : " + validEtudiants);

        // Vérifier que seul l'étudiant avec une réservation valide est récupéré
        assertEquals(1, validEtudiants.size(), "Il doit y avoir un étudiant avec une réservation valide");
        assertEquals("John", validEtudiants.get(0).getNomEt(), "L'étudiant récupéré doit être John");
    }



}