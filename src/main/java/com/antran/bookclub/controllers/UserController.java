package com.antran.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.antran.bookclub.models.Book;
import com.antran.bookclub.models.LoginUser;
import com.antran.bookclub.models.User;
import com.antran.bookclub.services.BookService;
import com.antran.bookclub.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
//	======================= Display ========================
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		model.addAttribute("newUser", new User());
		model.addAttribute("loginUser", new LoginUser());
		if(session.getAttribute("uuid") != null) {
			return "redirect:/dashboard";
		}
		
		return "/login/login.jsp";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		User user = userService.findUser((Long) session.getAttribute("uuid"));
		model.addAttribute("user", user);
		List<Book> books = bookService.allBooks();
		model.addAttribute("books", books);
		return "dashboard.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("uuid");
		return "redirect:/";
	}
	
//	======================= Action ============================
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		User user = userService.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "/login/login.jsp";
		}
		session.setAttribute("uuid", user.getId());
		return "redirect:/dashboard";
	}
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User user = userService.login(loginUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "/login/login.jsp";
		}
		session.setAttribute("uuid", user.getId());
		return "redirect:/dashboard";
	}
}
