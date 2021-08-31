package com.job.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecuiterRegistrationDto
{
	private String name;
	private String role;
	private String company;
	private String about;
	private String address;
	private String website;
}
