/****************************************
 * 作成者		:　戴
 * 学籍番号	: 45008
 * 内容		:　データベースにアクセス（取得、検索、更新）処理をまとめたクラス
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import Classes.PackingDetailGet;
import Classes.PackingListGet;
import Classes.ProductListGet;
import Classes.WaitingListGet;

public class DBAccess extends DBConnection
{

	/**
	 * 受け渡されたSQL文を用いて欲しい値を取得する
	 * 
	 * @param sql
	 *            （ResultSet） 受け渡されたSQL文
	 * @return ArrayListに結果を格納して返す
	 */
	public ArrayList<ArrayList> select(String sql)
	{
		ArrayList<ArrayList> aryTbl = new ArrayList<ArrayList>();
		try
		{
			rs = st.executeQuery(sql);
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next())
			{
				ArrayList<String> rec = new ArrayList<String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					rec.add(rs.getString(i));
				}
				aryTbl.add(rec);
			}
			rs.close();
			return aryTbl;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return aryTbl;
		} catch (NullPointerException e)
		{
			System.out.println("サーバ側で問題発生しました");
			return aryTbl;
		}
	}

	/**
	 * データベースにアクセス情報取得
	 * 
	 * @param params
	 *            パラメータ可変の時 （PreparedStatement）
	 * @return ArrayList<ArrayList>
	 */
	public ArrayList<ArrayList> select(Object... params)
	{
		ArrayList<ArrayList> aryTbl = new ArrayList<ArrayList>();
		try
		{

			ps = con.prepareStatement((String) params[0]);
			;
			for (int i = 1; i < params.length; i++)
			{
				if (params[i] instanceof String)
				{
					ps.setString(i, (String) params[i]);
				} else if (params[i] instanceof Integer)
				{
					ps.setInt(i, (int) params[i]);
				} else if (params[i] instanceof Date)
				{
					ps.setDate(i, (Date) params[i]);
				}

				// System.out.println(params[i]);
			}
			rs = ps.executeQuery();
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next())
			{
				ArrayList<String> rec = new ArrayList<String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					rec.add(rs.getString(i));
				}
				aryTbl.add(rec);
			}
			rs.close();
			return aryTbl;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return aryTbl;
		} catch (NullPointerException e)
		{
			System.out.println("サーバ側で問題発生しました");
			return aryTbl;
		}
	}

	/****************************************************************
	 * SEARCH処理
	 ***************************************************************/

	/**
	 * すでにデータベースに登録されたかどうかのチェック
	 * 
	 * @param params
	 *            最初のパラメータはsql文
	 * @return すでにあるならtrue, ないならfalse
	 * @throws SQLException
	 */
	public boolean SelectIsExist(Object... params) throws SQLException
	{

		ps = con.prepareStatement((String) params[0]);
		for (int i = 1; i < params.length; i++)
		{
			if (params[i] instanceof String)
			{
				ps.setString(i, (String) params[i]);
			} else if (params[i] instanceof Integer)
			{
				ps.setInt(i, (int) params[i]);
			} else if (params[i] instanceof Date)
			{
				ps.setDate(i, (Date) params[i]);
			}

		}

		rs = ps.executeQuery();

		return rs.next();

	}

	/**
	 * 従業員IDにより データベースに登録されたパスワード取得
	 * 
	 * @param sql
	 * @return データベースに登録されたパスワード
	 */
	public String SearchEmployeePass(String sql)
	{
		String password = null;
		try
		{
			rs = st.executeQuery(sql);
			while (rs.next())
			{
				password = rs.getString("password");

			}
			rs.close();
			return password;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return password;
		}
	}

	/**
	 * 倉庫名前を取得
	 * 
	 * @param sql
	 * @return 倉庫名
	 */
	public String SearchRepositoryName(String sql)
	{
		String repositoryName = null;
		try
		{
			rs = st.executeQuery(sql);
			while (rs.next())
			{
				repositoryName = rs.getString("repository_name");

			}
			rs.close();
			return repositoryName;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return repositoryName;
		}
	}

	/****************************************************************
	 * SELECT処理
	 ***************************************************************/

	/**
	 * 検品待ち一覧または梱包一覧
	 * 
	 * @param sql
	 * @return 受注情報
	 */
	public ArrayList<WaitingListGet> selectWaitingList(String sql)
	{
		ArrayList<WaitingListGet> WaitingList = new ArrayList<WaitingListGet>();

		WaitingListGet wl;
		try
		{
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				wl = new WaitingListGet();
				wl.setOrderID(rs.getString("order_id"));
				wl.setAddressee(rs.getString("addressee"));
				wl.setPostalCode(rs.getString("postal_code"));
				wl.setAddressFirst(rs.getString("address_first"));
				wl.setAddressEnd(rs.getString("address_end"));
				wl.setInspectionStartDatetime(rs.getString("inspection_start_datetime"));
				wl.setRepositoryID(rs.getString("repository_id"));
				WaitingList.add(wl);
			}
			rs.close();

			return WaitingList;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return WaitingList;
		}

	}

	/**
	 * 商品一覧
	 * 
	 * @param sql
	 * @return 商品情報
	 */
	public ArrayList<ProductListGet> selectProductList(String sql)
	{
		ArrayList<ProductListGet> ProductList = new ArrayList<ProductListGet>();
		ProductListGet pl;
		try
		{
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				pl = new ProductListGet();
				pl.setProductID(rs.getString("product_id"));
				pl.setProductName(rs.getString("product_name"));
				pl.setProductAllNum(rs.getString("num"));
				pl.setMaker(rs.getString("maker_id"));
				pl.setMakerName(rs.getString("name"));
				pl.setRackName(rs.getString("rack_name"));
				pl.setProductSummary(rs.getString("summary"));
				pl.setImg(rs.getString("main_image"));
				ProductList.add(pl);
			}
			rs.close();

			return ProductList;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return ProductList;
		}

	}

	/**
	 * 商品詳細情報
	 * 
	 * @param sql
	 * @return 商品詳細情報
	 */
	public ArrayList<ProductListGet> selectProductDetailList(String sql)
	{
		ArrayList<ProductListGet> ProductList = new ArrayList<ProductListGet>();

		ProductListGet pl;
		try
		{
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				pl = new ProductListGet();
				pl.setProductID(rs.getString("product_id"));
				pl.setProductName(rs.getString("product_name"));
				pl.setMaker(rs.getString("maker_id"));
				pl.setMakerName(rs.getString("name"));
				pl.setProductSummary(rs.getString("summary"));
				pl.setImg(rs.getString("main_image"));
				ProductList.add(pl);
			}
			rs.close();

			return ProductList;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return ProductList;
		}

	}

	/**
	 * 梱包一覧
	 * 
	 * @param sql
	 * @return 梱包一覧情報
	 */
	public ArrayList<PackingListGet> selectPackingList(String sql)
	{
		ArrayList<PackingListGet> PackingList = new ArrayList<PackingListGet>();

		PackingListGet pl;
		try
		{
			rs = st.executeQuery(sql);

			while (rs.next())
			{
				pl = new PackingListGet();
				pl.setOrderID(rs.getString("order_id"));
				pl.setProductID(rs.getString("product_id"));
				pl.setProductName(rs.getString("product_name"));
				pl.setOrderItemQuantity(rs.getString("order_item_quantity"));
				pl.setPostalCode(rs.getString("postal_code"));
				pl.setAddressee(rs.getString("addressee"));
				pl.setAddressFirst(rs.getString("address_first"));
				pl.setAddressEnd(rs.getString("address_end"));
				PackingList.add(pl);
			}
			rs.close();

			return PackingList;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return PackingList;
		}

	}

	/****************************************************************
	 * INSERT処理とUPDATE処理
	 ***************************************************************/
	/**
	 * 出荷テーブル 検品開始時 出荷テーブル または出荷完了日時
	 * 
	 * @param employee_id
	 *            従業員ID
	 * @param repositoryID
	 *            倉庫ID
	 * @param order_id
	 *            受注ID
	 * @param shipping_datetime
	 *            出荷完了日時 在庫更新処理
	 * @param fixing_datetime
	 *            確定日時
	 * @param order_id
	 *            受注ID 売上登録処理
	 * @return できたかどうかのMessage
	 */
	public int UpdateORInsert(Object... params)
	{
		int state = 0;
		try
		{

			ps = con.prepareStatement((String) params[0]);
			;
			con.setAutoCommit(false);
			for (int i = 1; i < params.length; i++)
			{
				if (params[i] instanceof String)
				{
					ps.setString(i, (String) params[i]);
				} else if (params[i] instanceof Integer)
				{
					ps.setInt(i, (int) params[i]);
				} else if (params[i] instanceof Date)
				{
					ps.setDate(i, (Date) params[i]);
				}

				// System.out.println(params[i]);
			}
			ps.executeUpdate();
			state = 1;

			try
			{
				con.commit();

			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				con.rollback();
				e1.printStackTrace();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		return state;
	}

}
