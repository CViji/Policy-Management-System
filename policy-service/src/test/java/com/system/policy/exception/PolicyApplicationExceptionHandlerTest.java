package com.system.policy.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ContextConfiguration(classes = {PolicyApplicationExceptionHandler.class})
@ExtendWith(SpringExtension.class)
public class PolicyApplicationExceptionHandlerTest {
	
	@Autowired
	private PolicyApplicationExceptionHandler policyApplicationExceptionHandler;
	

	@Test
	public void handleInvalidArgument() {
		MethodParameter parameter = mock(MethodParameter.class);
	    BindingResult bindingResult = mock(BindingResult.class);
	    List<FieldError> fieldErrors = Arrays.asList(
	            new FieldError("policyDto", "policyName", "Policy name is required"),
	            new FieldError("policyDto", "company", "Company name is required")
	    );
	    when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
	    MethodArgumentNotValidException methodArgumentNotValidException = 
	            new MethodArgumentNotValidException(parameter, bindingResult);
	    
	    Map<String, String> errorMap = policyApplicationExceptionHandler.handleInvalidArgument(methodArgumentNotValidException);
	    assertEquals(2, errorMap.size());
	    assertTrue(errorMap.containsKey("policyName"));
	    assertEquals("Policy name is required", errorMap.get("policyName"));
	    assertTrue(errorMap.containsKey("company"));
	    assertEquals("Company name is required", errorMap.get("company"));
	}
}
