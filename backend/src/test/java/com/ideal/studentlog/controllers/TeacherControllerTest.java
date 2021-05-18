package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.TeacherRepository;
import com.ideal.studentlog.helpers.dataclass.TeacherDTO;
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

import java.util.Date;

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
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TeacherRepository repository;

    @Test
    public void shouldReturnAvailableTeachers() throws Exception {
        mockMvc
                .perform(get("/teachers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Abdul Karim")))
                .andExpect(jsonPath("$[1].bloodGroup", is("A-")))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void shouldReturnTeacherGetById() throws Exception {
        mockMvc
                .perform(get("/teachers/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Sobur Ali Khan")))
                .andExpect(jsonPath("$.presentAddress", is("Bashabo, Dhaka")));
    }

    @Test
    @Transactional
    public void shouldCreateTeacher() throws Exception {
        mockMvc
                .perform(
                        post("/teachers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Test Teacher")))
                .andExpect(jsonPath("$.teacherId", is("1011")));

        assertEquals(repository.count(), 11);
    }

    @Test
    @Transactional
    public void shouldUpdateTeacher() throws Exception {
        mockMvc
                .perform(
                        patch("/teachers/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Teacher")))
                .andExpect(jsonPath("$.teacherId", is("1011")));

        assertEquals(repository.count(), 10);
    }

    @Test
    @Transactional
    public void shouldDeleteTeacher() throws Exception {
        mockMvc
                .perform(
                        delete("/teachers/10")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 9);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentTeacher() throws Exception {
        mockMvc
                .perform(get("/teachers/11"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Teacher not found with ID: 11")));
    }

    @NotNull
    @Contract(" -> new")
    private TeacherDTO getDto() {
        return new TeacherDTO(
                "Test Teacher",
                new Date(),
                new Date(),
                new Date(),
                "MSc",
                "1234567123",
                "1011",
                "Professor",
                "01819101111",
                "Dhaka",
                "Dhaka",
                "A+"
        );
    }

}
