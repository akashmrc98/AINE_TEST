package com.job.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostDto
{
	private String title;
	private String description;
	private String jobRole;
	private String responsibilites;
	private int[] ctc;
	private int[] exp;
	private String[] location;
	private String[] profile;
	private String[] education;
	private String[] skills;
	private Long recuiterId;
}
