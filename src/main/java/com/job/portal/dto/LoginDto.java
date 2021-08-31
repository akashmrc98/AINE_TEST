package com.job.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto
{
	private Long id;
	private String name;
	private String userType;
	private boolean isProfileComplete;
	private String username;
}
