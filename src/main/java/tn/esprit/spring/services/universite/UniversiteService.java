package tn.esprit.spring.services.universite;

import lombok.AllArgsConstructor;
import tn.esprit.spring.dao.Repositories.UniversiteRepository;
import tn.esprit.spring.dao.entities.TypeUniversite;
import tn.esprit.spring.dao.entities.Universite;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteService implements IUniversiteService {
    UniversiteRepository repo;

    @Override
    public Universite addOrUpdate(Universite u) {
        return repo.save(u);
    }

    @Override
    public List<Universite> findAll() {
        return repo.findAll();
    }

    @Override
    public Universite findById(long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
    }

    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(Universite u) {
        repo.delete(u);
    }
    
    // Retourne la liste des Universités répondant au deux critères : Type (Privé/Public) & Programme.niveau (Licence, Master, Ingénieur, Doctorat)
    @Override
    public List<Universite> findUniversitiesByTypeAndProgram(TypeUniversite typeUn, String niveauProg) {
        return repo.findByTypeUniversiteAndProgrammesNiveau(typeUn, niveauProg);
    }
    
}
