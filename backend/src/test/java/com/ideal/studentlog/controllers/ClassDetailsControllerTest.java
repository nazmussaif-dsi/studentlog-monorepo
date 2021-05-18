package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.ClassDetailsRepository;
import com.ideal.studentlog.helpers.dtos.ClassDetailsDTO;
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
public class ClassDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ClassDetailsRepository repository;

    @Test
    public void shouldReturnAvailableClassDetails() throws Exception {
        mockMvc
                .perform(get("/class-details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].schoolClassId", is(5)))
                .andExpect(jsonPath("$[1].year", is("1994")))
                .andExpect(jsonPath("$", hasSize(40)));
    }

    @Test
    public void shouldReturnClassDetailsGetById() throws Exception {
        mockMvc
                .perform(get("/class-details/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolClassId", is(3)))
                .andExpect(jsonPath("$.teacherId", is(6)));
    }

    @Test
    @Transactional
    public void shouldCreateClassDetails() throws Exception {
        mockMvc
                .perform(
                        post("/class-details")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolClassId", is(15)))
                .andExpect(jsonPath("$.year", is("2015")));

        assertEquals(repository.count(), 41);
    }

    @Test
    @Transactional
    public void shouldUpdateClassDetails() throws Exception {
        mockMvc
                .perform(
                        patch("/class-details/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolClassId", is(15)))
                .andExpect(jsonPath("$.year", is("2015")));

        assertEquals(repository.count(), 40);
    }

    @Test
    @Transactional
    public void shouldDeleteClassDetails() throws Exception {
        mockMvc
                .perform(
                        delete("/class-details/40")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 39);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentClassDetails() throws Exception {
        mockMvc
                .perform(get("/class-details/101"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Class Details not found with ID: 101")));
    }

    @NotNull
//    @Contract(" -> new")
    private ClassDetailsDTO getDto() throws ParseException {
        return new ClassDetailsDTO(
                15,
                "2015",
                5
        );
    }

}
