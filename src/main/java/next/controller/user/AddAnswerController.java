package next.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

public class AddAnswerController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(AddAnswerController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String questionId = req.getParameter("questionId");
		logger.debug(questionId);
		Answer answer = new Answer(req.getParameter("writer"), 
				req.getParameter("contents"),
				Long.parseLong(questionId));
		AnswerDao answerdao = new AnswerDao();
		
		if(answer != null){
			Answer savaedAnswer = answerdao.insert(answer);
			ObjectMapper mapper = new ObjectMapper();
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(mapper.writeValueAsString(savaedAnswer));
		}
		return null;
	}
}
