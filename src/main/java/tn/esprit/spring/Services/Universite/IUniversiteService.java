package tn.esprit.spring.Services.Universite;

import java.util.List;

import tn.esprit.spring.dao.Entities.TypeUniversite;
import tn.esprit.spring.dao.Entities.Universite;

public interface IUniversiteService {
    Universite addOrUpdate(Universite u);
    List<Universite> findAll();
    Universite findById(long id);
    void deleteById(long id);
    void delete(Universite u);
    List<Universite> findUniversitiesByTypeAndProgram(TypeUniversite typeUn, String niveauProg);
}
