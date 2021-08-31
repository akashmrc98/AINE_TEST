package com.job.portal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.dto.ApplicantsDto;
import com.job.portal.dto.JobCatalogDto;
import com.job.portal.dto.JobPostDto;
import com.job.portal.dto.JobViewDto;
import com.job.portal.dto.PostGrabberDto;
import com.job.portal.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/jobs")
public class JobController
{
	private final JobService jobService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void postJob(@RequestBody JobPostDto jobPostDto)
	{
		jobService.postJob(jobPostDto);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<JobCatalogDto>> getJobs()
	{
		return new ResponseEntity<>(jobService.getJobs(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobViewDto> getJobByID(@PathVariable("id") Long id)
	{
		return new ResponseEntity<>(jobService.getJobById(id), HttpStatus.OK);
	}
	
	@GetMapping("/recuiter/{id}")
	public ResponseEntity<Iterable<JobCatalogDto>> getJobByRecuiterID(
	    @PathVariable("id") Long id
	)
	{
		return new ResponseEntity<>(jobService.getRecuiterPostedJobs(id), HttpStatus.OK);
	}
	
	@PostMapping("/apply")
	@ResponseStatus(code = HttpStatus.OK)
	public void applyJob(@RequestBody PostGrabberDto id)
	{
		jobService.applyForJob(id.getUserId(), id.getPostId());
	}
	
	@GetMapping("/applicants/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Iterable<ApplicantsDto>> getApplicants(
	    @PathVariable("id") Long id
	)
	{
		return new ResponseEntity<>(jobService.getApplicants(id), HttpStatus.OK);
	}
	
}
