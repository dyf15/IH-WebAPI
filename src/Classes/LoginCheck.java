/**
 * 従業員ログインチェックまた従業員情報取得クラス
 */
package Classes;

import java.sql.SQLException;

import DAO.DBAccess;

public class LoginCheck
{
	//データベースアクセス
	DBAccess db = new DBAccess();
	
	//入力された従業員ID
	private String employeeID;
	
	//入力されたパスワード
	private String password; 
	
	//データベース上の
	private String mes;
	
	//ログイン可否
	private String status; 
	
	
	/**
	 * 入力された従業員IDがデータベースに登録されているかどうか 
	 * @return　登録されているならtrue,なかったらfalse
	 */
	public boolean SeachEmployee() throws SQLException
	{
		
		String sql = "SELECT * FROM M_EMPLOYEE WHERE EMPLOYEE_ID = '" + this.employeeID + "'";
		System.out.println("データベースの従業員IDを取得" + sql);
		System.out.println("取得してきた従業員ID" + db.SelectIsExist(sql));
		return db.SelectIsExist(sql);
	}

	/**
	 *  employeeIDにより
	 *  データベースに登録されたパスワードを取り出し
	 * @return　DB上のパスワード
	 */
	public String DBPassWord()
	{
		
		String sql = "SELECT password FROM M_EMPLOYEE WHERE EMPLOYEE_ID = '" + this.employeeID + "'";
		System.out.println("データベースのパスワードを取得" + sql);
		System.out.println("取得してきたパスワード" + db.SearchEmployeePass(sql));
		return db.SearchEmployeePass(sql);
	}



	/**
	 * データベースから取り出したパスワードと入力されたパスワードを比較
	 * @return　パスワード一致しているならtrue,不一致ならfalse
	 */
	public boolean LoginStatus() 
	{
		try
		{		
			System.out.println("パスワード" + password);
			
			
			if (this.password.equals(DBPassWord()))
			{
				
				return true;
			}
			return false;
		}
		catch (NullPointerException ex)
		{
			return false;
		}
	}
	
	/**
	 * ログインした結果のメッセージを返すメソッド
	 * @return　ログインした結果
	 */
	public String sendMes()
	{
		String mes = "";
		try
		{
			if (SeachEmployee() == true)
			{
				if (LoginStatus() == true)
				{
					mes = "ログイン成功しました";
					this.status = "1";
				}
				else
				{
					mes = "パスワードが違います";
				}
				
			}
			else
			{
				mes = "従業員IDを正しく入力してください";
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mes = mes;
		return mes;
	}
	
	

	/****************************************************************
	 * GetterとSetter
	 ****************************************************************/
	public String getMes()
	{
		return mes;
	}

	public void setMes(String mes)
	{
		this.mes = mes;
	}
	public String getEmployeeID()
	{
		return employeeID;
	}

	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
}
