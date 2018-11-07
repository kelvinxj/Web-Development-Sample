package com.bookstore;

import java.math.BigDecimal;
import java.util.Date;

public class Book implements Cloneable{
	private String title1;
	private String description;
	private BigDecimal price;
	private Date pubDate;
	private String id;
	
	public Book(String id, String title, String description, BigDecimal price, Date pubDate){
		this.id = id;
		this.title1 = title;
		this.description = description;
		this.price = price;
		this.pubDate = pubDate;
	}
	
	public Book(){
		
	}

	public String getTitle() {
		return title1;
	}

	public void setTitle(String title) {
		this.title1 = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Book cloneMe(){
			try {
				return (Book)super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				return null;
			}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Book [title="+ this.title1 + ", description="+ this.description 
				+ ", price="+ this.price + ", pubDate="+ this.pubDate 
				+ ", id="+ this.id + "]";
	}
	
	
}
