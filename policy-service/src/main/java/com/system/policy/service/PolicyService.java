package com.system.policy.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.policy.entity.Policy;
import com.system.policy.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired
	private PolicyRepository policyRepository;
	
	public Policy registerPolicy(Policy policy) {
        // Set policy ID based on type and year
        String policyId = generatePolicyId(policy.getPolicyType(), getYear(policy.getStartDate()), policy.getPolicyName());
        policy.setPolicyId(policyId);

        // Calculate maturity amount
        double maturityAmount = calculateMaturityAmount(policy.getInitialDeposit(), policy.getDuration(),
                policy.getTermsPerYear(), policy.getTermAmount(), policy.getInterest());
        policy.setMaturityAmount(maturityAmount);

        // Save policy to database
        return policyRepository.save(policy);
    }
	
	public List<Policy> searchPolicies(Integer numberOfYears, String companyName, String policyType, String policyId, String policyName) {
        return policyRepository.searchPolicies(numberOfYears, companyName, policyType, policyId, policyName);
    }

    private String getYear(String startDate) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(startDate, formatter);
		return String.valueOf(date.getYear());
	}

    public String generatePolicyId(String policyType, String year, String policyName) {
        // Fetch the last generated policy ID for the specified policy type
        String lastPolicyId = policyRepository.findLastPolicyIdByPolicyType(policyType);
        
        if (lastPolicyId == null || lastPolicyId.isEmpty()) {
            // No existing policy found, generate the first policy ID for the year
        	StringBuilder abbreviation = new StringBuilder();
        	String[] words = policyName.split(" ");
            for (String word : words) {
                abbreviation.append(word.charAt(0));
            }
            return abbreviation.toString().toUpperCase() + "-" + year + "-001";
        }
        
        // Extract the year and the numeric part from the last policy ID
        String yearString = year;
        int lastNumericPart = 0;
        String numericPart = "";
        Pattern pattern = Pattern.compile(yearString + "-(\\d{3})");
        Matcher matcher = pattern.matcher(lastPolicyId);
        if (matcher.find()) {
            lastNumericPart = Integer.parseInt(matcher.group(1));
        }
        
        // Increment the numeric part and format it
        int nextNumericPart = lastNumericPart + 1;
        if (nextNumericPart < 10) {
            numericPart = "00" + nextNumericPart;
        } else if (nextNumericPart < 100) {
            numericPart = "0" + nextNumericPart;
        } else {
            numericPart = String.valueOf(nextNumericPart);
        }

        // Generate the next policy ID based on policy type prefix
        String prefix = getPolicyNamePrefix(policyName);
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
                // For any other policy type, you can define a default prefix or handle it accordingly
                return "OTHER";
        }
    }

    private double calculateMaturityAmount(double initialDeposit, int durationYears, int termsPerYear,
                                           double termAmount, double interest) {
        // Calculate maturity amount based on provided formula
        return initialDeposit + (durationYears * termsPerYear * termAmount)
                + ((durationYears * termsPerYear * termAmount) * (interest / 100));
    }

	public List<Policy> getAllPolicies() {
		return policyRepository.findAll();
	}

}
