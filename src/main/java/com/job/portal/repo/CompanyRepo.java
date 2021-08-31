package com.job.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {
}
