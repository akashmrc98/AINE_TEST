package com.job.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.JobPost;

public interface JobPostRepo extends JpaRepository<JobPost, Long>
{
}
