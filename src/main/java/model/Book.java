package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {

	private int id;
	private String title;
	private Author author;
	
	public static Book create(ResultSet set) throws SQLException{
		
		Book book = new Book();
		book.setId(set.getInt("id"));
		book.setTitle(set.getString("title"));
		Author author = Author.create(set);
		author.setId(set.getInt("authors.id")); // set id explicitly, since set contains books.id == id
		book.setAuthor(author);
		
		return book;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
}
