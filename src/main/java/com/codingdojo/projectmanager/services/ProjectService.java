package com.codingdojo.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository proRepo;

	public List<Project> allProjects() {
		return proRepo.findAll();
	}

	public Project createProject(Project p) {
		return proRepo.save(p);
	}

	public Project findProject(Long id) {
		Optional<Project> optionalProject = proRepo.findById(id);
		if (optionalProject.isPresent()) {
			return optionalProject.get();
		} else {
			return null;
		}
	}

	public void deleteProject(Long id) {
		proRepo.deleteById(id);
	}

	public Project updateProject(Project p) {
		return proRepo.save(p);
	}

}
