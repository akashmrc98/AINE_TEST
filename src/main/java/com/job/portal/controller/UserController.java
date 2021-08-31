package com.job.portal.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.domain.Resume;
import com.job.portal.dto.AppliedJobDtos;
import com.job.portal.dto.AuthenticationDto;
import com.job.portal.dto.EmployeeRegisterDto;
import com.job.portal.dto.LoginDto;
import com.job.portal.dto.RecuiterRegistrationDto;
import com.job.portal.dto.UserCreationDto;
import com.job.portal.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1/users")
public class UserController
{
	
	private final UserService userService;
	
	@GetMapping("/applications/{id}")
	public ResponseEntity<Iterable<AppliedJobDtos>> getAppliedJobs(
	    @PathVariable("id") Long id
	)
	{
		return new ResponseEntity<>(
		    userService.getAppliedDtos(id), HttpStatus.OK
		);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@RequestBody UserCreationDto userCreationDto)
	{
		userService.createUser(userCreationDto);
	}
	
	@PostMapping("/recuiter/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerRecuiter(
	    @RequestBody RecuiterRegistrationDto recuiterRegistrationDto,
	    @PathVariable("id") Long id
	)
	{
		userService.registerRecuiter(id, recuiterRegistrationDto);
	}
	
	@PostMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerEmployee(
	    @RequestBody EmployeeRegisterDto employeeRegisterDto, @PathVariable("id") Long id
	)
	{
		userService.registerEmployee(id, employeeRegisterDto);
	}
	
	@PostMapping("/employee/{id}/resume")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Resume> uploadResume(
	    @RequestParam("file") MultipartFile file
	) throws IOException
	{
		Resume resume = userService.uploadResume(file);
		return new ResponseEntity<>(resume, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginDto> login(@RequestBody AuthenticationDto authenticationDto)
	{
		return new ResponseEntity<>(userService.login(authenticationDto), HttpStatus.CREATED);
	}
	
}
