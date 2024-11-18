package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookService mybookService;
	
	@GetMapping("/")
	public String home() {
		
		return "home";
		
	}
	
	@GetMapping("/bookRegister")
	public String bookRegister() {
		
		return "bookRegister";
	}
	
	@GetMapping("/availableBooks")
	public ModelAndView getAllBooks() {
		List<Book> list = service.getAllBooks();
		
		return new ModelAndView("bookList", "book", list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/availableBooks";
	}
	
	@GetMapping("/myBooks")
	public String getMyBooks(Model model) {
		
		List<MyBookList> list = mybookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable ("id") int id) {
		
		Book b = service.getBookById(id);
		MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
		mybookService.saveMyBooks(mb);
		return "redirect:/myBooks";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		
		Book b = service.getBookById(id);
		model.addAttribute("book", b);
		return "editBook";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		
		service.deleteById(id);
		return "redirect:/availableBooks";
	}

}
