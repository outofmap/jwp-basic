package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class AddQuestionController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(AddQuestionController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		QuestionDao questionDao = new QuestionDao(); 
		Question question = new Question(
				req.getParameter("writer"), 
				req.getParameter("title"),
				req.getParameter("contents")); 
		questionDao.addQuestion(question);
		logger.debug(question.toString());
		
		return "redirect:/qna/show.jsp";
	}

}
