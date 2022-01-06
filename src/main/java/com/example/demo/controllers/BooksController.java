package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dataaccess.IBookRepository;
import com.example.demo.models.Book;
import com.example.demo.models.NotFoundException;

import java.util.List;

@CrossOrigin
@RestController
public class BooksController {

	@Autowired
	private IBookRepository bookRepo;
	
	@GetMapping("/books")
	public List<Book> GetBlooks()
	{
		return  bookRepo.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Book GetBlooks(@PathVariable Integer id)
	{
		return  bookRepo.findById(id).orElseThrow(()->new NotFoundException("Book Not Fund"));
	}
	
	@PostMapping("/books")
	public Book AddBlook(@RequestBody Book book)
	{
		return bookRepo.save(book);
	}
	
	@PutMapping("/books/{id}")
	public Book UpdateBlook(@PathVariable Integer id, @RequestBody Book book)
	{
		var bookdb = bookRepo.findById(id).orElseThrow(()->new NotFoundException("Book Not Fund"));
		
		bookdb.setTitle(book.getTitle());
		bookdb.setPublishYear(book.getPublishYear());
		
		return bookRepo.save(bookdb);
	}
	
	@DeleteMapping("/books/{id}")
	public void DeleteBlook(@PathVariable Integer id)
	{
		bookRepo.findById(id).orElseThrow(()->new NotFoundException("Book Not Fund"));
		bookRepo.deleteById(id);
	}
}
