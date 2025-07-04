package com.example.demo;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.entity.Etudiant;
import com.example.rcontroller.EtudiantController;
import com.example.service.EtudiantService;

@WebMvcTest(EtudiantController.class)
public class EtudiantControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EtudiantService service;

    @Test
    public void testCreateEtudiant() throws Exception {
        Etudiant e = new Etudiant("New Student", "newstudent@example.com");
        Etudiant saved = new Etudiant( "New Student", "newstudent@example.com");

        when(service.ajouterEtudiant(any(Etudiant.class))).thenReturn(saved);

        String json = """
            {
                "nom": "New Student",
                "email": "newstudent@example.com"
            }
            """;

        mockMvc.perform(post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.nom").value("New Student"))
                .andExpect(jsonPath("$.email").value("newstudent@example.com"));
    }
}
