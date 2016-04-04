package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class UpdateQuestionContorller extends AbstractController {
	private QuestionDao questiondao = QuestionDao.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(UpdateQuestionContorller.class);

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long questionId = Long.parseLong(req.getParameter("questionId"));
		Question question = questiondao.findById(questionId);
		Question updateQuestion = new Question(question.getWriter(), req.getParameter("title"),
				req.getParameter("contents"));
		updateQuestion.setQuestionId(questionId);
		Question updatedQuestion = questiondao.update(updateQuestion);
		logger.debug(updatedQuestion.toString());
		return jspView("redirect:/");
	}

}
