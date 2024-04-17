package com.system.policysearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.policy.entity.Policy;
import com.system.policysearch.service.PolicySearchService;

@RestController
@RequestMapping
public class PolicySearchController {

	@Autowired
	private PolicySearchService policySearchService;
	
	@GetMapping("/searches")
    public List<Policy> searchPolicies(@RequestParam(required = false) Integer numberOfYears,
                                       @RequestParam(required = false) String companyName,
                                       @RequestParam(required = false) String policyType,
                                       @RequestParam(required = false) String policyId,
                                       @RequestParam(required = false) String policyName) {
        return policySearchService.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName);
    }
}
