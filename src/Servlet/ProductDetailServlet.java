
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
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailServlet()
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
		
		//商品IDを取得
		String productID = request.getParameter("productID");
		
		//データベースにアクセス
		DBAccess db = new DBAccess();
		
		//商品情報取得
		String sql = "select * from product_detail where product_id = '" + productID + "'";

		String json = JSON.encode(db.selectProductDetailList(sql));
		db.DBClose();
         
		response.getWriter().append(json);
		
	}

}
