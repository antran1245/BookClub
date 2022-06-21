package com.antran.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.antran.bookclub.models.Book;
import com.antran.bookclub.models.User;
import com.antran.bookclub.services.BookService;
import com.antran.bookclub.services.UserService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;

//	======================= Display =========================
	@GetMapping("/new")
	public String newBook(Model model) {
		model.addAttribute("newBook", new Book());
		return "/book/addBook.jsp";
	}
	@GetMapping("/{id}")
	public String detailBook(@PathVariable("id")Long id, Model model) {
		Book book = bookService.getBook(id);
		if(book == null) {
			return "redirect:/dashboard";
		}
		model.addAttribute("book", book);
		
		if(book.getUser() != null) {
			User user = userService.findUser(book.getUser().getId());
			model.addAttribute("user", user);
		}
		return "/book/detailBook.jsp";
	}
//	======================= Action ===========================
	@PostMapping("/create")
	public String createBook(@Valid @ModelAttribute("newBook") Book book, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "/book/addBook.jsp";
		}
		User user = userService.findUser((Long) session.getAttribute("uuid"));
		book.setUser(user);
		bookService.createBook(book);
		return "redirect:/dashboard";
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		return "redirect:/bookmarket";
	}
}
