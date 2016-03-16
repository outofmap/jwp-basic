package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/update")
public class UpdateUserDataServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(UpdateUserDataServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("userId");
		User user = DataBase.findUserById(id);
		if(req.getParameter("password") != null){
			user.setPassword(req.getParameter("password"));
			logger.debug("update password");
		}
		if(req.getParameter("name") != null){
			user.setName(req.getParameter("name"));
			logger.debug("update name");
		}
		if(req.getParameter("email") != null){
			user.setEmail(req.getParameter("email"));
			logger.debug("update mail");
		}
		logger.debug("user:",user);
		resp.sendRedirect("/user/list");
	}
	
}
