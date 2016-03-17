package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.UserDao;

public class HomeController implements Controller {
	UserDao userdao = new UserDao();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setAttribute("users", userdao.findAll());
		return "index.jsp";
	}
}
