package com.system.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.user.dto.UserDto;
import com.system.user.entity.User;
import com.system.user.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerSpringTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private UserDto userDto;
    
    @BeforeEach
    public void init()
    {
    	userDto = new UserDto();
    	userDto.setFirstName("Viji");
        userDto.setLastName("C");
        userDto.setDob(LocalDate.of(1990, 1, 1));
        userDto.setEmail("viji.c@gmail.com");
        userDto.setAddress("123 Main St, City, Country");
        userDto.setContactNumber("1234567890");
        userDto.setSalary(5);
        userDto.setPan("ABCDE1234F");
        userDto.setEmployerName("ABC Corporation");
        userDto.setEmployerType("Private");
        userDto.setPassword("password123");
    }
    
    @Test
    public void testRegisterUser_Success() throws Exception {
    	
        when(userService.isUserExists(userDto.getEmail())).thenReturn(false);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk()).andReturn();
        
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "User registered successfully");
    }
    
    @Test
    public void testRegisterUser_UserAlreadyRegistered() throws Exception {
    
        when(userService.isUserExists(userDto.getEmail())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isConflict()).andReturn();
        
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "User already registered");
    }


    @Test
    public void testGetAllUser() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Viji");
        user1.setLastName("C");

        User user2 = new User();
        user2.setFirstName("Jhon");
        user2.setLastName("Vikram");

        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUser()).thenReturn(users);

        mockMvc.perform(get("/api/v1.0/user/getall")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("Viji"))
                .andExpect(jsonPath("$[0].lastName").value("C"))
                .andExpect(jsonPath("$[1].firstName").value("Jhon"))
                .andExpect(jsonPath("$[1].lastName").value("Vikram"));
    }
}
