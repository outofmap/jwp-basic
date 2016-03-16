package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet(value = { "/user/login", "/user/loginForm" })
public class LoginServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("userId");
		String pw = req.getParameter("password");
		User user = DataBase.findUserById(id);
		logger.debug(id);
		logger.debug(pw);
		if (user.getUserId().equals(id)) {
			if (user.getPassword().equals(pw)) {
				logger.debug("login succes");
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.sendRedirect("/user/login.jsp");
			} else {
				logger.debug("pw x");
				resp.sendRedirect("/user/login_failed.html");
			}
		} else {
			logger.debug("id x");
			resp.sendRedirect("/user/login_failed.html");
		}
	}

}
