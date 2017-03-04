/****************************************
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * 内容		:　データベースの売上テーブルの売上IDを作成するクラス
 */

package Classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.DBAccess;

public class SalesCodeCreate
{

	/************************************************************
	 * 現在の日付を取得
	 ************************************************************/
	Calendar cal = Calendar.getInstance();
	// 年
	private String year = String.valueOf(cal.get(Calendar.YEAR));

	// 月
	private String month = String.valueOf(cal.get(Calendar.MONTH) + 1);

	// 日
	private String date = String.valueOf(cal.get(Calendar.DATE));

	// replace年
	private String replaceYear = ReplaceYear();

	// 今日の売上数
	private String num;

	// 売上ID
	private String salesCode;

	DBAccess db = new DBAccess();

	/**
	 * 売上フォマートについては 年 2桁 月 2桁 日 2桁 今日の売上数 4桁 計 10桁
	 */

	/**
	 * 年の末二桁取り出し
	 * 
	 * @param 今現在の年
	 * @return 年の末二桁
	 */
	public String ReplaceYear()
	{
		String _year = this.year;
		return _year.substring(2);
	}

	/**
	 * データベースで降順で並び替え
	 * 
	 * @see DBAccess
	 */

	/**
	 * データベースで並び替えたもの 降順で今日の日付のものだけを取り出す なかった場合は、最後4桁0001を作って 今日の日付と組み合わせ
	 */
	/**
	 * 今日の日付の売上あるかどうか
	 * 
	 * @return あるならtrue,なかったらfalse
	 * @throws SQLException
	 */
	private boolean SelectSalesCode() throws SQLException
	{
		String sql = "SELECT sale_id FROM m_sale where sale_id like ? ORDER BY sale_id DESC";

		// System.out.println(db.select(sql));

		System.out.println("this" + SearchSalesID());
		boolean test = db.SelectIsExist(sql, SearchSalesID() + "%");
		System.out.println("this" + test);
		return test;

	}

	/**
	 * 今日の日付の売上がある場合一番大きの売上IDを取得
	 * 
	 * @return 一番大きの売上ID、なかったらnull
	 */
	private String MaxSalesCodeGet()
	{
		String orderID = null;
		try
		{
			// 今日の日付の売上がある
			if (SelectSalesCode() == true)
			{
				String sql = "SELECT sale_id FROM m_sale where sale_id like ? ORDER BY sale_id DESC LIMIT 1;";
				ArrayList<ArrayList> list = new ArrayList<ArrayList>();
				list = db.select(sql, SearchSalesID() + "%");

				for (int i = 0; i < list.size(); i++)
				{
					ArrayList<String> order = list.get(i);
					for (String j : order)
					{
						orderID = j;
					}
				}
				return orderID;
			} else
			{
				return null;
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 売上テーブルの売上ID生成
	 * @return　売上ID
	 */
	public String CodeCreate()
	{
		String orderID = MaxSalesCodeGet();
	
		if (orderID != null)
		{
			this.num = orderID.substring(6);

			return SearchSalesID() + ParseNum(SalesNum());

		} else
		{
			this.num = "0001";
			return SearchSalesID() + this.num;
		}

	}

	/**
	 * データベースの現在の売上数を取り出し 売上数を更新
	 * 
	 * @return 更新された売上数
	 */
	public int SalesNum()
	{

		String _num = this.num;
		return Integer.parseInt(_num) + 1;
	}

	/**
	 * 更新された売上数をStringに変換
	 * 
	 * @param num
	 *            更新された売上する
	 * @return Stringに変換された売上数
	 * @see #SalesNum
	 */
	public String ParseNum(int num)
	{
		return String.format("%04d", num);
	}

	/**
	 * データベースに検索するための売上IDパターン
	 * 
	 * @return 売上IDパターン
	 */
	public String SearchSalesID()
	{
		return this.replaceYear + this.month + this.date;
	}

	/****************************************************************
	 * GetterとSetter
	 ****************************************************************/
	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getNum()
	{
		return num;
	}

	public void setNum(String num)
	{
		this.num = num;
	}

	public String getSalesCode()
	{
		return salesCode;
	}

	public void setSalesCode(String salesCode)
	{
		this.salesCode = salesCode;
	}

	public String getReplaceYear()
	{
		return replaceYear;
	}

	public void setReplaceYear(String replaceYear)
	{
		this.replaceYear = replaceYear;
	}

}
