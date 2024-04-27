package com.system.policy.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "policies")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String policyName;
	private String policyId;
	private String policyType;
	private LocalDate startDate;
	private int durationInYears;
	private String company;
	private double initialDeposit;
	private String userTypes;
	private int termsPerYear;
	private double termAmount;
	private double interest;
	private LocalDate endDate;
	private double maturityAmount;
	
	public Policy() {
		super();
	}

	public Policy(Long id, String policyName, String policyId, String policyType, LocalDate startDate, int durationInYears,
			String company, double initialDeposit, String userTypes, int termsPerYear, double termAmount,
			double interest, LocalDate endDate, double maturityAmount) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.policyId = policyId;
		this.policyType = policyType;
		this.startDate = startDate;
		this.durationInYears = durationInYears;
		this.company = company;
		this.initialDeposit = initialDeposit;
		this.userTypes = userTypes;
		this.termsPerYear = termsPerYear;
		this.termAmount = termAmount;
		this.interest = interest;
		this.endDate = endDate;
		this.maturityAmount = maturityAmount;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
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

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyName=" + policyName + ", policyId=" + policyId + ", policyType="
				+ policyType + ", startDate=" + startDate + ", durationInYears=" + durationInYears + ", company="
				+ company + ", initialDeposit=" + initialDeposit + ", userTypes=" + userTypes + ", termsPerYear="
				+ termsPerYear + ", termAmount=" + termAmount + ", interest=" + interest + ", endDate=" + endDate
				+ ", maturityAmount=" + maturityAmount + "]";
	}

	

}
