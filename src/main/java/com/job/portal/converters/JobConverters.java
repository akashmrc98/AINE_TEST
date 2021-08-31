package com.job.portal.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.job.portal.domain.AppUsers;
import com.job.portal.domain.JobPost;
import com.job.portal.dto.ApplicantsDto;
import com.job.portal.dto.JobCatalogDto;
import com.job.portal.dto.JobViewDto;
import com.job.portal.dto.RecuiterDto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class JobConverters
{
	public List<JobCatalogDto> jobToJobDto(Iterable<JobPost> posts)
	{
		List<JobCatalogDto> catalog = new ArrayList<>();
		posts.forEach(p ->
		{
			RecuiterDto recuiter = RecuiterDto
			    .builder()
			    .email(p.getRecuiter().getEmail())
			    .id(p.getRecuiter().getId())
			    .phone(p.getRecuiter().getPhone())
			    .jobRole(p.getRecuiter().getJobRole())
			    .name(p.getRecuiter().getName())
			    .build();
			
			JobCatalogDto post = JobCatalogDto
			    .builder()
			    .company(p.getCompany())
			    .id(p.getId())
			    .ctc(p.getCtc())
			    .description(p.getDescription())
			    .location(p.getLocation())
			    .experience(p.getExperience())
			    .postedAt(p.getPostedAt())
			    .title(p.getTitle())
			    .recuiter(recuiter)
			    .job(p.getJob())
			    .build();
			
			catalog.add(post);
		});
		
		Collections.reverse(catalog);
		return catalog;
	}
	
	public JobViewDto jobToJobDto(JobPost p)
	{
		RecuiterDto recuiter = RecuiterDto
		    .builder()
		    .email(p.getRecuiter().getEmail())
		    .id(p.getRecuiter().getId())
		    .phone(p.getRecuiter().getPhone())
		    .jobRole(p.getRecuiter().getJobRole())
		    .name(p.getRecuiter().getName())
		    .build();
		
		return JobViewDto
		    .builder()
		    .company(p.getCompany())
		    .id(p.getId())
		    .applicants(p.getApplicants().size())
		    .ctc(p.getCtc())
		    .description(p.getDescription())
		    .location(p.getLocation())
		    .experience(p.getExperience())
		    .postedAt(p.getPostedAt())
		    .title(p.getTitle())
		    .recuiter(recuiter)
		    .job(p.getJob())
		    .build();
	}
	
	public List<ApplicantsDto> jobToApplicantDto(JobPost posts)
	{
		List<ApplicantsDto> applicants = new ArrayList<>();
		List<AppUsers> users = posts.getApplicants();
		users.forEach(user ->
		{
			ApplicantsDto applicant = ApplicantsDto
			    .builder()
			    .about(user.getAbout())
			    .education(user.getEduation())
			    .id(user.getId())
			    .email(user.getEmail())
			    .name(user.getName())
			    .others(user.getOthers())
			    .phone(user.getPhone())
			    .resume(user.getResume())
			    .skills(user.getSkill())
			    .username(user.getUsername())
			    .build();
			
			applicants.add(applicant);
		});
		
		Collections.reverse(applicants);
		return applicants;
	}
	
}
