package com.job.portal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.converters.UserConverters;
import com.job.portal.domain.AppUsers;
import com.job.portal.domain.Company;
import com.job.portal.domain.Education;
import com.job.portal.domain.Resume;
import com.job.portal.domain.Skills;
import com.job.portal.dto.AppliedJobDtos;
import com.job.portal.dto.AuthenticationDto;
import com.job.portal.dto.EmployeeRegisterDto;
import com.job.portal.dto.LoginDto;
import com.job.portal.dto.RecuiterRegistrationDto;
import com.job.portal.dto.UserCreationDto;
import com.job.portal.repo.AppUsersRepo;
import com.job.portal.repo.CompanyRepo;
import com.job.portal.repo.EducationRepo;
import com.job.portal.repo.ResumeRepo;
import com.job.portal.repo.SkillsRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService
{
	
	private final AppUsersRepo appUsersRepo;
	private final CompanyRepo companyRepo;
	private final EducationRepo educationRepo;
	private final SkillsRepo skillsRepo;
	private final ResumeRepo resumeRepo;
	
	public LoginDto login(AuthenticationDto authenticationDto)
	{
		AppUsers user = appUsersRepo.findByUsername(authenticationDto.getUsername()).get(0);
		if (user != null)
		{
			if (user.getPassword().equals(authenticationDto.getPassword()))
			{
				return LoginDto
				    .builder()
				    .id(user.getId())
				    .isProfileComplete(user.isCompleteProfile())
				    .userType(user.getUserRole())
				    .username(user.getUsername())
				    .name(user.getName())
				    .build();
			}
			return null;
		}
		return null;
	}
	
	public void createUser(UserCreationDto creationDto)
	{
		try
		{
			AppUsers user = UserConverters.userDtoToUser(creationDto);
			user.setPosts(new ArrayList<>());
			appUsersRepo.save(user);
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
		}
	}
	
	public Education createEductation(String education)
	{
		try
		{
			Education edu = Education.builder().education(education).build();
			educationRepo.save(edu);
			return edu;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return null;
		}
	}
	
	public Skills createSkill(String skill)
	{
		try
		{
			Skills sk = Skills.builder().skill(skill).build();
			skillsRepo.save(sk);
			return sk;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return null;
		}
	}
	
	public void registerRecuiter(Long id, RecuiterRegistrationDto registrationDto)
	{
		try
		{
			Company company = Company
			    .builder()
			    .company(registrationDto.getCompany())
			    .about(registrationDto.getAbout())
			    .address(registrationDto.getAddress())
			    .website(registrationDto.getWebsite())
			    .build();
			companyRepo.save(company);
			
			Optional<AppUsers> user = appUsersRepo.findById(id);
			if (user.isPresent())
			{
				user.get().setJobRole(registrationDto.getRole());
				user.get().setName(registrationDto.getName());
				user.get().setCompany(company);
				user.get().setCompleteProfile(true);
				appUsersRepo.save(user.get());
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
		}
	}
	
	public Resume uploadResume(MultipartFile file) throws IOException
	{
		try
		{
			String fileName = file.getOriginalFilename();
			byte[] data = file.getBytes();
			Resume resume = Resume
			    .builder()
			    .fileName(fileName)
			    .data(data)
			    .build();
			resumeRepo.save(resume);
			return resume;
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return null;
		}
	}
	
	public void registerEmployee(
	    Long id, EmployeeRegisterDto employeeRegisterDto
	)
	{
		try
		{
			Optional<AppUsers> user = appUsersRepo.findById(id);
			if (user.isPresent())
			{
				
				List<String> education = employeeRegisterDto.getEducation();
				List<Education> newEducation = new ArrayList<>();
				education.stream().forEach(e -> newEducation.add(createEductation(e)));
				
				List<String> skills = employeeRegisterDto.getSkills();
				List<Skills> newSkills = new ArrayList<>();
				skills.stream().forEach(s -> newSkills.add(createSkill(s)));
				
				user.get().setCompleteProfile(true);
				user.get().setEduation(newEducation);
				user.get().setSkill(newSkills);
				user.get().setAbout(employeeRegisterDto.getAbout());
				user.get().setName(employeeRegisterDto.getName());
				user.get().setOthers(employeeRegisterDto.getOthers());
				user.get().setResume(employeeRegisterDto.getResume());
				appUsersRepo.save(user.get());
			}
		}
		catch (Exception e)
		{
			log.error("has error");
			log.error(e.getMessage() + e.getCause());
		}
		
	}
	
	public List<AppliedJobDtos> getAppliedDtos(Long id)
	{
		try
		{
			Optional<AppUsers> user = appUsersRepo.findById(id);
			if (user.isPresent())
			  return UserConverters.usersToAppliedJobDtos(user.get().getPosts());
			return new ArrayList<>();
		}
		catch (Exception e)
		{
			log.error(e.getMessage() + e.getCause());
			return new ArrayList<>();
		}
	}
	
}
