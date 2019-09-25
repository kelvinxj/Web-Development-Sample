package com.bookstore;

import java.util.List;

//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Alternative;

//@ApplicationScoped
//@Alternative
public class BookRepositoryImpl1 implements BookRepository{

	public BookRepositoryImpl1(){
		
	}
	
	@Override
	public Book lookupBookById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(String title, String description, String price,
			String pubDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(String id, String title, String description,
			String price, String pubDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBook(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> listBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksSortByPriceAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksSortByTitleAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksSortByTitleDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksSortByPriceDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksSortByPubDateDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBooksSortByPubDateAsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
