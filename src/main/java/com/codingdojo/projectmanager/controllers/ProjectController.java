package com.codingdojo.projectmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
