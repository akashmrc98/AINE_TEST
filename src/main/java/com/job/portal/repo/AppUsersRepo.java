package com.job.portal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.portal.domain.AppUsers;

public interface AppUsersRepo extends JpaRepository<AppUsers, Long>
{
	List<AppUsers> findByUsername(String username);
}
