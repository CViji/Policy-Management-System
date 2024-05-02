package com.system.policy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.system.policy.dto.PolicyDto;
import com.system.policy.entity.Policy;
import com.system.policy.repository.PolicyRepository;

@SpringBootTest
public class PolicyServiceTest {
	
	@MockBean
	private PolicyRepository policyRepository;
	
	@Autowired
	private PolicyService policyService;
	
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void registerPolicy()
	{
		PolicyDto policyDto = new PolicyDto();
        policyDto.setPolicyName("Life Insurance Policy");
        policyDto.setPolicyType("Life Insurance");
        policyDto.setStartDate(LocalDate.now().plusDays(1));
        policyDto.setDurationInYears(10);
        policyDto.setCompany("ABC Insurance Company");
        policyDto.setInitialDeposit(5000.0);
        policyDto.setUserTypes("Individual");
        policyDto.setTermsPerYear(12);
        policyDto.setTermAmount(100.0);
        policyDto.setInterest(5.0);
        
        Policy savedPolicy = new Policy();
        savedPolicy.setId(1l);
        savedPolicy.setPolicyName(policyDto.getPolicyName());
        savedPolicy.setPolicyType(policyDto.getPolicyType());
        savedPolicy.setStartDate(policyDto.getStartDate());
        savedPolicy.setDurationInYears(policyDto.getDurationInYears());
        savedPolicy.setCompany(policyDto.getCompany());
        savedPolicy.setInitialDeposit(policyDto.getInitialDeposit());
        savedPolicy.setUserTypes(policyDto.getUserTypes());
        savedPolicy.setTermsPerYear(policyDto.getTermsPerYear());
        savedPolicy.setTermAmount(policyDto.getTermAmount());
        savedPolicy.setInterest(policyDto.getInterest());
        savedPolicy.setEndDate(LocalDate.now().plusDays(1).plusYears(10));
        savedPolicy.setMaturityAmount(100000);
        savedPolicy.setPolicyId("OTHER-2024-001");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
//        when(policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(policyDto.getPolicyType())).thenReturn(null);

        Policy createdPolicy = policyService.registerPolicy(policyDto);

        assertEquals(policyDto.getPolicyName(), createdPolicy.getPolicyName());
        assertEquals(policyDto.getPolicyType(), createdPolicy.getPolicyType());
        assertEquals(policyDto.getStartDate(), createdPolicy.getStartDate());
        assertEquals(policyDto.getDurationInYears(), createdPolicy.getDurationInYears());
        assertEquals(policyDto.getCompany(), createdPolicy.getCompany());
        assertEquals(policyDto.getInitialDeposit(), createdPolicy.getInitialDeposit());
        assertEquals(policyDto.getUserTypes(), createdPolicy.getUserTypes());
        assertEquals(policyDto.getTermsPerYear(), createdPolicy.getTermsPerYear());
        assertEquals(policyDto.getTermAmount(), createdPolicy.getTermAmount());
        assertEquals(policyDto.getInterest(), createdPolicy.getInterest());
        assertNotNull(createdPolicy.getEndDate());
        assertNotNull(createdPolicy.getMaturityAmount());
        
        Policy policyInDatabase = new Policy();
        policyInDatabase.setPolicyId("OTHER-2024-001");
        when(policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(policyDto.getPolicyType())).thenReturn(policyInDatabase);
        
        savedPolicy.setPolicyId("OTHER-2024-002");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        Policy createdPolicy2 = policyService.registerPolicy(policyDto);
        assertEquals(savedPolicy.getPolicyId(), createdPolicy2.getPolicyId());
        
        //Test case For Retirement Plans
        policyDto.setPolicyType("Retirement Plans");
        savedPolicy.setPolicyType(policyDto.getPolicyType());
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        savedPolicy.setPolicyId("RP-2024-001");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        Policy createdPolicy3 = policyService.registerPolicy(policyDto);
        assertEquals(savedPolicy.getPolicyId(), createdPolicy3.getPolicyId());
        
        //Test case For Child Plans
        policyDto.setPolicyType("Child Plans");
        savedPolicy.setPolicyType(policyDto.getPolicyType());
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        savedPolicy.setPolicyId("CP-2024-001");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        Policy createdPolicy4 = policyService.registerPolicy(policyDto);
        assertEquals(savedPolicy.getPolicyId(), createdPolicy4.getPolicyId());
        
        //Test case For Health Insurance
        policyDto.setPolicyType("Health Insurance");
        savedPolicy.setPolicyType(policyDto.getPolicyType());
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        savedPolicy.setPolicyId("HI-2024-001");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        Policy createdPolicy5 = policyService.registerPolicy(policyDto);
        assertEquals(savedPolicy.getPolicyId(), createdPolicy5.getPolicyId());
        
        //Test case For Travel Insurance
        policyDto.setPolicyType("Travel Insurance");
        savedPolicy.setPolicyType(policyDto.getPolicyType());
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        policyInDatabase.setPolicyId("TI-2024-011");
        when(policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(policyDto.getPolicyType())).thenReturn(policyInDatabase);
        savedPolicy.setPolicyId("TI-2024-012");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        Policy createdPolicy6 = policyService.registerPolicy(policyDto);
        assertEquals(savedPolicy.getPolicyId(), createdPolicy6.getPolicyId());
        
        //Test case For Vehicle Insurance
        policyDto.setPolicyType("Vehicle Insurance");
        savedPolicy.setPolicyType(policyDto.getPolicyType());
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        policyInDatabase.setPolicyId("VI-2024-101");
        when(policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(policyDto.getPolicyType())).thenReturn(policyInDatabase);
        savedPolicy.setPolicyId("VI-2024-102");
        when(policyRepository.save(any(Policy.class))).thenReturn(savedPolicy);
        Policy createdPolicy7 = policyService.registerPolicy(policyDto);
        assertEquals(savedPolicy.getPolicyId(), createdPolicy7.getPolicyId());
	}
	
	@Test
	public void testGetAllPolicies()
	{
		List<Policy> policies = new ArrayList<>();
        policies.add(new Policy(1L, "Policy 1", "POL-2024-001", "Life Insurance", LocalDate.of(2024, 1, 1), 10, "ABC Insurance Company", 5000.0, "Individual", 12, 100.0, 5.0, LocalDate.of(2034, 1, 1), 17600.0));
        policies.add(new Policy(2L, "Policy 2", "POL-2024-002", "Health Insurance", LocalDate.of(2024, 1, 1), 5, "XYZ Insurance Company", 3000.0, "Group", 6, 150.0, 6.0, LocalDate.of(2029, 1, 1), 10000.0));
        when(policyRepository.findAll()).thenReturn(policies);
		List<Policy> actualPolicies = policyService.getAllPolicies();
		assertEquals(policies.size(), actualPolicies.size());
        assertEquals(policies.get(0), actualPolicies.get(0));
        assertEquals(policies.get(1), actualPolicies.get(1));
	}
	
	@Test
    public void searchPolicies_ReturnsListOfPolicies_WhenCalledWithParameters() {
        Integer numberOfYears = 5;
        String companyName = "ABC Insurance";
        String policyType = "Life Insurance";
        String policyId = "LI-2023-001";
        String policyName = "Policy";
        
        List<Policy> expectedPolicies = new ArrayList<>();
        expectedPolicies.add(new Policy(1L, "Life Insurance Policy", "OTHER-2024-001", "Life Insurance", LocalDate.of(2024, 4, 28), 10, "ABC Insurance Company", 5000.0, "Individual", 12, 100.0, 5.0, LocalDate.of(2034, 4, 28), 17600.0));
        expectedPolicies.add(new Policy(2L, "Life Insurance Policy", "OTHER-2024-002", "Life Insurance", LocalDate.of(2024, 4, 28), 10, "XYZ Insurance Company", 6000.0, "Individual", 12, 100.0, 5.0, LocalDate.of(2034, 4, 28), 17600.0));
        when(policyRepository.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName))
        .thenReturn(expectedPolicies);
        
        List<Policy> actualPolicies = policyService.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName);
        assertEquals(expectedPolicies, actualPolicies);
	}
}
