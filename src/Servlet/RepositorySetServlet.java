/** 
 * 作成者		:　dyf
 * 作成日		:  2016/11/10
 * 学籍番号	:  45008
 * **************************
 * 内容
 * 倉庫ログインチェック
 * ログアウトの場合はまだ
 */
package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.RepositoryClass;
import net.arnx.jsonic.JSON;

/**
 * Servlet implementation class RepositorySetServlet
 */
@WebServlet("/RepositorySetServlet")
public class RepositorySetServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RepositorySetServlet()
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
		
		//入力された倉庫IDを取得
		String repositoryID = request.getParameter("repositoryID");
		
		//倉庫クラスを取得
		RepositoryClass repository = new RepositoryClass();
		repository.setRepositoryID(repositoryID);
		//repository.setRepositoryID("1002");
		
		//倉庫ログイン情報取得
		String sql = "SELECT repository_id FROM m_repository WHERE repository_id = ?";
		repository.Result(sql);
		
		//JSONに変換
		String json = JSON.encode(repository);
		
		response.getWriter().append(json);
	}

}
