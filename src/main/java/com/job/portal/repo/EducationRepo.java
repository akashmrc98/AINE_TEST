package com.job.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.Education;

public interface EducationRepo extends JpaRepository<Education, Long> {

}
