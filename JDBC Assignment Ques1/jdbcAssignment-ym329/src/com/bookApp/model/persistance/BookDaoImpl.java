package com.bookApp.model.persistance;

import java.util.*;

import com.bookApp.model.exceptions.DataAccessException;

import java.sql.*;

public class BookDaoImpl implements BookDao{
	
	private Connection con;
	
	public BookDaoImpl() {
		con = ConnectionFactory.getConnection();	
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = new ArrayList<>();
		Book tempBook = null;
		try {
			PreparedStatement pstmt =  con.prepareStatement("select * from book");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 tempBook = new Book(rs.getInt(1),
						 rs.getString(2), 
						 rs.getString(3), 
						 rs.getString(4), 
						 rs.getDouble(6),
						 rs.getDate(5));
				 
	             bookList.add(tempBook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}


	@Override
	public Book getBookById(int bookId) {
		
		Book tempBook = null;
		try {
			PreparedStatement pstmt =  con.prepareStatement("select * from book where id = ?");
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				tempBook = new Book(rs.getInt(1),
						 rs.getString(2), 
						 rs.getString(3), 
						 rs.getString(4), 
						 rs.getDouble(6),
						 rs.getDate(5));
			}
			else {
                throw new DataAccessException("book with id ="+ bookId +" is not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return tempBook;		
	}

	@Override
	public void updateBook(Book book, double price) {
		try {
          PreparedStatement pstmt=con.prepareStatement
                  ("update book set price=? where id=?");
          pstmt.setDouble(1, price);
          pstmt.setInt(2, book.getId());
          pstmt.executeUpdate();
          book.setPrice(price);

      }catch(SQLException ex) {
          ex.printStackTrace();
      }				
	}

	@Override
	public void removeBook(int bookId) {
		try {
          PreparedStatement pstmt=con.prepareStatement
                  ("delete from book where id=?");
          pstmt.setInt(1, bookId);
          pstmt.executeUpdate();

      }catch(SQLException ex) {
          ex.printStackTrace();
      }				
	}

	@Override
	public void addBook(Book book) {
		try {
          PreparedStatement pstmt=con.prepareStatement
        		  ("insert into book(isbn,title,author,pubdate,price) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
          pstmt.setString(1, book.getIsbn());
          pstmt.setString(2, book.getTitle());
          pstmt.setString(3, book.getAuthor());
          pstmt.setDate(4, book.getDate());
          pstmt.setDouble(5, book.getPrice());

          pstmt.executeUpdate();
          
          ResultSet rs = pstmt.getGeneratedKeys();
          while (rs.next()) {
        	  int id = rs.getInt(1);
        	  book.setId(id);
          }

      }catch(SQLException ex) {
          ex.printStackTrace();
      }				
	}
	
}
