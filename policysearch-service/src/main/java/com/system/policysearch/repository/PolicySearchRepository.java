package com.system.policysearch.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.system.policy.entity.Policy;

public interface PolicySearchRepository extends JpaRepository<Policy, Long>{
	
	@Query("SELECT p FROM Policy p " +
	           "WHERE (:numberOfYears IS NULL OR p.duration = :numberOfYears) " +
	           "AND (:companyName IS NULL OR p.company = :companyName) " +
	           "AND (:policyType IS NULL OR p.policyType = :policyType) " +
	           "AND (:policyId IS NULL OR p.policyId = :policyId) " +
	           "AND (:policyName IS NULL OR p.policyName = :policyName)")
	List<Policy> searchPolicies(@Param("numberOfYears") Integer numberOfYears,
	                            @Param("companyName") String companyName,
	                            @Param("policyType") String policyType,
	                            @Param("policyId") String policyId,
	                            @Param("policyName") String policyName);

}
