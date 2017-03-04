/**
 * 作成者		:　戴
 * 作成日		:  2016/11/20
 * 学籍番号	:  45008
 * 内容		:　従業員ログインチェック
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.LoginCheck;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
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
		
		//従業員ID取得
		String employeeID = request.getParameter("employeeID");
		
		//パスワード取得
		String password = request.getParameter("password");
		
		//テスト
		System.out.println( "従業員ID" + employeeID);
		System.out.println( "パスワード" + password);
		
		try
		{
			//ログインチェッククラスを呼び出す
			LoginCheck login = new LoginCheck();
			login.setEmployeeID(employeeID);
			login.setPassword(password);
			login.sendMes();
			System.out.println(login.sendMes());
			
			
			//JSON形式でログイン結果を格納
			String json = JSON.encode(login);
			
			response.getWriter().append(json);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
