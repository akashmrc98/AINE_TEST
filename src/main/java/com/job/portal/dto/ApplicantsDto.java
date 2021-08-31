package com.job.portal.dto;

import java.util.List;

import com.job.portal.domain.Education;
import com.job.portal.domain.Resume;
import com.job.portal.domain.Skills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantsDto
{
	private Long id;
	private String username;
	private String phone;
	private String email;
	private String name;
	private String about;
	private String others;
	private List<Education> education;
	private List<Skills> skills;
	private Resume resume;
}
