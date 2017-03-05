
package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.DateTimeGet;
import Classes.SalesCodeCreate;
import DAO.DBAccess;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class PackingDetailServlet
 */
@WebServlet("/PackingDetailServlet")
public class PackingDetailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackingDetailServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		// 文字化け対策
		response.setContentType("application/json; charset = UTF-8");
		request.setCharacterEncoding("UTF-8");

		DBAccess db = new DBAccess();

		// order_idを取得
		String orderID = request.getParameter("orderID");

		System.out.println("orderID **" + orderID);

		String flg = isNull(request.getParameter("flg"));

		// ステータスを取得1なら出荷テーブルに出荷完了日時を登録
		DateTimeGet getDateTime = new DateTimeGet();
		String dateTime = getDateTime.getFormatDate();

		// 一件の受注がチェック済むと
		if (flg.equals("OK"))
		{
			// チェックした商品IDを格納したListを取得
			String productIDList = request.getParameter("productID");

			// 検品した従業員IDを取得
			String employeeID = request.getParameter("employeeID");

			// 商品IDを格納したList大括弧を中括弧に変換
			productIDList = productIDList.replaceAll("\\[", "('");
			productIDList = productIDList.replaceAll("]", "')");
			
			//追加
			productIDList = productIDList.replaceAll("[,\\s]+","','");
			

			// Android 確認済みすると
			String shippingSQL = "select shipping_id from m_shipping where order_id = '" + orderID + "'";
		
			// 出荷ID
			String shippingID = result(shippingSQL);
			// 出荷完了日時
			String shippingSql = "update m_shipping set shipping_datetime = ? ,employee_id = ? where order_id = ?";
			db.UpdateORInsert(shippingSql, dateTime, employeeID, orderID);

			// 売り上げテーブルをinsert

			SalesCodeCreate codeCreate = new SalesCodeCreate();
			codeCreate.setSalesCode(codeCreate.CodeCreate());
			String saleCode = codeCreate.getSalesCode();

			String salePriceSql = "select total from m_order where order_id = '" + orderID + "'";
			
			// 合計を取得
			String salePrice = result(salePriceSql);
			
			//売上すでに登録されているかどうか
			String checkSaleIsExist = "select shipping_id from m_sale where shipping_id = ?";
			
			try
			{
				//登録されていない場合
				if (db.SelectIsExist(checkSaleIsExist, shippingID) != true)
				{
					// 売上insert
					String saleSql = "insert into m_sale(sale_id,shipping_id,sale_price,recorded_datetime) values(?,?,?,?)";

					db.UpdateORInsert(saleSql, saleCode, shippingID, salePrice, dateTime);

					System.out.println("売上 **" + saleCode + shippingID + salePrice + dateTime);
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				System.out.println("売り上げ登録失敗");
				e.printStackTrace();
			}

			// 在庫確定日時
			String stockSql = "update t_stock set fixing_datetime = ? where order_id = ? and product_id in "
					+ productIDList;

			db.UpdateORInsert(stockSql, dateTime, orderID);

		} else
		{
			String sql = "select * from packing_info where order_id = '" + orderID + "'";
			String json = JSON.encode(db.selectPackingList(sql));
			response.getWriter().append(json);
		}
		db.DBClose();
	}

	/**
	 * 欲しいデータベースの値を取得
	 * @param sql
	 * @return ほしい情報
	 */
	private String result(String sql)
	{
		String result = "";
		DBAccess db = new DBAccess();
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		list = db.select(sql);
		for (int i = 0; i < list.size(); i++)
		{
			ArrayList<String> item = list.get(i);
			for (String j : item)
			{
				result = j;
			}
		}
		return result;

	}

	/**
	 * nullチェック、nullなら空を返す
	 * 
	 * @param value
	 * @return nullなら空を返す
	 */
	private String isNull(String value)
	{
		if (value == null)
		{
			return "";
		} else
		{
			return value;
		}
	}

}
