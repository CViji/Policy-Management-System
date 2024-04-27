package com.system.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.user.dto.UserDto;
import com.system.user.entity.User;
import com.system.user.service.UserService;

@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result) {

		if(result.hasErrors())
	    {
			Map<String, String> errorMap = new HashMap<>();
	        result.getFieldErrors().forEach(error -> {
	        	errorMap.put(error.getField(), error.getDefaultMessage());
	        });
	    	return ResponseEntity.badRequest().body("Invalid request:\n" + errorMap);
	    }
		if (userService.isUserExists(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already registered");
        }
        
        userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully");
    }
	
	@GetMapping("getall")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
}
