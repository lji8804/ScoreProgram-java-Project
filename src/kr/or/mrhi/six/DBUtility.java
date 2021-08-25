package kr.or.mrhi.six;


import java.io.FileReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtility {

	public static Connection getConnection()  {
		Connection connection = null;
		
		try {
			Properties properties = new Properties();
			String filePath = DBView.class.getResource("db.properties").getPath();
			filePath = URLDecoder.decode(filePath, "utf-8");
			properties.load(new FileReader(filePath));
			String driver = properties.getProperty("DRIVER");
			String url = properties.getProperty("URL");
			String userID = properties.getProperty("userID");
			String userPassword = properties.getProperty("userPassword");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userID, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
