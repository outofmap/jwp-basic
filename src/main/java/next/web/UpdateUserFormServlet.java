package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/update")
public class UpdateUserFormServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UpdateUserFormServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("userId");
		System.out.println("id:"+id);
		User user = DataBase.findUserById(id);
		//body에 전달할 attribute를 set
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
		rd.forward(req, resp);
	}
}
