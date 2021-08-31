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
public class JobViewDto
{
	private Long id;
	private String title;
	private String description;
	private LocalDate postedAt;
	private int[] ctc;
	private int[] experience;
	private Company company;
	private RecuiterDto recuiter;
	private JobDetails job;
	private String[] location;
	private int applicants;
}
