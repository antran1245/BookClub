package com.antran.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.antran.bookclub.models.Book;
import com.antran.bookclub.models.User;
import com.antran.bookclub.services.BookService;
import com.antran.bookclub.services.UserService;

@Controller
public class BookMarketController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
//	========================= Display =========================
	
	@GetMapping("/bookmarket")
	public String bookmarket(Model model, HttpSession session) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		List<Book> books = bookService.allBooks();
		model.addAttribute("books", books);
		Long id = (Long) session.getAttribute("uuid");
		User user = userService.findUser(id);
		model.addAttribute("user", user);
		return "/book/bookmarket.jsp";
	}
	
// 	========================== Action ===========================
	@PutMapping("/borrow/{id}")
	public String borrow(@PathVariable("id") Long id, HttpSession session) {
		bookService.updateBorrow(id, (Long) session.getAttribute("uuid"));
		return "redirect:/bookmarket";
	}
}
