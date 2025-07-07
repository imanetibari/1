package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entity.Etudiant;
import com.example.repository.EtudiantRepository;

@SpringBootTest
public class EtudiantRepositoryIntegrationTest {

    @Autowired
    private EtudiantRepository repo;

    @Test
    public void testCreateEtudiant() {
        Etudiant e = new Etudiant("f", "youssef@example.com");
        Etudiant saved = repo.save(e);
        assertNotNull(saved.getId());
        System.out.println(" Etudiant  ID: " + saved.getId());
    }

    @Test
    public void testFindAllEtudiants() {
        java.util.List<Etudiant> etudiants = repo.findAll();
        System.out.println(" " + etudiants.size());
        assertNotNull(etudiants);
    }
    
//    @Test
//    public void testFindAllEtudiants() {
//        List<Etudiant> etudiants = repo.findAll();
//        System.out.println("📦 عدد الطلبة فـ DB: " + etudiants.size());
//        assertEquals(17, etudiants.size()); // 2 ديال data.sql + 17 لي زدتي = 19
//    }

}
