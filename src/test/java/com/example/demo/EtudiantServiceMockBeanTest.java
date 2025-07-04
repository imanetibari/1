package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.example.service.EtudiantService;

@SpringBootTest
class EtudiantServiceMockBeanTest {

    @MockBean
    private EtudiantRepository repo;  // mock ديال الريبو فـ ApplicationContext

    @Autowired
    private EtudiantService service;  // service حقيقية كتعتمد على الريبو المموه

    @Test
    void testAjouterEtudiant() {
        Etudiant e = new Etudiant();
        e.setNom("Ali");
        when(repo.save(any(Etudiant.class))).thenReturn(e);

        Etudiant saved = service.ajouterEtudiant(e);

        assertEquals("Ali", saved.getNom());
    }

    @Test
    void testGetAllEtudiants() {
        List<Etudiant> list = Arrays.asList(new Etudiant(), new Etudiant());
        when(repo.findAll()).thenReturn(list);

        List<Etudiant> result = service.getAllEtudiants();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant exist = new Etudiant();
        exist.setId(1L);
        exist.setNom("Old Name");

        Etudiant newData = new Etudiant();
        newData.setNom("New Name");

        when(repo.findById(1L)).thenReturn(Optional.of(exist));
        when(repo.save(any(Etudiant.class))).thenReturn(exist);

        Etudiant updated = service.updateEtudiant(1L, newData);
        assertEquals("New Name", updated.getNom());
    }

    @Test
    void testSupprimerEtudiant() {
        service.supprimerEtudiant(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}
