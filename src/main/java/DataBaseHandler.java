import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHandler implements IDataBaseQuery {

	private Connection connection;
	private boolean isOpen;
	private String dbDriverName;
	
	public DataBaseHandler(String dbDriverName) {
		this.dbDriverName = dbDriverName;
	}
	
	public boolean getIsOpen() {
		return this.isOpen;
	}
	
	public void open(String filePath, String user, String password) throws ClassNotFoundException, SQLException {
		
		if(this.getIsOpen()) {
			return;
		}
		
		// create driver
		Class.forName(this.dbDriverName);
		
		this.connection = DriverManager.getConnection("jdbc:h2:" + filePath, user, password);
	}	

	public void close() throws SQLException {
		
		if(!this.getIsOpen()) {
			return;
		}
		
		this.connection.close();		
	}
	
	public QueryResult queryAllBooks() throws SQLException {
		
		String sql = "SELECT * FROM books JOIN authors ON books.author = authors.id";
		
		return this.runQuery(sql);
	}	

	public QueryResult queryBook(int id) throws SQLException {
		
		String sql = "SELECT * FROM books JOIN authors ON books.author = authors.id WHERE books.id=" + id;
		
		return this.runQuery(sql);
	}

	public QueryResult queryAllAuthors() throws SQLException {
		
		String sql = "SELECT * FROM authors";
		
		return this.runQuery(sql);
	}

	public QueryResult queryAuthor(int id) throws SQLException {
		
		String sql = "SELECT * FROM authors WHERE id=" + id;
		
		return this.runQuery(sql);
	}

	public QueryResult queryAuthor(String familyName) throws SQLException {
		
		String sql = "SELECT * FROM authors WHERE familyname='" + familyName+"'";
		
		return this.runQuery(sql);
	}
	
	private QueryResult runQuery(String sql) throws SQLException {
		
		Statement statement = this.connection.createStatement();
		
		return new QueryResult(statement.executeQuery(sql), statement);
	}	
}
