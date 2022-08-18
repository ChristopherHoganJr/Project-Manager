package com.codingdojo.projectmanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Must enter a title")
	@Size(min = 2, max = 20, message = "title must be between 2 and 20 characters")
	private String title;

	@NotEmpty(message = "Must enter a description")
	@Size(min = 2, max = 20, message = "Description must be between 2 and 20 characters")
	private String description;

	@NotNull(message = "Must enter a project due date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date project_date;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_lead")
	private User project_lead;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_projects", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> project_users;

	public Project() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getProject_date() {
		return project_date;
	}

	public void setProject_date(Date project_date) {
		this.project_date = project_date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}



	public List<User> getProject_users() {
		return project_users;
	}

	public void setProject_users(List<User> project_users) {
		this.project_users = project_users;
	}

	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}

	public User getProject_lead() {
		return project_lead;
	}

	public void setProject_lead(User project_lead) {
		this.project_lead = project_lead;
	}
}
