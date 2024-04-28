package com.system.policy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.system.policy.entity.Policy;

@SpringBootTest
public class PolicyRepositoryTest {

	@MockBean
	private PolicyRepository policyRepository;
	
	private Policy policy;
	
	@BeforeEach
	public void init()
	{
		policy = new Policy();
		policy.setPolicyName("Life Insurance Policy");
        policy.setPolicyId("OTHER-2025-003");
        policy.setPolicyType("Life Insurance");
        policy.setStartDate(LocalDate.of(2025, 1, 1));
        policy.setDurationInYears(10);
        policy.setCompany("ABC Insurance Company");
        policy.setInitialDeposit(5000.0);
        policy.setUserTypes("Individual");
        policy.setTermsPerYear(12);
        policy.setTermAmount(100.0);
        policy.setInterest(5.0);
        policy.setEndDate(LocalDate.of(2035, 1, 1));
        policy.setMaturityAmount(17600.0);
        
	}
	
	@Test
	public void  testFindFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc()
	{
		 String policyType = "Life Insurance";
	     when(policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(eq(policyType)))
	            .thenReturn(policy);

	     Policy actualPolicy = policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(policyType);

         assertEquals(policy.getPolicyId(), actualPolicy.getPolicyId());
	}
	
	@Test
	public void searchPolicies()
	{
		Integer durationInYears = 5;
	    String companyName = "ABC Insurance Company";
	    String policyType = "Life Insurance";
	    String policyId = "ABC123";
	    String policyName = "Test Policy";
	    
	    List<Policy> matchingPolicies = new ArrayList<>();
	    Policy matchingPolicy = new Policy();
	    matchingPolicy.setDurationInYears(durationInYears);
	    matchingPolicy.setCompany(companyName);
	    matchingPolicy.setPolicyType(policyType);
	    matchingPolicy.setPolicyId(policyId);
	    matchingPolicy.setPolicyName(policyName);
	    matchingPolicies.add(matchingPolicy);
	    when(policyRepository.searchPolicies(null,null,policyType,null,null)).thenReturn(matchingPolicies);
	    List<Policy> findPolicy = policyRepository.searchPolicies(null,null,policyType,null,null);
	    assertEquals(1, findPolicy.size());
	}
}
