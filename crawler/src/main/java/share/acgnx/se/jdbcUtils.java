package share.acgnx.se;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JdbcUtils {
	static String driverClass = null;
	static String url = null;
	static String username = null;
	static String password = null;
	static {
		// 确保 配置文件信息加载一次
		// 1.1 加载驱动 .. DriverManager 加载驱动
		// DriverManager.registerDriver(new Driver());
		// Driver d = new Driver();
		// 优化 不依赖具体jar 执行驱动类加载 反射 执行驱动类的注册(源码的静态代码块已经注册驱动了)
		try {
			driverClass = ResourceBundle.getBundle("jdbc").getString(
					"driver");
			Class.forName(driverClass);// 目标驱动类加载 static 静态代码块执行
			// 2: 读取 .properties 文件信息 专属类 ResourceBundle
			url = ResourceBundle.getBundle("jdbc").getString("url");
			username = ResourceBundle.getBundle("jdbc").getString("username");
			password = ResourceBundle.getBundle("jdbc").getString("password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("加载驱动失败");
		}

	}

	// 获取Conenction 对象
	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(url, username,
					password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e + "获取链接失败");
		}
	}

	// 回收资源 封装
	public static void close(Connection con) {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection con, Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(con);// 上面的封装 方法重载

	}

	public static void close(Connection con, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(con, st);// 上面的封装 方法重载

	}

}
