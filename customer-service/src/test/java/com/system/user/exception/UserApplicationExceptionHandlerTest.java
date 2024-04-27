package com.system.user.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ContextConfiguration(classes = {UserApplicationExceptionHandler.class})
@ExtendWith(SpringExtension.class)
public class UserApplicationExceptionHandlerTest {
	
	@Autowired
	private UserApplicationExceptionHandler exceptionHandler;
	
	@Test
	public void handleInvalidArgument() {
		MethodParameter parameter = mock(MethodParameter.class);
	    BindingResult bindingResult = mock(BindingResult.class);
	    List<FieldError> fieldErrors = Arrays.asList(
	            new FieldError("userDto", "firstName", "First name is required"),
	            new FieldError("userDto", "email", "Invalid email format")
	    );
	    when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
	    MethodArgumentNotValidException methodArgumentNotValidException = 
	            new MethodArgumentNotValidException(parameter, bindingResult);
	    
	    Map<String, String> errorMap = exceptionHandler.handleInvalidArgument(methodArgumentNotValidException);
	    assertEquals(2, errorMap.size());
	    assertTrue(errorMap.containsKey("firstName"));
	    assertEquals("First name is required", errorMap.get("firstName"));
	    assertTrue(errorMap.containsKey("email"));
	    assertEquals("Invalid email format", errorMap.get("email"));
	}
	
}
