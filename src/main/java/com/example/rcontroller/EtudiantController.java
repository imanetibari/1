package com.example.rcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Etudiant;
import com.example.service.EtudiantService;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService service;

    @PostMapping
    public Etudiant create(@RequestBody Etudiant e) {
        return service.ajouterEtudiant(e);
    }

    @GetMapping
    public List<Etudiant> getAll() {
        return service.getAllEtudiants();
    }

    @PutMapping("/{id}")
    public Etudiant update(@PathVariable Long id, @RequestBody Etudiant e) {
        return service.updateEtudiant(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.supprimerEtudiant(id);
    }
}
