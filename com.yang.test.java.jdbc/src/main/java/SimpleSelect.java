import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleSelect {
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.10:3306/t1?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "123456";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(false);
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

		t(conn);
		t(conn);
	}

	public static void t (Connection conn) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from t");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			long id = rs.getLong("id");
			long c = rs.getLong("c");
			long d = rs.getLong("d");
			System.out.print(id);
			System.out.print("，");
			System.out.print(c);
			System.out.print("，");
			System.out.println(d);
		}
	}
}