package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

public class ShowQnAController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		String questionId = req.getParameter("questionId");
		req.setAttribute("question",questionDao.findByQuestionId(questionId));
		req.setAttribute("answers", answerDao.findAllByQuestionId(questionId));
		return "/qna/show.jsp";
	}

}
