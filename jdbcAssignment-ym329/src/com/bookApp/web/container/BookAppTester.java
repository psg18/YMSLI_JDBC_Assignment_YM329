package com.bookApp.web.container;

import java.util.Scanner;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bookApp.model.service.*;
import com.bookApp.model.persistance.*;

public class BookAppTester {
	
	static BookService service = new BookServiceImpl();
	
	static Scanner sc = new Scanner(System.in);

	private static void printAllBooks() {
		System.out.println("\nPrinting all Books: ");
		
		for(Book book : service.getAllBooks()) {
			System.out.println(book);
		}
	}
	
	private static void printBookById(int bookId) {
		System.out.println("\nBook for ID: "+bookId);
		Book printBook = service.getBookById(bookId);
		System.out.println(printBook);
	}
	
	private static void removeBook() {
		System.out.println("\n\nRemoving Book!");
		System.out.println("Enter book Id: ");
		int bookId = sc.nextInt();
		Book book = service.getBookById(bookId);
		service.removeBook(bookId);	
	}

	private static void updateBookPrice() {
		System.out.println("\n\nUpdating Book Price!");
		System.out.println("Enter book Id: ");
		int bookId = sc.nextInt();
		Book book = service.getBookById(bookId);
		System.out.println("Enter New Price: ");
		Double price = sc.nextDouble();
		service.updateBook(book,price);
	}

	private static void addBook() throws ParseException {
		
		System.out.println("\nEnter New Book Details: ");
		System.out.println("Enter Title: ");
		String btitle = sc.nextLine();
		System.out.println("Enter isbn: ");
		String isbn = sc.nextLine();
		System.out.println("Enter auhtor: ");
		String author = sc.nextLine();
		System.out.println("Enter Publishing Date(yyyy-mm-dd) : ");
		String dateString = sc.nextLine();
		Date date = Date.valueOf(dateString);
		System.out.println("Enter Price : ");
		Double price = sc.nextDouble();
		
		Book tempBook = new Book(isbn,btitle,author,price,date);
		
		service.addBook(tempBook);
	}
	
	public static void main(String[] args) throws ParseException {	

		printAllBooks();
		addBook();
		updateBookPrice();
		removeBook();
		printBookById(1);
		printAllBooks();

	}



}
