package com.system.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.user.dto.UserDto;
import com.system.user.entity.User;
import com.system.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User registerUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDob(userDto.getDob());
        user.setAddress(userDto.getAddress());
        user.setContactNumber(userDto.getContactNumber());
        user.setEmail(userDto.getEmail());
        user.setSalary(userDto.getSalary());
        user.setPan(userDto.getPan());
        user.setEmployerName(userDto.getEmployerName());
        user.setSalary(userDto.getSalary());
        user.setEmployerType(determineEmployerType(userDto.getSalary()));
        user.setPassword(userDto.getPassword());

        return userRepository.save(user);
    }

    private String determineEmployerType(int income) {
        if (income <= 5) {
            return "User type A";
        } else if (income <= 10) {
            return "User type B";
        } else if (income <= 15) {
            return "User type C";
        } else if (income <= 30) {
            return "User type D";
        } else {
            return "User type E";
        }
    }

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public boolean isUserExists(String email) {
		return userRepository.existsByEmail(email);
	}
}
