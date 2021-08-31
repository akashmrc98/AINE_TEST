package com.job.portal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.job.portal.converters.JobConverters;
import com.job.portal.domain.AppUsers;
import com.job.portal.domain.Education;
import com.job.portal.domain.JobDetails;
import com.job.portal.domain.JobPost;
import com.job.portal.domain.ProfileDetails;
import com.job.portal.domain.Skills;
import com.job.portal.dto.ApplicantsDto;
import com.job.portal.dto.JobCatalogDto;
import com.job.portal.dto.JobPostDto;
import com.job.portal.dto.JobViewDto;
import com.job.portal.repo.AppUsersRepo;
import com.job.portal.repo.EducationRepo;
import com.job.portal.repo.JobDetailsRepo;
import com.job.portal.repo.JobPostRepo;
import com.job.portal.repo.ProfileDetailsRepo;
import com.job.portal.repo.SkillsRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService
{
	
	private final AppUsersRepo appUsersRepo;
	private final EducationRepo educationRepo;
	private final SkillsRepo skillsRepo;
	private final ProfileDetailsRepo profileDetailsRepo;
	private final JobPostRepo jobPostRepo;
	private final JobDetailsRepo jobDetailsRepo;
	private final JobConverters jobConverters;
	
	public List<Education> createEductation(String[] education)
	{
		try
		{
			List<Education> educationList = new ArrayList<>();
			for (String edu : education)
			{
				Education ed = Education.builder().education(edu).build();
				educationRepo.save(ed);
				educationList.add(ed);
			}
			return educationList;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return new ArrayList<>();
		}
	}
	
	public List<ProfileDetails> createProfileDetails(String[] profile)
	{
		try
		{
			List<ProfileDetails> profileDetailsList = new ArrayList<>();
			for (String pd : profile)
			{
				ProfileDetails pdf = ProfileDetails.builder().profilePerk(pd).build();
				profileDetailsRepo.save(pdf);
				profileDetailsList.add(pdf);
			}
			return profileDetailsList;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return new ArrayList<>();
		}
	}
	
	public List<Skills> createSkill(String[] skills)
	{
		try
		{
			List<Skills> skillsList = new ArrayList<>();
			for (String skill : skills)
			{
				Skills sk = Skills.builder().skill(skill).build();
				skillsRepo.save(sk);
				skillsList.add(sk);
			}
			return skillsList;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return new ArrayList<>();
		}
	}
	
	public void postJob(JobPostDto jobPostDto)
	{
		try
		{
			
			Optional<AppUsers> userD = appUsersRepo.findById(jobPostDto.getRecuiterId());
			if (userD.isPresent())
			{
				
				JobDetails details = JobDetails.builder()
				    .education(createEductation(jobPostDto.getEducation()))
				    .profiles(createProfileDetails(jobPostDto.getProfile()))
				    .skills(createSkill(jobPostDto.getSkills()))
				    .responsibilities(jobPostDto.getResponsibilites())
				    .role(jobPostDto.getJobRole())
				    .build();
				
				jobDetailsRepo.save(details);
				
				JobPost post = JobPost.builder().ctc(jobPostDto.getCtc())
				    .title(jobPostDto.getTitle())
				    .description(jobPostDto.getDescription())
				    .experience(jobPostDto.getExp())
				    .location(jobPostDto.getLocation())
				    .postedAt(LocalDate.now())
				    .job(details)
				    .recuiter(userD.get())
				    .company(userD.get().getCompany())
				    .applicants(new ArrayList<>())
				    .build();
				
				jobPostRepo.save(post);
				
				List<JobPost> posts = userD.get().getPosts();
				posts.add(post);
				userD.get().setPosts(posts);
				appUsersRepo.save(userD.get());
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
		}
	}
	
	public Iterable<JobCatalogDto> getJobs()
	{
		try
		{
			return jobConverters
			    .jobToJobDto(jobPostRepo.findAll());
		}
		catch (Exception e)
		{
			
			log.error(e.getMessage() + e.getCause());
			return null;
		}
	}
	
	public Iterable<JobCatalogDto> getRecuiterPostedJobs(Long id)
	{
		try
		{
			List<JobPost> posts = jobPostRepo.findAll();
			posts
			    .stream()
			    .filter(p -> p.getId().equals(id))
			    .collect(Collectors.toList());
			return jobConverters.jobToJobDto(posts);
		}
		catch (Exception e)
		{
			
			log.error(e.getMessage() + e.getCause());
			return null;
		}
	}
	
	public JobViewDto getJobById(Long id)
	{
		try
		{
			Optional<JobPost> job = jobPostRepo.findById(id);
			if (job.isPresent())
			  return jobConverters.jobToJobDto(job.get());
			return null;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return null;
		}
	}
	
	public void applyForJob(Long userId, Long postId)
	{
		try
		{
			Optional<AppUsers> applicant = appUsersRepo.findById(userId);
			Optional<JobPost> post = jobPostRepo.findById(postId);
			if (applicant.isPresent() && post.isPresent())
			{
				List<AppUsers> users = post.get().getApplicants();
				
				if (users.contains(applicant.get()))
				  throw new Error("User Already Applied for this post");
				else
				  applicant.get().getPosts()
				      .add(post.get());
				
				List<JobPost> posts = applicant.get().getPosts();
				
				if (posts.contains(post.get()))
				  throw new Error("Applicant Already Applied for this post");
				else
				  post.get().getApplicants()
				      .add(applicant.get());
				
				jobPostRepo.save(post.get());
				appUsersRepo.save(applicant.get());
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
		}
	}
	
	public List<ApplicantsDto> getApplicants(Long postId)
	{
		try
		{
			Optional<JobPost> job = jobPostRepo.findById(postId);
			if (job.isPresent())
			  return jobConverters.jobToApplicantDto(job.get());
			else
			  return new ArrayList<>();
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return new ArrayList<>();
		}
	}
	
}
