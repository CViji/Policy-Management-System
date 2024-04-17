package com.system.policysearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.system.policy.entity.Policy;
import com.system.policysearch.repository.PolicySearchRepository;

@Service
public class PolicySearchService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private PolicySearchRepository policySearchRepository;

	public List<Policy> searchPolicies(Integer numberOfYears, String companyName, String policyType, String policyId, String policyName) {
		
//		String url = "http://localhost:3002/api/v1.0/policy/searches";
		String url = "http://POLICY-SERVICE/api/v1.0/policy/searches";
		ParameterizedTypeReference<List<Policy>> responseType = new ParameterizedTypeReference<List<Policy>>() {};
        ResponseEntity<List<Policy>> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            responseType
        );
        List<Policy> policies = responseEntity.getBody();
        return policies;
//		List<Policy> policy = restTemplate.getForObject("", Policy.class);
//		return policySearchRepository.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName);
        
	}
}
