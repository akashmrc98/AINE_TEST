package com.job.portal.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
public class JobPost
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Lob
	private String description;
	private LocalDate postedAt;
	private int[] experience;
	private int[] ctc;
	private String[] location;
	
	@OneToOne
	private JobDetails job;
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private AppUsers recuiter;
	
	@OneToMany
	private List<AppUsers> applicants;
}
