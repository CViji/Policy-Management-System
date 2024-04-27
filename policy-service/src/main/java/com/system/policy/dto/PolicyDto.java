package com.system.policy.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PolicyDto {

    @NotBlank(message = "Policy name is required")
    private String policyName;

    @Future(message = "Policy start date should be on or after current date")
    private LocalDate startDate;
    private LocalDate endDate;
    private double maturityAmount;
    private String policyId;

    @Min(value = 1, message = "Duration must be at least 1 year")
    private int durationInYears;

    @NotBlank(message = "Company name is required")
    private String company;

    @Min(value = 1, message = "Initial deposit must be a positive number")
    private double initialDeposit;

    @NotBlank(message = "Policy type is required")
    private String policyType;

    @NotBlank(message = "User types are required")
    private String userTypes;

    @Min(value = 1, message = "Terms per year must be at least 1")
    private int termsPerYear;

    @Min(value = 1, message = "Term amount must be a positive number")
    private double termAmount;

    @Min(value = 0, message = "Interest must be a non-negative number")
    private double interest;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public int getDurationInYears() {
        return durationInYears;
    }

    public void setDurationInYears(int durationInYears) {
        this.durationInYears = durationInYears;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(String userTypes) {
        this.userTypes = userTypes;
    }

    public int getTermsPerYear() {
        return termsPerYear;
    }

    public void setTermsPerYear(int termsPerYear) {
        this.termsPerYear = termsPerYear;
    }

    public double getTermAmount() {
        return termAmount;
    }

    public void setTermAmount(double termAmount) {
        this.termAmount = termAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
