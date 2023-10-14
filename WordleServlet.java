

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.WordleGame;
import util.DBConnection;

@WebServlet("/WordleServlet")
public class WordleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WordleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String word = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			DBConnection.getDBConnection();
			connection = DBConnection.connection;

			Random r = new Random();
			int rid = r.nextInt(10);
			String selectSQL = "SELECT * FROM WORDS WHERE id = ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, rid);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				word = rs.getString("ENTRY").trim();
			}
			response.getWriter().append(word);
			rs.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se2) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
