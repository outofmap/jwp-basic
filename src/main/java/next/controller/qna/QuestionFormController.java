package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.model.User;

public class QuestionFormController extends AbstractController {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.isLogined(request.getSession())){
			throw new IllegalStateException("로그인 사용자만 질문할 수 있습니다.");
		}
		User loginedUser = UserSessionUtils.getUserFromSession(request.getSession());
		return jspView("/qna/form.jsp").addObject("user", loginedUser);
	}

}
