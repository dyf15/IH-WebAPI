
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
 * Servlet implementation class WaitingServlet
 */
@WebServlet("/WaitingServlet")
public class WaitingServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaitingServlet()
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
		
		//倉庫IDを取得
		String repositoryID = request.getParameter("repositoryID");
		
		DBAccess db = new DBAccess();
		String sql = "select * from m_order where repository_id = '" + repositoryID + "' and inspection_start_datetime is null";
		//String sql = "select * from m_order where repository_id = '1001' and inspection_start_datetime is null";
		
		String json = JSON.encode(db.selectWaitingList(sql));
		db.DBClose();
         
		response.getWriter().append(json);
		
	}

}
