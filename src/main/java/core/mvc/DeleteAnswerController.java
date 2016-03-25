package core.mvc;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.jfxmedia.logging.Logger;

import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String answerId = req.getParameter("answerId");
		AnswerDao answerdao = new AnswerDao();
		
		long deletedAnswerId = answerdao.deleteById(Long.parseLong(answerId));
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if(deletedAnswerId == Long.parseLong(answerId)){
			out.print(mapper.writeValueAsString(Result.ok()));
			return null;
		}
		out.print(mapper.writeValueAsString(Result.fail("SQL Exception")));
		return null;
	}
}
