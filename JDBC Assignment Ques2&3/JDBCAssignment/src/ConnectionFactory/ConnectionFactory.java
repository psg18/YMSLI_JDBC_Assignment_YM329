package ConnectionFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

 
public class ConnectionFactory {
 
    private static Connection connection=null;

    public static Connection getConnection() {
    	
    	Properties properties=new Properties();
    	
		try {
			InputStream inStream = new FileInputStream("C:\\Users\\ve00ym329\\Documents\\lab assignments\\JDBCAssignment\\src\\db.properties");
			properties.load(inStream);
			
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String url=properties.getProperty("jdbc.url");
		String driver=properties.getProperty("jdbc.driver");
		String username=properties.getProperty("jdbc.username");
		String password=properties.getProperty("jdbc.password");
		

        try {
            Class.forName(driver);
            System.out.println("driver is loaded...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}