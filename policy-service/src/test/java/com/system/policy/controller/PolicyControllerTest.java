package com.system.policy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.policy.dto.PolicyDto;
import com.system.policy.entity.Policy;
import com.system.policy.service.PolicyService;

@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {
	
	@MockBean
	private PolicyService policyService;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private PolicyDto policyDto;
	
	@BeforeEach
	public void init()
	{
		policyDto = new PolicyDto();
		policyDto.setPolicyName("Life Insurance Policy");
		policyDto.setPolicyType("Vehicle Insurance");
		policyDto.setStartDate(LocalDate.now().plusDays(1));
		policyDto.setDurationInYears(5);
		policyDto.setCompany("ABC Company");
		policyDto.setInitialDeposit(12345);
		policyDto.setUserTypes("User Type A");
		policyDto.setTermsPerYear(1);
		policyDto.setTermAmount(12000);
		policyDto.setInterest(12);
	}
	
	@Test
	public void testRegisterPolicy_Success() throws Exception
	{
		Policy policy = new Policy(3L,"Life Insurance Policy","OTHER-2025-003","Life Insurance",
                LocalDate.of(2025, 1, 1),10,"ABC Insurance Company", 5000.0, "Individual", 12, 100.0, 5.0,LocalDate.of(2035, 1, 1), 17600.0);
		when(policyService.registerPolicy(policyDto)).thenReturn(policy);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/policy/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(policyDto)))
                .andExpect(status().isOk()).andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Policy Created successfully");
	}
	
	public List<Policy> mockPolicyData()
	{
		List<Policy> policies = new ArrayList<>();
		Policy policy = new Policy();
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
        policies.add(policy);
        return policies;
	}
	
	@Test
	public void testGetAll_Policies() throws Exception
	{
		List<Policy> policies = mockPolicyData();
        when(policyService.getAllPolicies()).thenReturn(policies);
		mockMvc.perform(get("/api/v1.0/policy/getall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].policyName").value("Life Insurance Policy"))
                .andExpect(jsonPath("$[0].policyId").value("OTHER-2025-003"))
                .andExpect(jsonPath("$[0].policyType").value("Life Insurance"))
                .andExpect(jsonPath("$[0].startDate").value("2025-01-01"))
                .andExpect(jsonPath("$[0].durationInYears").value(10))
                .andExpect(jsonPath("$[0].company").value("ABC Insurance Company"))
                .andExpect(jsonPath("$[0].initialDeposit").value(5000.0))
                .andExpect(jsonPath("$[0].userTypes").value("Individual"))
                .andExpect(jsonPath("$[0].termsPerYear").value(12))
                .andExpect(jsonPath("$[0].termAmount").value(100.0))
                .andExpect(jsonPath("$[0].interest").value(5.0))
                .andExpect(jsonPath("$[0].endDate").value("2035-01-01"))
                .andExpect(jsonPath("$[0].maturityAmount").value(17600.0))
				.andReturn();
	}
	
	@Test
	public void testSearchPolicies() throws Exception
	{
		List<Policy> policies = mockPolicyData();
		when(policyService.searchPolicies(null,null,"Life Insurance",null,null)).thenReturn(policies);
		mockMvc.perform(get("/api/v1.0/policy/searches?policyType=Life Insurance")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].policyName").value("Life Insurance Policy"))
                .andExpect(jsonPath("$[0].policyId").value("OTHER-2025-003"))
                .andExpect(jsonPath("$[0].policyType").value("Life Insurance"))
                .andExpect(jsonPath("$[0].startDate").value("2025-01-01"))
                .andExpect(jsonPath("$[0].durationInYears").value(10))
                .andExpect(jsonPath("$[0].company").value("ABC Insurance Company"))
                .andExpect(jsonPath("$[0].initialDeposit").value(5000.0))
                .andExpect(jsonPath("$[0].userTypes").value("Individual"))
                .andExpect(jsonPath("$[0].termsPerYear").value(12))
                .andExpect(jsonPath("$[0].termAmount").value(100.0))
                .andExpect(jsonPath("$[0].interest").value(5.0))
                .andExpect(jsonPath("$[0].endDate").value("2035-01-01"))
                .andExpect(jsonPath("$[0].maturityAmount").value(17600.0));
	}
}
