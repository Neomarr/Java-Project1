package furniture;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
private static Connection conn;
public static Connection connect() {
	try {
		if(conn == null || conn.isClosed()) {
			String dppath = "jdbc:ucanaccess:C:\\Users\\Neomar\\OneDrive\\Documents\\Database2.accdb";
			conn=DriverManager.getConnection(dppath);
		}
		return conn;
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}

	}
public static void disconnect() {
	try {
		if(conn!=null && !conn.isClosed()) {
			conn.close();
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
