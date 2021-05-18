package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.SubjectRepository;
import com.ideal.studentlog.helpers.dataclass.SubjectDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SubjectRepository repository;

    @Test
    public void shouldReturnAvailableSubjects() throws Exception {
        mockMvc
                .perform(get("/subjects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Bangla")))
                .andExpect(jsonPath("$[1].category", is("Pure subject")))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void shouldReturnSubjectGetById() throws Exception {
        mockMvc
                .perform(get("/subjects/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Physics")))
                .andExpect(jsonPath("$.category", is("Science")));
    }

    @Test
    @Transactional
    public void shouldCreateSubject() throws Exception {
        mockMvc
                .perform(
                        post("/subjects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Test Subject")))
                .andExpect(jsonPath("$.category", is("test-cat")));

        assertEquals(repository.count(), 11);
    }

    @Test
    @Transactional
    public void shouldUpdateSubject() throws Exception {
        mockMvc
                .perform(
                        patch("/subjects/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Subject")))
                .andExpect(jsonPath("$.category", is("test-cat")));

        assertEquals(repository.count(), 10);
    }

    @Test
    @Transactional
    public void shouldDeleteSubject() throws Exception {
        mockMvc
                .perform(
                        delete("/subjects/10")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 9);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentSubject() throws Exception {
        mockMvc
                .perform(get("/subjects/11"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Subject not found with ID: 11")));
    }

    @NotNull
    @Contract(" -> new")
    private SubjectDTO getDto() {
        return new SubjectDTO(
                "Test Subject",
                "test-cat"
        );
    }

}
