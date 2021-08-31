package com.job.portal.dto;

import java.time.LocalDate;

import com.job.portal.domain.Company;
import com.job.portal.domain.JobDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppliedJobDtos
{
	private Long id;
	private String title;
	private String description;
	private LocalDate postedAt;
	private int[] experience;
	private int[] ctc;
	private String[] location;
	private JobDetails job;
	private Company company;
	
}
