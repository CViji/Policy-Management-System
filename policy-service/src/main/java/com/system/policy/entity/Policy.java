package com.system.policy.entity;

import javax.persistence.Column;
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

    @Column(nullable = false)
    private String policyName;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private double initialDeposit;

    @Column(nullable = false)
    private String policyType;

    @Column(nullable = false)
    private String userTypes;

    @Column(nullable = false)
    private int termsPerYear;

    @Column(nullable = false)
    private double termAmount;

    @Column(nullable = false)
    private double interest;

    @Column(nullable = false)
    private double maturityAmount;

    @Column(nullable = false)
    private String policyId;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public Policy(Long id, String policyName, String startDate, int duration, String company, double initialDeposit,
			String policyType, String userTypes, int termsPerYear, double termAmount, double interest,
			double maturityAmount, String policyId) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.startDate = startDate;
		this.duration = duration;
		this.company = company;
		this.initialDeposit = initialDeposit;
		this.policyType = policyType;
		this.userTypes = userTypes;
		this.termsPerYear = termsPerYear;
		this.termAmount = termAmount;
		this.interest = interest;
		this.maturityAmount = maturityAmount;
		this.policyId = policyId;
	}

	public Policy() {
		super();
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyName=" + policyName + ", startDate=" + startDate + ", duration=" + duration
				+ ", company=" + company + ", initialDeposit=" + initialDeposit + ", policyType=" + policyType
				+ ", userTypes=" + userTypes + ", termsPerYear=" + termsPerYear + ", termAmount=" + termAmount
				+ ", interest=" + interest + ", maturityAmount=" + maturityAmount + ", policyId=" + policyId + "]";
	}

}