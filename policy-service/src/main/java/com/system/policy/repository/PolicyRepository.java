package com.system.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.policy.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>{

	Policy findFirstByPolicyTypeIgnoreCaseOrderByPolicyIdDesc(String policyType);
	
	@Query("SELECT p FROM Policy p " +
	           "WHERE (:durationInYears IS NULL OR p.durationInYears = :durationInYears) " +
	           "AND (:companyName IS NULL OR p.company = :companyName) " +
	           "AND (:policyType IS NULL OR p.policyType = :policyType) " +
	           "AND (:policyId IS NULL OR p.policyId = :policyId) " +
	           "AND (:policyName IS NULL OR p.policyName = :policyName)")
	List<Policy> searchPolicies(@Param("durationInYears") Integer durationInYears,
	                            @Param("companyName") String companyName,
	                            @Param("policyType") String policyType,
	                            @Param("policyId") String policyId,
	                            @Param("policyName") String policyName);

}
