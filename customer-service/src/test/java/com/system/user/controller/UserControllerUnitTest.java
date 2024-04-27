package com.system.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.system.user.dto.UserDto;
import com.system.user.entity.User;
import com.system.user.service.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerUnitTest {

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() {

    	UserDto userDto = new UserDto();
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
        
        when(userService.isUserExists(userDto.getEmail())).thenReturn(false);
        when(userService.registerUser(userDto)).thenReturn(null);
        ResponseEntity<?> response = userController.registerUser(userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
        
        when(userService.isUserExists(userDto.getEmail())).thenReturn(true);
        when(userService.registerUser(userDto)).thenReturn(null);
        ResponseEntity<?> response2 = userController.registerUser(userDto);
        assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
        assertEquals("User already registered", response2.getBody());
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Viji", "C", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890", "viji.c@gmail.com", 5, "ABCDE1234F", "XYZ Corp", "password", "User type A"));

        when(userService.getAllUser()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUser();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
}
