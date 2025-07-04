package com.example.demo;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.entity.Etudiant;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EtudiantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllEtudiants() throws Exception {
        mockMvc.perform(get("/api/etudiants"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(4)); // 2 car data.sql
    }

    @Test
    void testCreateEtudiant() throws Exception {
        Etudiant e = new Etudiant();
        e.setNom("Layla");
        e.setEmail("layla@example.com");

        mockMvc.perform(post("/api/etudiants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(e)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.nom").value("Layla"));
    }

    @Test
    void testUpdateEtudiant() throws Exception {
        // Step 1: ajouter d'abord un etudiant
        Etudiant e = new Etudiant();
        e.setNom("Kamal");
        e.setEmail("kamal@example.com");

        String response = mockMvc.perform(post("/api/etudiants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(e)))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        Etudiant saved = objectMapper.readValue(response, Etudiant.class);

        // Step 2: update
        saved.setNom("Kamal Updated");

        mockMvc.perform(put("/api/etudiants/" + saved.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(saved)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nom").value("Kamal Updated"));
    }

    @Test
    void testDeleteEtudiant() throws Exception {
        // Step 1: ajouter d'abord un etudiant
        Etudiant e = new Etudiant();
        e.setNom("Youssef");
        e.setEmail("youssef@example.com");

        String response = mockMvc.perform(post("/api/etudiants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(e)))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        Etudiant saved = objectMapper.readValue(response, Etudiant.class);

        // Step 2: delete
        mockMvc.perform(delete("/api/etudiants/" + saved.getId()))
            .andExpect(status().isOk());
    }
}
