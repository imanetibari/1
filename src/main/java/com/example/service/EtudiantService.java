package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Etudiant;
import com.example.repository.EtudiantRepository;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository repo;

    public Etudiant ajouterEtudiant(Etudiant e) {
        return repo.save(e);
    }

    public List<Etudiant> getAllEtudiants() {
        return repo.findAll();
    }

    public Etudiant updateEtudiant(Long id, Etudiant e) {
        Etudiant exist = repo.findById(id).orElseThrow();
        exist.setNom(e.getNom());
        exist.setEmail(e.getEmail());
        return repo.save(exist);
    }

    public void supprimerEtudiant(Long id) {
        repo.deleteById(id);
    }
}
