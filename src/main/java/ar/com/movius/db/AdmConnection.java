package ar.com.movius.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdmConnection {
	
	public static Connection getConnection() {		
		String host = "YOUR DB HOST HERE";
		String port = "YOUR DB PORT HERE";
		String db = "YOUR DB NAME HERE";
		String user = "YOUR DB USER HERE";
		String passwd = "YOUR DB PASSWD HERE";
		
		String url = "jdbc:mariadb://" + host + ":" + port + "/" + db + "?serverTimeZone=UTC&useSSL=false";
		
		String driverClassName = "org.mariadb.jdbc.Driver";
		
		Connection cnn;
		
		try {
			Class.forName(driverClassName);
			//url jdbc:mariadb://
			cnn = DriverManager.getConnection(url, user, passwd);
			
		} catch (Exception e) {
			cnn = null;
		}
		
		return cnn;
	}

}
