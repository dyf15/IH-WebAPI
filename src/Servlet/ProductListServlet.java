/** 
 * 作成者		:　戴
 * 作成日		:  2016/11/13
 * 学籍番号	:  45008
 * 内容		:　検品商品一覧
 */
package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.DateTimeGet;
import DAO.DBAccess;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductListServlet()
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

		String orderList = request.getParameter("orderList");
		//String orderList = "[10000000001,10000000002]";	
		orderList = orderList.replaceAll("\\[", "('");
		orderList = orderList.replaceAll("]", "')");
		// 追加
		orderList = orderList.replaceAll("[,\\s]+", "','");

		// 検品商品一覧情報を取得する
		String sql = "select o.order_id as order_id,`o`.`product_id` AS `product_id`,p.product_name as product_name, "
				+ "SUM(o.order_item_quantity) as num,p.main_image as main_image,p.summary as summary, "
				+ "`p`.`product_name` AS `product_name`, `m`.`maker_id` AS `maker_id`, `m`.`name` AS `name`, "
				+ "`s`.`repository_id` AS `repository_id`, `s`.`rack_name` AS `rack_name` from "
				+ "(((`order_info` `o` join `m_product` `p` on((`p`.`product_id` = `o`.`product_id`))) "
				+ "join `m_maker` `m` on((`m`.`maker_id` = `p`.`maker_id`))) "
				+ "join `t_stock` `s` on((`s`.`product_id` = `p`.`product_id`))) "
				+ "where s.repository_id = o.`repository_id` and" + " o.`order_id` in " + orderList
				+ " group by p.product_id ORDER BY `s`.`rack_name` ASC";
		// String sql = "select * from test where order_id in " + orderList;
		DBAccess db = new DBAccess();

		// 検品開始日時を登録
		String dateTimeSql = "update m_order set inspection_start_datetime = ? where order_id in " + orderList;
		DateTimeGet dateTime = new DateTimeGet();

		db.UpdateORInsert(dateTimeSql, dateTime.getFormatDate());

		// db.UpdateORInsert(dateTimeSql,"2016-11-23 08:50:23");

		String json = JSON.encode(db.selectProductList(sql));
		db.DBClose();
		response.getWriter().append(json);

		System.out.println(dateTime.getFormatDate());
		System.out.println(orderList);

	}

}
