package com.antran.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antran.bookclub.models.Book;
import com.antran.bookclub.repositories.BookRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo bookRepo;
	
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
}
