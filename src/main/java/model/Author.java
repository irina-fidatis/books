package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Author {
	
	private int id;	
	private String firstName;
	private String familyName;
	
	public static Author create(ResultSet set) throws SQLException{
		
		Author author = new Author();
		author.setId(set.getInt("id"));
		author.setFirstName(set.getString("firstname"));
		author.setFamilyName(set.getString("familyname"));
		
		return author;
	}
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFamilyName() {
		return this.familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
