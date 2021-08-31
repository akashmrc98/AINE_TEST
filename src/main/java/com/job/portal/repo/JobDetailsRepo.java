package com.job.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.JobDetails;

public interface JobDetailsRepo extends JpaRepository<JobDetails, Long> {

}
