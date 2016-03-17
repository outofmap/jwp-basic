package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.UserDao;

public class ListUserController implements Controller {
	UserDao userdao = new UserDao();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
			return "redirect:/users/loginForm";
		}
		
		req.setAttribute("users", userdao.findAll());
		return "/user/list.jsp";
	}
}
