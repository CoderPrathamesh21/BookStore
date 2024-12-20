package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public void save(Book b) {
		bookRepo.save(b);
	}
	
	public List<Book> getAllBooks() {
		
		return bookRepo.findAll();
	}
	
	public Book getBookById(int id) {
		
		return bookRepo.findById(id).get();
	}
	
	public void deleteById(int id) {
		
		bookRepo.deleteById(id);
	}

}
