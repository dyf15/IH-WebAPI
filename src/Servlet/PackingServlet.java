/** 
 * 作成者		:　戴
 * 作成日		:  2016/11/23
 * 学籍番号	:  45008
 * 内容		:　梱包一覧
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBAccess;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class PackingServlet
 */
@WebServlet("/PackingServlet")
public class PackingServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PackingServlet()
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
		
		//受注IDListを取得、梱包する
		String orderList = request.getParameter("orderList");
		
		//受注IDList  replace
		orderList = orderList.replaceAll("\\[", "('");
		orderList = orderList.replaceAll("]", "')");
		
		//追加
		orderList = orderList.replaceAll("[,\\s]+","','");
		
		//データベースにアクセスJSONを返す
		String sql = "select * from packing_list where order_id in " + orderList;
		DBAccess db = new DBAccess();
		String json = JSON.encode(db.selectWaitingList(sql));
		db.DBClose();
		response.getWriter().append(json);

	}

}
