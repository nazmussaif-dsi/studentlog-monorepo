package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.SubjectDetailsRepository;
import com.ideal.studentlog.helpers.dataclass.SubjectDetailsDTO;
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
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubjectDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SubjectDetailsRepository repository;

    @Test
    public void shouldReturnAvailableSubjectDetails() throws Exception {
        mockMvc
                .perform(get("/subject-details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subjectId", is(1)))
                .andExpect(jsonPath("$[1].teacherId" +
                        "", is(2)))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void shouldReturnSubjectDetailsById() throws Exception {
        mockMvc
                .perform(get("/subject-details/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId", is(3)))
                .andExpect(jsonPath("$.teacherId", is(3)));
    }

    @Test
    @Transactional
    public void shouldCreateSubjectDetails() throws Exception {
        mockMvc
                .perform(
                        post("/subject-details")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subjectId", is(10)))
                .andExpect(jsonPath("$.teacherId", is(10)));

        assertEquals(repository.count(), 11);
    }

    @Test
    @Transactional
    public void shouldUpdateSubjectDetails() throws Exception {
        mockMvc
                .perform(
                        patch("/subject-details/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId", is(10)))
                .andExpect(jsonPath("$.classDetailsId", is(10)));

        assertEquals(repository.count(), 10);
    }

    @Test
    @Transactional
    public void shouldDeleteSubjectDetails() throws Exception {
        mockMvc
                .perform(
                        delete("/subject-details/7")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 9);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentSubjectDetails() throws Exception {
        mockMvc
                .perform(get("/subject-details/12"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Subject Details not found with ID: 12")));
    }

    @NotNull
    @Contract(" -> new")
    private SubjectDetailsDTO getDto() {
        return new SubjectDetailsDTO(
                10,
                10,
                10
        );
    }

}
