package com.job.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Long> {
}
