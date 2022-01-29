package com.bookApp.model.service;

import java.util.List;

import com.bookApp.model.persistance.*;

public class BookServiceImpl implements BookService{
	
	private BookDaoImpl bookDaoImpl = null;
	
	public BookServiceImpl() {
		bookDaoImpl = new BookDaoImpl();
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDaoImpl.getAllBooks();
	}

	@Override
	public Book getBookById(int bookId) {
		return bookDaoImpl.getBookById(bookId);
	}

	@Override
	public void addBook(Book book) {
		bookDaoImpl.addBook(book); 		
	}

	@Override
	public void updateBook(Book book, double price) {
		bookDaoImpl.updateBook(book, price);		
	}

	@Override
	public void removeBook(int bookId) {
		bookDaoImpl.removeBook(bookId);
	}
	
}
