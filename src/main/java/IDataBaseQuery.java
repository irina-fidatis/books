import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDataBaseQuery {

	QueryResult queryAllBooks() throws SQLException;
	
	QueryResult queryBook(int id) throws SQLException;
	
	QueryResult queryAllAuthors() throws SQLException;
	
	QueryResult queryAuthor(int id) throws SQLException;
	
	QueryResult queryAuthor(String familyName) throws SQLException;
}
