package com.example.module11.controller;

import com.example.module11.entity.User;
import com.example.module11.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({UserController.class})
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @InjectMocks
    ObjectMapper mapper;

    @Test
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Ilia", 25),
                new User(2L, "Roman",  35)
        ));

        when(service.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void getUserById() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Ilia", 25),
                new User(2L, "Roman",  35)
        ));
        when(service.findById(1L)).thenReturn(users.get(0));
        mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ilia"));
    }

    @Test
    void createUser() throws Exception {
        User user = new User(3L, "Mark", 25);

        when(service.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
                )
                .andDo(print())
                .andExpect(header().string("Location", "/api/v1/users/" + user.getId()))
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception {
        User user = new User(3L, "Mark", 25);

        when(service.update(any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
                )
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());

    }

    @Test
    void deleteUserById() throws Exception {
        doNothing().when(service).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }
}