import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application
{
	private static DataBaseHandler dbHandler;
	
	public static IDataBaseQuery GetDbQueryHandler()
	{
		return dbHandler;
	}
	
	public static void main(String[] args)
	{
		try {
			// create and/or initialize data base
			System.out.println("Creating h2 data base driver");
			dbHandler = new DataBaseHandler("org.h2.Driver");
			System.out.println("Creating h2 data base driver finished");
			
			String dbFile = "~/workspace/bookstore";
			System.out.println("Open connection to data base " + dbFile);
			dbHandler.open(dbFile, "sa", "");
			System.out.println("Open connection to data base " + dbFile + " finished");

			// start web server and REST API
			System.out.println("Starting application server");
			ConfigurableApplicationContext appContext = SpringApplication.run(RequestHandler.class, args);
			System.out.println("Starting application server finished");
			
			// wait for explicit stop from cmd line
			System.in.read();

			System.out.println("Stopping application server");
			appContext.close();
			System.out.println("Stopping application server finished");
			
			// close db connection after web server stopped
			System.out.println("Closing data base connection");
			dbHandler.close();
			dbHandler = null;
			System.out.println("Closing data base connection finished");
		}
		catch(IOException e) {
			System.out.print("Could not stop application: " + e.toString());
		}
		catch(ClassNotFoundException e) {
			System.out.print("Could not start data base driver: " + e.toString());
		}
		catch (SQLException e) {
			System.out.print("Could not start data base: " + e.toString());
		}
	}
	
	
}
