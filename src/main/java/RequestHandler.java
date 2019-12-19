import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Author;
import model.Book;

@RestController
@EnableAutoConfiguration
@RequestMapping("/library")
public class RequestHandler {
	
	@GetMapping(value = { "/book", "/bookById" } )
	public List<Book> book(@RequestParam(value="id", defaultValue = "all") String bookId) {
		QueryResult qr = null;
		List<Book> books = new ArrayList<Book>();
		
		try {		
			if("all".equals(bookId)) {
				qr = Application.GetDbQueryHandler().queryAllBooks();
			}
			else {
				qr = Application.GetDbQueryHandler().queryBook(Integer.parseInt(bookId));
			}		
			
			while(qr.getResultSet().next()) {
				books.add(Book.create(qr.getResultSet()));
			}
			
			return books;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return books;
		}
		finally {
			if(qr != null) {
				try {
					qr.Close();						
				}
				catch (SQLException e) {
				}
			}
		}
	}

	@GetMapping(value = { "/author", "/authorById" })
	public List<Author> authorById(@RequestParam(value="id") String authorId) {
		QueryResult qr = null;
		List<Author> authors = new ArrayList<Author>();
		
		try {		
			if("all".equals(authorId)) {
				qr = Application.GetDbQueryHandler().queryAllAuthors();
			}
			else {
				qr = Application.GetDbQueryHandler().queryAuthor(Integer.parseInt(authorId));
			}		
			
			while(qr.getResultSet().next()) {
				authors.add(Author.create(qr.getResultSet()));
			}
			
			return authors;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return authors;
		}
		finally {
			if(qr != null) {
				try {
					qr.Close();						
				}
				catch (SQLException e) {
				}
			}
		}
	}
	
	@GetMapping("/authorByName")
	public List<Author> authorByName(@RequestParam(value="familyName") String familyName) {
		QueryResult qr = null;
		List<Author> authors = new ArrayList<Author>();
		
		try {		
			qr = Application.GetDbQueryHandler().queryAuthor(familyName);
						
			while(qr.getResultSet().next()) {
				authors.add(Author.create(qr.getResultSet()));
			}
			
			return authors;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return authors;
		}
		finally {
			if(qr != null) {
				try {
					qr.Close();						
				}
				catch (SQLException e) {
				}
			}
		}
	}	
	
}
