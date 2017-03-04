/**
 * 作成者		:　戴
 * 学籍番号	:  45008
 * 内容		:　データベースにアクセスするためのクラス
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_PASS = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "sgkr";
	private static final String USER_NAME = "root";
	private static final String USER_PASSWORD = "";

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public DBConnection()
	{
		try
		{
			Class.forName(DRIVER_NAME).newInstance();
			con = DriverManager.getConnection(DB_PASS + DB_NAME, USER_NAME, USER_PASSWORD);
			st = con.createStatement();
		} catch (InstantiationException ex)
		{
			System.out.println("InstantiationException が発生しました" + ex.getMessage());
		} catch (IllegalAccessException ex)
		{
			System.out.println("IllegalAccessException が発生しました" + ex.getMessage());
		} catch (ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException が発生しました" + ex.getMessage());
		} catch (SQLException ex)
		{
			System.out.println("SQLException が発生しました" + ex.getMessage());
		}

	}

	/**
	 * DBクローズ
	 */
	public void DBClose() 
	{
		if (rs != null)
		{
			try
			{
				rs.close();
				rs = null;
			} catch (SQLException ex)
			{
				System.out.println("ResultSetオブジェクト切断中にSQLExceptionが発生しました" + ex.getMessage());
			}
		}
		if (st != null)
		{
			try
			{
				st.close();
			} catch (SQLException ex)
			{
				System.out.println("Statementオブジェクト切断中にSQLExceptionが発生しました" + ex.getMessage());
			}
		}
		if (ps != null)
		{
			try
			{
				ps.close();
			} catch (SQLException ex)
			{
				System.out.println("Statementオブジェクト切断中にSQLExceptionが発生しました" + ex.getMessage());
			}
		}

		if (con != null)
		{
			try
			{
				con.close();
			} catch (SQLException ex)
			{
				System.out.println(" DB接続切断中にSQLExceptionが発生しました" + ex.getMessage());
			}
		}
	}

}
