package com.codingdojo.projectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.projectmanager.models.LoginUser;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "registerErrors", "This email is already in database");
		}
		if (!newUser.getPassword().equals(newUser.getConfirmPw())) {
			result.rejectValue("confirmPw", "registerErrors", "The confirm password does not match");
		}
		if (result.hasErrors()) {
			return null;
		} else {
			String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashedPW);
			return userRepo.save(newUser);
		}
	}

	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "loginErrors", "email is not found");
		} else {
			User user = potentialUser.get();
			if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
				result.rejectValue("password", "loginErrors", "invalid password");
			}
			if (result.hasErrors()) {
				return null;
			} else {
				return user;
			}
		}
		return null;
	}

	public User findOneUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get();
		} else {
			return null;
		}
	}
}
