package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.entity.Etudiant;
import com.example.repository.EtudiantRepository;

@SpringBootTest
public class EtudiantRepositoryWithMockBeanTest {

    @MockBean
    private EtudiantRepository repo;  // هنا mock ديال الريبو

    @Autowired
    private EtudiantRepository realRepo; // هنا الكائن الحقيقي

    @Test
    public void testFindAllWithMock() {
        Etudiant e1 = new Etudiant("Ali", "ali@example.com");
        Etudiant e2 = new Etudiant("Sara", "sara@example.com");

        when(repo.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Etudiant> etudiants = repo.findAll();
        assertEquals(2, etudiants.size());
        assertEquals("Ali", etudiants.get(0).getNom());
    }

    @Test
    public void testFindByIdWithMock() {
        Etudiant e = new Etudiant("Yasmine", "yasmine@example.com");
        when(repo.findById(1L)).thenReturn(Optional.of(e));

        Optional<Etudiant> found = repo.findById(1L);
        assertEquals(true, found.isPresent());
        assertEquals("Yasmine", found.get().getNom());
    }
}
