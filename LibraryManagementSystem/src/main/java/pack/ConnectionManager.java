package pack;


import java.sql.*;

public class ConnectionManager {

	private static ConnectionManager _instance = null;
	private Connection con = null;

	protected ConnectionManager() {

	}

	public void init() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		this.con = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/cybage", "root", "yoga");
	}

	public Connection getConnection() {
		return this.con;
	}

	public static ConnectionManager getInstance()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		if (_instance == null) {
			_instance = new ConnectionManager();
			_instance.init();
		}
		return _instance;
	}

}