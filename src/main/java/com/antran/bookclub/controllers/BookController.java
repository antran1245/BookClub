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
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping("/{id}/edit")
	public String editBook(@PathVariable("id") Long id, Model model) {
		Book book = bookService.getBook(id);
		model.addAttribute("editBook", book);
		return "/book/editBook.jsp";
	}
//	======================= Action ===========================
	@PostMapping("/create")
	public String createBook(@Valid @ModelAttribute("newBook") Book book, BindingResult result, HttpSession session) {
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
		System.out.println("here");
		bookService.deleteBook(id);
		return "redirect:/bookmarket";
	}
	@PutMapping("/{id}")
	public String edit(@Valid @ModelAttribute("editBook") Book book, BindingResult result, HttpSession session) {
		User user = userService.findUser((Long) session.getAttribute("uuid"));
		book.setUser(user);
		bookService.createBook(book);
		return "redirect:/bookmarket";
	}
}
