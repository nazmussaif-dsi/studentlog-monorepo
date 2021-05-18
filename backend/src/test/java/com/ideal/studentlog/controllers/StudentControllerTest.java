package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.StudentRepository;
import com.ideal.studentlog.helpers.dataclass.StudentDTO;
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
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StudentRepository repository;

    @Test
    public void shouldReturnAvailableStudents() throws Exception {
        mockMvc
                .perform(get("/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("MD SHOEB AL AFNAN")))
                .andExpect(jsonPath("$[1].bloodGroup", is("A+")))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void shouldReturnStudentGetById() throws Exception {
        mockMvc
                .perform(get("/students/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("AYMAN RAHMAN")))
                .andExpect(jsonPath("$.presentAddress", is("Kolabagan, Dhaka")));
    }

    @Test
    @Transactional
    public void shouldCreateStudent() throws Exception {
        mockMvc
                .perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Test Student")))
                .andExpect(jsonPath("$.studentId", is("student-001")));

        assertEquals(repository.count(), 11);
    }

    @Test
    @Transactional
    public void shouldUpdateStudent() throws Exception {
        mockMvc
                .perform(
                        patch("/students/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Student")))
                .andExpect(jsonPath("$.studentId", is("student-001")));

        assertEquals(repository.count(), 10);
    }

    @Test
    @Transactional
    public void shouldDeleteStudent() throws Exception {
        mockMvc
                .perform(
                        delete("/students/10")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 9);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentStudent() throws Exception {
        mockMvc
                .perform(get("/students/11"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Student not found with ID: 11")));
    }

    @NotNull
    @Contract(" -> new")
    private StudentDTO getDto() {
        return new StudentDTO(
                null,
                "Test Student",
                "b:reg-001",
                "student-001",
                new Date(),
                "A+",
                "Strange Location",
                "Even Stranger Location",
                "Test Student's Father",
                "test.father@fakemail.com",
                "01819101111"
        );
    }

}
