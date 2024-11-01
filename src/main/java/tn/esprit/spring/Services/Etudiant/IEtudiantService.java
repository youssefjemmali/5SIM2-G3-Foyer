package tn.esprit.spring.Services.Etudiant;

import java.util.List;

import tn.esprit.spring.dao.Entities.Etudiant;

public interface IEtudiantService {
    Etudiant addOrUpdate(Etudiant e);
    List<Etudiant> findAll();
    Etudiant findById(long id);
    void deleteById(long id);
    void delete(Etudiant e);
}
