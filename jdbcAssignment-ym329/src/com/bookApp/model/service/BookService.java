package com.bookApp.model.service;

import java.util.*;

import com.bookApp.model.persistance.*;

public interface BookService {
	
	public List<Book> getAllBooks();
	public Book getBookById(int bookId);
	public void addBook(Book book);
	public void updateBook(Book book, double price);
	public void removeBook(int bookId);

}
