package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class CreateQuestionController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(CreateQuestionController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())){
			logger.debug(req.getSession().toString());
			logger.debug("login해");
			throw new IllegalStateException("login 후에 질문 할 수 있습니다.");
		} else{
			return "/qna/form.jsp";
		}
	}

}
