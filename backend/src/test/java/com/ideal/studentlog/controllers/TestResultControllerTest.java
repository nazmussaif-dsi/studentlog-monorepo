package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.TestResultRepository;
import com.ideal.studentlog.helpers.dataclass.TestResultDTO;
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
public class TestResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestResultRepository repository;

    @Test
    public void shouldReturnAvailableResults() throws Exception {
        mockMvc
                .perform(get("/test-results"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].testId", is(1)))
                .andExpect(jsonPath("$[1].studentId", is(1)))
                .andExpect(jsonPath("$", hasSize(37)));
    }

    @Test
    public void shouldReturnResultById() throws Exception {
        mockMvc
                .perform(get("/test-results/11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.testId", is(11)))
                .andExpect(jsonPath("$.grade", containsString("A+")));
    }

    @Test
    public void shouldReturnResultsByStudentId() throws Exception {
        mockMvc
                .perform(get("/test-results/student/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].testId", is(1)))
                .andExpect(jsonPath("$[2].grade", containsString("B+")))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    @Transactional
    public void shouldCreateResult() throws Exception {
        mockMvc
                .perform(
                        post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.testId", is(5)))
                .andExpect(jsonPath("$.grade", is("D-")));

        assertEquals(repository.count(), 38);
    }

    @Test
    @Transactional
    public void shouldUpdateResult() throws Exception {
        mockMvc
                .perform(
                        patch("/test-results/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.testId", is(5)))
                .andExpect(jsonPath("$.studentId", is(10)));

        assertEquals(repository.count(), 37);
    }

    @Test
    @Transactional
    public void shouldDeleteResult() throws Exception {
        mockMvc
                .perform(
                        delete("/test-results/5")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 36);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentResult() throws Exception {
        mockMvc
                .perform(get("/test-results/38"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Test Result not found with ID: 38")));
    }

    @Test
    public void shouldReturnBadRequestResponseWhenCreatingStudentWithInvalidId() throws Exception {
        TestResultDTO dto = getDto();

        dto.setStudentId(100);

        mockMvc
                .perform(
                        post("/test-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.code", is("API-400")))
                .andExpect(jsonPath("$.message", is("[Invalid value for field studentId: 100]")));

        assertEquals(repository.count(), 37);
    }

    @NotNull
    @Contract(" -> new")
    private TestResultDTO getDto() {
        return new TestResultDTO(
                5,
                10,
                "D-"
        );
    }

}
