

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.WordleGame;

@WebServlet("/ValidatorServlet")
public class ValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ValidatorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guess = request.getParameter("guess");
		String answer = request.getParameter("answer");
		WordleGame wg = new WordleGame(answer);
		wg.guess(guess);
		response.getWriter().append(wg.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
