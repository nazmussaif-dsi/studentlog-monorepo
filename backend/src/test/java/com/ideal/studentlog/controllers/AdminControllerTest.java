package com.ideal.studentlog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.studentlog.database.repositories.AdminRepository;
import com.ideal.studentlog.helpers.dataclass.AdminDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AdminRepository repository;

    @Test
    public void shouldReturnAvailableAdmins() throws Exception {
        mockMvc
                .perform(get("/admins"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("MD SHOEB AL AFNAN")))
                .andExpect(jsonPath("$[1].bloodGroup", is("A+")))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void shouldReturnAdminGetById() throws Exception {
        mockMvc
                .perform(get("/admins/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("AYMAN RAHMAN")))
                .andExpect(jsonPath("$.presentAddress", is("Kolabagan, Dhaka")));
    }

    @Test
    @Transactional
    public void shouldCreateAdmin() throws Exception {
        mockMvc
                .perform(
                        post("/admins")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Test Admin")))
                .andExpect(jsonPath("$.adminId", is("test-id")));

        assertEquals(repository.count(), 11);
    }

    @Test
    @Transactional
    public void shouldUpdateAdmin() throws Exception {
        mockMvc
                .perform(
                        patch("/admins/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Admin")))
                .andExpect(jsonPath("$.adminId", is("test-id")));

        assertEquals(repository.count(), 10);
    }

    @Test
    @Transactional
    public void shouldDeleteAdmin() throws Exception {
        mockMvc
                .perform(
                        delete("/admins/5")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(getDto()))
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(repository.count(), 9);
    }

    @Test
    public void shouldReturnNotFoundResponseForNonExistentAdmin() throws Exception {
        mockMvc
                .perform(get("/admins/11"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.code", is("API-404")))
                .andExpect(jsonPath("$.message", is("Admin not found with ID: 11")));
    }

    @NotNull
    @Contract(" -> new")
    private AdminDTO getDto() {
        return new AdminDTO(
                "test-id",
                "Test Admin",
                "Manager",
                new Date(),
                "A+",
                "Four-Five",
                new Date(),
                new Date(),
                "01819101111",
                "Test present address",
                "Test permanent address"
        );
    }

}
