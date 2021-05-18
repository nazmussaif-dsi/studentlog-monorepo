package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.ClassStudentRepository;
import com.ideal.studentlog.helpers.dtos.ClassStudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.text.ParseException;

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
public class ClassStudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ClassStudentRepository repository;

    @Test
    public void shouldReturnAvailableClassStudents() throws Exception {
        mockMvc
                .perform(get("/class-students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].classDetailsId", is(19)))
                .andExpect(jsonPath("$[1].studentId", is(6)))
                .andExpect(jsonPath("$", hasSize(100)));
    }

    @Test
    public void shouldReturnClassStudentGetById() throws Exception {
        mockMvc
                .perform(get("/class-students/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.classDetailsId", is(19)))
                .andExpect(jsonPath("$.studentId", is(4)));
    }

    @Test
    @Transactional
    public void shouldCreateClassStudent() throws Exception {
        mockMvc
                .perform(
                        post("/class-students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.classDetailsId", is(15)))
                .andExpect(jsonPath("$.studentId", is(5)));

        assertEquals(repository.count(), 101);
    }

    @Test
    @Transactional
    public void shouldUpdateClassStudent() throws Exception {
        mockMvc
                .perform(
                        patch("/class-students/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.classDetailsId", is(15)))
                .andExpect(jsonPath("$.studentId", is(5)));

        assertEquals(repository.count(), 100);
    }

    @Test
    @Transactional
    public void shouldDeleteClassStudent() throws Exception {
        mockMvc
                .perform(
                        delete("/class-students/100")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 99);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentClassStudent() throws Exception {
        mockMvc
                .perform(get("/class-students/101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Class Student not found with ID: 101")));
    }

    @NotNull
//    @Contract(" -> new")
    private ClassStudentDTO getDto() throws ParseException {
        return new ClassStudentDTO(
                15,
                5
        );
    }

}
