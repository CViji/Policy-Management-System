package com.system.policy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.policy.dto.PolicyDto;
import com.system.policy.entity.Policy;
import com.system.policy.service.PolicyService;

@RestController
@RequestMapping("/api/v1.0/policy")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerPolicy(@Valid @RequestBody PolicyDto policy)
	{
//		if(result.hasErrors())
//		{
//			Map<String, String> errorMap = new HashMap<>();
//			result.getFieldErrors().forEach(error -> {
//				errorMap.put(error.getField(), error.getDefaultMessage());
//			});
//			return ResponseEntity.badRequest().body("Invalid request:\n" + errorMap);
//		}
		
		Policy newPolicy = policyService.registerPolicy(policy);
//		return new ResponseEntity<>(newPolicy, HttpStatus.CREATED);
		return ResponseEntity.ok("Policy Created successfully");
	}
	
	@GetMapping("/searches")
    public List<Policy> searchPolicies(@RequestParam(required = false) Integer durationInYears,
                                       @RequestParam(required = false) String companyName,
                                       @RequestParam(required = false) String policyType,
                                       @RequestParam(required = false) String policyId,
                                       @RequestParam(required = false) String policyName) {
        return policyService.searchPolicies(durationInYears, companyName, policyType, policyId, policyName);
    }
	
	@GetMapping("/getall")
	public List<Policy> getAllPolicies()
	{
		return policyService.getAllPolicies();
	}
}
