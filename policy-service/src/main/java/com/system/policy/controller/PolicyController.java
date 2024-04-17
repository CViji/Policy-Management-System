package com.system.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.policy.entity.Policy;
import com.system.policy.service.PolicyService;

@RestController
@RequestMapping("/api/v1.0/policy")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	@PostMapping("/register")
	public ResponseEntity<Policy> registerPolicy(@RequestBody Policy policy)
	{
		Policy newPolicy = policyService.registerPolicy(policy);
		return new ResponseEntity<>(newPolicy, HttpStatus.CREATED);
	}
	
	@GetMapping("/searches")
    public List<Policy> searchPolicies(@RequestParam(required = false) Integer numberOfYears,
                                       @RequestParam(required = false) String companyName,
                                       @RequestParam(required = false) String policyType,
                                       @RequestParam(required = false) String policyId,
                                       @RequestParam(required = false) String policyName) {
        return policyService.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName);
    }
	
	@GetMapping("/getall")
	public List<Policy> getAllPolicies()
	{
		return policyService.getAllPolicies();
	}
}
