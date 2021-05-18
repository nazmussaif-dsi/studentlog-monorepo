package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.SchoolClassRepository;
import com.ideal.studentlog.helpers.dtos.SchoolClassDTO;
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
public class SchoolClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SchoolClassRepository repository;

    @Test
    public void shouldReturnAvailableSchoolClasses() throws Exception {
        mockMvc
                .perform(get("/school-classes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Andreana")))
                .andExpect(jsonPath("$[1].grade", is(7)))
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    public void shouldReturnSchoolClassGetById() throws Exception {
        mockMvc
                .perform(get("/school-classes/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Arnuad")))
                .andExpect(jsonPath("$.grade", is(2)));
    }

    @Test
    @Transactional
    public void shouldCreateSchoolClass() throws Exception {
        mockMvc
                .perform(
                        post("/school-classes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("New Class Name")))
                .andExpect(jsonPath("$.grade", is(9)));

        assertEquals(repository.count(), 21);
    }

    @Test
    @Transactional
    public void shouldUpdateSchoolClass() throws Exception {
        mockMvc
                .perform(
                        patch("/school-classes/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("New Class Name")))
                .andExpect(jsonPath("$.grade", is(9)));

        assertEquals(repository.count(), 20);
    }

    @Test
    @Transactional
    public void shouldDeleteSchoolClass() throws Exception {
        mockMvc
                .perform(
                        delete("/school-classes/20")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 19);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentSchoolClass() throws Exception {
        mockMvc
                .perform(get("/school-classes/101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("School Class not found with ID: 101")));
    }

    @NotNull
//    @Contract(" -> new")
    private SchoolClassDTO getDto() throws ParseException {
        return new SchoolClassDTO(
                "New Class Name",
                9
        );
    }

}
