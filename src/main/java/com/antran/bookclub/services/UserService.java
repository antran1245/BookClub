package com.antran.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.antran.bookclub.models.LoginUser;
import com.antran.bookclub.models.User;
import com.antran.bookclub.repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
//	========================= Register/Login =============================
	
	public User register(User newUser, BindingResult result) {
		if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
			result.rejectValue("email", "Unique", "Email is already in used.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Matches", "Confirm Password does not match Password.");
		}
		if(result.hasErrors()) {
			return null;
		}
		String hashPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashPassword);
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser loginUser, BindingResult result) {
		Optional<User> optionalUser = userRepo.findByEmail(loginUser.getEmail());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			if(BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
				return user;
			}
		}
		result.rejectValue("email", "Credential", "Invalid Email/Password!");
		result.rejectValue("password", "Credential", "Invalid Email/Password!");
		return null;
	}
}
