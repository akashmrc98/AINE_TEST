package com.job.portal.dto;

import java.util.List;

import com.job.portal.domain.Resume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterDto
{
	private String name;
	private String about;
	private String others;
	private List<String> education;
	private List<String> skills;
	private Resume resume;
}
