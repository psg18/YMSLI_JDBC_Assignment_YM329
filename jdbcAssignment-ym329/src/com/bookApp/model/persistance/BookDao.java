package com.bookApp.model.persistance;

import java.util.*;

public interface BookDao {
	
	public List<Book> getAllBooks();
	public Book getBookById(int bookId);
	public void addBook(Book book);
	public void updateBook(Book book, double price);
	public void removeBook(int bookId);

}
