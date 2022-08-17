package com.codingdojo.projectmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.services.ProjectService;
import com.codingdojo.projectmanager.services.UserService;

@Controller
public class ProjectController {
	@Autowired
	private UserService userServ;
	@Autowired
	private ProjectService proServ;

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User currentUser = userServ.findOneUser(userId);
			model.addAttribute("currentUser", currentUser);

			List<Project> allProjects = proServ.allProjects();
			model.addAttribute("allProjects", allProjects);
			

			return "Dashboard.jsp";
		}
	}

	@GetMapping("/projects/new")
	public String addProject(HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/";
		} else {
			return "CreateProject.jsp";
		}
	}

	@PostMapping("/projects/new/submit")
	public String addProjectSubmit(HttpSession session,@Valid @ModelAttribute("project") Project project, BindingResult result) {
		Long user_id = (Long) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/";
		} else {
			if(result.hasErrors()) {
				return "CreateProject.jsp";
			} else {
				project.setTeam_lead(user_id);
				Project newPro = proServ.createProject(project);
		
				System.out.println(newPro.getId());
				System.out.println(newPro.getProject_users());
				User u = userServ.findOneUser(user_id);
				u.getProjects().add(newPro);

				userServ.updateUser(u);
				
			}
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/projects/edit/{id}")
	public String editProject(@PathVariable Long id, HttpSession session, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/";
		} else {
			Project p = proServ.findProject(id);
			model.addAttribute("project", p);
			return "EditProject.jsp";
		}
	}
	
	@PostMapping("/projects/edit/{id}/submit")
	public String submitEditProject(@PathVariable Long id, @ModelAttribute("project") Project project) {
		Project p = proServ.findProject(id);
		p = project;
		proServ.updateProject(p);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/projects/{id}")
	public String showProject(@PathVariable Long id, HttpSession session, Model model) {
		Project p = proServ.findProject(id);
		model.addAttribute("project", p);
		return "ShowProject.jsp";
	}
}
