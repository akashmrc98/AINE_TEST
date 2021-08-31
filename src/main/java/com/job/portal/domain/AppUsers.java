package com.job.portal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUsers
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	@Column(unique = true, nullable = false)
	private String phone;
	@Column(unique = true, nullable = false)
	private String email;
	
	private String userRole;
	private boolean isCompleteProfile;
	private String password;
	
	private String jobRole;
	private String name;
	
	@Lob
	private String about;
	@Lob
	private String others;
	
	@OneToOne
	private Company company;
	
	@OneToMany
	private List<Education> eduation;
	@OneToMany
	private List<Skills> skill;
	
	@OneToOne
	private Resume resume;
	
	@ManyToMany
	private List<JobPost> posts;
}
