package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.P5Application;

@SpringBootTest(classes = P5Application.class)
@ActiveProfiles("test")
public class EtudiantRepositoryIntegrationTest {

    @Autowired
    private EtudiantRepository repo;

    @Test
    void testFindAllContainsPreloadedData() {
        List<com.example.entity.Etudiant> etudiants = repo.findAll();
        assertEquals(2, etudiants.size());
    }
}
