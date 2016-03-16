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

@WebServlet("/user/profile")
public class ProfileServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object value = session.getAttribute("user");
		if (value != null) {
			User user = (User) value;
			req.setAttribute("user", user);
			RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
			rd.forward(req, resp);

		}
	}
}
