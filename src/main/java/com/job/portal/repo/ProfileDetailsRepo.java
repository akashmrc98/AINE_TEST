package com.job.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.ProfileDetails;

public interface ProfileDetailsRepo extends JpaRepository<ProfileDetails, Long> {

}
