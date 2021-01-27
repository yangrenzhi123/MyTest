import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleSelect {
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://127.0.0.1:8066/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "123456";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		//conn.setAutoCommit(false);
		conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

		try {
			t(conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}

	public static void t (Connection conn) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = conn.prepareStatement("select * from t");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString("id"));
			System.out.print("\t");
			System.out.print(rs.getString("name"));
			System.out.println("");
		}
	}
}