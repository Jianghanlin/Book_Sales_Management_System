package Book_Sales_Manage_System;

import java.sql.*;

public class JDBCUtils {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String username = "root";
		String password = "jianghanlin123";
		con = DriverManager.getConnection(url, username, password);
		return con;
	}

	public static void release(Statement stat, Connection con) {
		if (stat != null) {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stat = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

	public static void release(PreparedStatement stat, Connection con) {
		if (stat != null) {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stat = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			con = null;
		}
	}

	public static void release(ResultSet rs, Statement stat, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stat = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
}
