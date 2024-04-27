package com.system.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.system.user.dto.UserDto;
import com.system.user.entity.User;
import com.system.user.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    
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
        userDto.setAddress("123 Main St");
        userDto.setContactNumber("1234567890");
        userDto.setEmail("viji.c@gmail.com");
        userDto.setSalary(35);
        userDto.setPan("ABCDE1234F");
        userDto.setEmployerName("XYZ Corp");
        userDto.setPassword("password");

        User expectedUser = new User();
        expectedUser.setFirstName(userDto.getFirstName());
        expectedUser.setLastName(userDto.getLastName());
        expectedUser.setDob(userDto.getDob());
        expectedUser.setAddress(userDto.getAddress());
        expectedUser.setContactNumber(userDto.getContactNumber());
        expectedUser.setEmail(userDto.getEmail());
        expectedUser.setSalary(userDto.getSalary());
        expectedUser.setPan(userDto.getPan());
        expectedUser.setEmployerName(userDto.getEmployerName());
        expectedUser.setEmployerType(determineEmployerType(userDto.getSalary()));
        expectedUser.setPassword(userDto.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        User actualUser = userService.registerUser(userDto);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1l,"Viji", "C", LocalDate.of(1990, 1, 1), "123 Main St", "1234567890", "viji.c@gmail.com", 5, "ABCDE1234F", "XYZ Corp", "password", "User type B"));
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualUserList = userService.getAllUser();
        assertEquals(userList, actualUserList);
    }

    @Test
    public void testIsUserExists() {
        String email = "john@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);
        assertTrue(userService.isUserExists(email));
    }

    private String determineEmployerType(int income) {
        if (income <= 50000) {
            return "User type A";
        } else if (income <= 100000) {
            return "User type B";
        } else if (income <= 150000) {
            return "User type C";
        } else if (income <= 300000) {
            return "User type D";
        } else {
            return "User type E";
        }
    }
}
