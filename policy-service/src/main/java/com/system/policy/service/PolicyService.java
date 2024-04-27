package com.system.policy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.policy.dto.PolicyDto;
import com.system.policy.entity.Policy;
import com.system.policy.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired
	private PolicyRepository policyRepository;
	
	public Policy registerPolicy(PolicyDto policyDto) {
        // Set policy ID based on type and year
		Policy policy = new Policy();
        String policyId = generatePolicyId(policyDto.getPolicyType(), policyDto.getStartDate().getYear());
        policyDto.setPolicyId(policyId);

        // Calculate maturity amount
        double maturityAmount = calculateMaturityAmount(policyDto.getInitialDeposit(), policyDto.getDurationInYears(),
                policyDto.getTermsPerYear(), policyDto.getTermAmount(), policyDto.getInterest());
        policyDto.setMaturityAmount(maturityAmount);
        
        // Calculate end date based on duration in years
        LocalDate endDate = getEndDate(policyDto);
        policyDto.setEndDate(endDate);
        
        
        policy.setPolicyName(policyDto.getPolicyName());
        policy.setStartDate(policyDto.getStartDate());
        policy.setDurationInYears(policyDto.getDurationInYears());
        policy.setCompany(policyDto.getCompany());
        policy.setInitialDeposit(policyDto.getInitialDeposit());
        policy.setPolicyType(policyDto.getPolicyType());
        policy.setUserTypes(policyDto.getUserTypes());
        policy.setTermsPerYear(policyDto.getTermsPerYear());
        policy.setTermAmount(policyDto.getTermAmount());
        policy.setInterest(policyDto.getInterest());
        policy.setEndDate(policyDto.getEndDate());
        policy.setMaturityAmount(policyDto.getMaturityAmount());
        policy.setPolicyId(policyDto.getPolicyId());

        // Save policy to database
        return policyRepository.save(policy);
    }

	public List<Policy> searchPolicies(Integer numberOfYears, String companyName, String policyType, String policyId,
			String policyName) {
		return policyRepository.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName);
	}

	private LocalDate getEndDate(PolicyDto policy)
	{
        LocalDate date = policy.getStartDate();
        return date.plusYears(policy.getDurationInYears());
	}

    public String generatePolicyId(String policyType, int year) {
    	Policy policy = policyRepository.findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(policyType);
        if (policy == null) {
        	String policyPrefix = getPolicyNamePrefix(policyType);
            return policyPrefix + "-" + year + "-001";
        }
        
        int yearString = year;
        int lastNumericPart = 0;
        String numericPart = "";
        Pattern pattern = Pattern.compile(yearString + "-(\\d{3})");
        Matcher matcher = pattern.matcher(policy.getPolicyId());
        if (matcher.find()) {
            lastNumericPart = Integer.parseInt(matcher.group(1));
        }
        
        int nextNumericPart = lastNumericPart + 1;
        if (nextNumericPart < 10) {
            numericPart = "00" + nextNumericPart;
        } else if (nextNumericPart < 100) {
            numericPart = "0" + nextNumericPart;
        } else {
            numericPart = String.valueOf(nextNumericPart);
        }

        String prefix = getPolicyNamePrefix(policyType);
        return prefix + "-" + yearString + "-" + numericPart;
    }
    
    private String getPolicyNamePrefix(String policyType) {
        switch (policyType) {
        	case "Vehicle Insurance":
        		return "VI";
            case "Travel Insurance":
                return "TI";
            case "Health Insurance":
                return "HI";
            case "Child Plans":
                return "CP";
            case "Retirement Plans":
                return "RP";
            default:
                return "OTHER";
        }
    }

    private double calculateMaturityAmount(double initialDeposit, int durationInYears, int termsPerYear,
                                           double termAmount, double interest) {
        // Calculate maturity amount based on provided formula
        return initialDeposit + (durationInYears * termsPerYear * termAmount)
                + ((durationInYears * termsPerYear * termAmount) * (interest / 100));
    }

	public List<Policy> getAllPolicies() {
		return policyRepository.findAll();
	}

}
