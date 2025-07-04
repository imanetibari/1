package com.example.demo;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.entity.Etudiant;
import com.example.repository.EtudiantRepository;

@DataJpaTest
@ActiveProfiles("test")  // لو عندك application-test.properties
public class EtudiantRepositoryIntegrationTest2 {

    @Autowired
    private EtudiantRepository repo;

    @Test
    public void testFindAllContainsPreloadedData() {
        List<Etudiant> etudiants = repo.findAll();
        assertEquals(2, etudiants.size()); // حسب بياناتك في data.sql
    }

    // يمكن تزيد اختبارات CRUD أخرى هنا
}
