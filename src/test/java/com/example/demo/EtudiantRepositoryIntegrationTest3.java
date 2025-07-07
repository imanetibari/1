package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.Etudiant;
import com.example.repository.EtudiantRepository;

@SpringBootTest

public class EtudiantRepositoryIntegrationTest3 {

    @Autowired
    private EtudiantRepository repo;

    @Test
    public void testCreateEtudiant() {
        Etudiant e = new Etudiant("i", "youssef@example.com");
        Etudiant saved = repo.save(e);
        assertNotNull(saved.getId());
        System.out.println("✔ Etudiant ajouté avec ID: " + saved.getId());
    }

    @Test
    public void testFindAllEtudiants() {
        List<Etudiant> etudiants = repo.findAll();
        System.out.println("📦 Nombre d'étudiants dans la base: " + etudiants.size());
        assertNotNull(etudiants);
    }
}
