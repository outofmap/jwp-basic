package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class AddQuestionController extends AbstractController {
	 private QuestionDao questionDao = QuestionDao.getInstance();
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = UserSessionUtils.getUserFromSession(request.getSession());
		
		Question question = new Question(user.getName(), 
				request.getParameter("title"), request.getParameter("contents"));
		Question savedQuestion = questionDao.insert(question);
		return jspView("redirect:/");
	}

}
