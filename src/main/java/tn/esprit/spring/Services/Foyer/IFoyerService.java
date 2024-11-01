package tn.esprit.spring.Services.Foyer;

import java.util.List;

import tn.esprit.spring.dao.Entities.Etudiant;
import tn.esprit.spring.dao.Entities.Foyer;
import tn.esprit.spring.dao.Entities.Reservation;
import tn.esprit.spring.dao.Entities.Universite;

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
