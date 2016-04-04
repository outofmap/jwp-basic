package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class ShowUpdateAnswerFormController extends AbstractController {
	private UserDao userDao = new UserDao();
	private QuestionDao questiondao = QuestionDao.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(ShowUpdateAnswerFormController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(request.getParameter("questionId"));
		Question question = questiondao.findById(questionId);
		User writer = userDao.findByName(question.getWriter());
		if(!UserSessionUtils.isSameUser(request.getSession(), writer)){
			throw new IllegalAccessException("글쓴이가 로그인 후 수정할 수 있습니다.");
		}
		ModelAndView mav = jspView("/qna/updateQuestionForm.jsp");
		mav.addObject("question", question);
		mav.addObject("user", writer);
		return mav;
	}
}
