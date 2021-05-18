package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.TestRepository;
import com.ideal.studentlog.helpers.dataclass.TestDTO;
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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestRepository repository;

    @Test
    public void shouldReturnAvailableTests() throws Exception {
        mockMvc
                .perform(get("/tests"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject", is("Bengali")))
                .andExpect(jsonPath("$[1].examiner", is("FAHMIDA TASNIM")))
                .andExpect(jsonPath("$", hasSize(24)));
    }

    @Test
    public void shouldReturnTestById() throws Exception {
        mockMvc
                .perform(get("/tests/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject", is("Mathematics")))
                .andExpect(jsonPath("$.date", containsString("2005-03-13")));
    }

    @Test
    @Transactional
    public void shouldCreateTest() throws Exception {
        mockMvc
                .perform(
                        post("/tests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject", is("Agricultural Science")))
                .andExpect(jsonPath("$.examiner", is("ARGHA PROTIM ANGON")));

        assertEquals(repository.count(), 25);
    }

    @Test
    @Transactional
    public void shouldUpdateTest() throws Exception {
        mockMvc
                .perform(
                        patch("/tests/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject", is("Agricultural Science")))
                .andExpect(jsonPath("$.examiner", is("ARGHA PROTIM ANGON")));

        assertEquals(repository.count(), 24);
    }

    @Test
    @Transactional
    public void shouldDeleteTest() throws Exception {
        mockMvc
                .perform(
                        delete("/tests/24")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 23);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentTest() throws Exception {
        mockMvc
                .perform(get("/tests/25"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Test not found with ID: 25")));
    }

    @NotNull
    @Contract(" -> new")
    private TestDTO getDto() {
        return new TestDTO(
                "Agricultural Science",
                "ARGHA PROTIM ANGON",
                new Date()
        );
    }

}
