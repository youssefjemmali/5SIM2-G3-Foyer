package tn.esprit.spring.services.foyer;

import java.util.List;

import tn.esprit.spring.dao.entities.Etudiant;
import tn.esprit.spring.dao.entities.Foyer;
import tn.esprit.spring.dao.entities.Reservation;
import tn.esprit.spring.dao.entities.Universite;

public interface IFoyerService {
    Foyer addOrUpdate(Foyer f);

    List<Foyer> findAll();

    Foyer findById(long id);

    void deleteById(long id);

    void delete(Foyer f);

    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);

    Universite desaffecterFoyerAUniversite(long idUniversite);

    Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, long idUniversite); // Universite: Parent / Foyer:Child
    Foyer ajoutFoyerEtBlocs(Foyer foyer);


}
