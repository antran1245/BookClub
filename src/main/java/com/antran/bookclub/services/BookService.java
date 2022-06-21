package com.antran.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antran.bookclub.models.Book;
import com.antran.bookclub.models.User;
import com.antran.bookclub.repositories.BookRepo;
import com.antran.bookclub.repositories.UserRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private UserRepo userRepo;
//	====================== Find Book(s) ===========================
	
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}
	public Book getBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}

//	======================== Save/Update ===========================
	
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	
	public Book updateBorrow(Long bookId, Long userId) {
		Optional<Book> optionalBook = bookRepo.findById(bookId);
		Optional<User> optionalUser = userRepo.findById(userId);
		if(optionalBook.isPresent() && optionalUser.isPresent()) {
			Book book = optionalBook.get();
			User user = optionalUser.get();
			if(book.getBorrower() != user) {
				book.setBorrower(user);				
			} else {
				book.setBorrower(null);
			}
			this.createBook(book);
			return book;
		}
		return null;
	}
	
//	======================== Delete =================================
	
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
}
