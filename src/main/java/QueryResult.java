import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryResult {

	private Statement statement;
	private ResultSet resultSet;
	
	public QueryResult(ResultSet resultSet, Statement statement) {
		this.resultSet = resultSet;
		this.statement = statement;
	}
	
	public ResultSet getResultSet() throws Exception {
		
		if(this.resultSet == null) {
			throw new Exception("QueryResult alraedy closed.");
		}
		
		return this.resultSet;
	}
	
	public void Close() throws SQLException{
		
		if(this.resultSet != null) {
			this.resultSet.close();
			this.resultSet = null;
		}
		
		if(this.statement != null) {
			this.statement.close();
			this.resultSet = null;
		}
	}
}
