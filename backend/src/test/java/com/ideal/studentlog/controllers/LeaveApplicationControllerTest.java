package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.LeaveApplicationRepository;
import com.ideal.studentlog.helpers.dtos.LeaveApplicationDTO;
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
public class LeaveApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private LeaveApplicationRepository repository;

    @Test
    public void shouldReturnAvailableLeaveApplications() throws Exception {
        mockMvc
                .perform(get("/leave-applications"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].approvedById", is(1)))
                .andExpect(jsonPath("$[1].studentId", is(2)))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldReturnLeaveApplicationGetById() throws Exception {
        mockMvc
                .perform(get("/leave-applications/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.applicationBody", containsString("With due respect and humble submission")))
                .andExpect(jsonPath("$.approvedById", is(5)));
    }

    @Test
    @Transactional
    public void shouldCreateLeaveApplication() throws Exception {
        mockMvc
                .perform(
                        post("/leave-applications")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.applicationBody", is("Test Leave Application")))
                .andExpect(jsonPath("$.approvedById", is(1)));

        assertEquals(repository.count(), 4);
    }

    @Test
    @Transactional
    public void shouldUpdateLeaveApplication() throws Exception {
        mockMvc
                .perform(
                        patch("/leave-applications/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.applicationBody", is("Test Leave Application")))
                .andExpect(jsonPath("$.studentId", is(9)));

        assertEquals(repository.count(), 3);
    }

    @Test
    @Transactional
    public void shouldDeleteLeaveApplication() throws Exception {
        mockMvc
                .perform(
                        delete("/leave-applications/3")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 2);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentLeaveApplication() throws Exception {
        mockMvc
                .perform(get("/leave-applications/11"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Leave Application not found with ID: 11")));
    }

    @NotNull
    @Contract(" -> new")
    private LeaveApplicationDTO getDto() {
        return new LeaveApplicationDTO(
                new Date(),
                new Date(),
                9,
                "Test Leave Application",
                1
        );
    }

}
