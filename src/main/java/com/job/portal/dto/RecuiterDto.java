package com.job.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecuiterDto
{
	private Long id;
	private String name;
	private String jobRole;
	private String phone;
	private String email;
}
