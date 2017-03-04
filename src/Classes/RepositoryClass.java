/**
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * 内容		:  倉庫ログインチェックまた倉庫情報取得クラス
 */
package Classes;

import java.sql.SQLException;

import DAO.DBAccess;

public class RepositoryClass
{
	//倉庫ID
	private String repositoryID;
	
	//倉庫ログインした後のメッセージ
	private String mes;
	
	//倉庫ログイン成功かどうか
	private String status;
	
	//倉庫名
	private String repositoryName;
	
	//データベース接続
	DBAccess db = new DBAccess();
	
	
	/**
	 * ログイン可否またログインメッセージを返すメソッド
	 * @param status　データベースに存在するかどうか
	 */
	private void ReturnMesAndStatus (boolean status)
	{
		if (status == false)
		{
			this.mes = "倉庫IDを正しく入力してください";
			this.status = "0";
		}
		else
		{
			this.repositoryName = db.SearchRepositoryName("SELECT repository_name FROM m_repository WHERE repository_id = '" + repositoryID +"'");
			this.mes = "倉庫にログイン成功しました";
			this.status = "1";
		}
		
	}
	
	/**
	 * 倉庫IDがデータベースに登録されたかどうかのチェック
	 * @param sql チェックするためのSQL文
	 */
	public void Result(String sql)
	{
		try
		{
			ReturnMesAndStatus(db.SelectIsExist(sql,repositoryID));
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.mes = "サーバー側で問題を発生しました"; 
			this.status = "0";
		}
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

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getRepositoryName()
	{
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName)
	{
		this.repositoryName = repositoryName;
	}

	public String getRepositoryID()
	{
		return repositoryID;
	}

	public void setRepositoryID(String repositoryID)
	{
		this.repositoryID = repositoryID;
	}
}
