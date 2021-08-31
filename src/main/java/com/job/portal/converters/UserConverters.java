package com.job.portal.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.job.portal.domain.AppUsers;
import com.job.portal.domain.JobPost;
import com.job.portal.dto.AppliedJobDtos;
import com.job.portal.dto.UserCreationDto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class UserConverters
{
	public static AppUsers userDtoToUser(UserCreationDto creationDto)
	{
		return AppUsers.builder().username(creationDto.getUsername())
		    .email(creationDto.getEmail())
		    .phone(creationDto.getPhone()).password(creationDto.getPassword())
		    .userRole(creationDto.getUserType())
		    .isCompleteProfile(false).build();
	}
	
	public static List<AppliedJobDtos> usersToAppliedJobDtos(List<JobPost> posts)
	{
		List<AppliedJobDtos> appliedJobDtos = new ArrayList<>();
		posts.forEach(p ->
		{
			AppliedJobDtos appliedDto = AppliedJobDtos
			    .builder()
			    .company(p.getCompany())
			    .ctc(p.getCtc())
			    .description(p.getDescription())
			    .experience(p.getExperience())
			    .postedAt(p.getPostedAt())
			    .id(p.getId())
			    .job(p.getJob())
			    .location(p.getLocation())
			    .title(p.getTitle())
			    .build();
			appliedJobDtos.add(appliedDto);
		});
		return appliedJobDtos;
	}
	
}
