package ru.edu.module12.controller;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.module12.entity.User;
import ru.edu.module12.service.UserService;

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

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest({UserController.class})
@AutoConfigureMockMvc(addFilters=false)
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

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }


    @Test
    @WithMockUser(roles = "ADMIN", username = "admin",password = "123456")
    void deleteUserById() throws Exception {
        doNothing().when(service).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
               .andExpect(status().isOk());
    }
}