package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;
import next.model.User;

public class QuestionDao {

	public List<Question> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getString("questionId"), rs.getString("writer"), rs.getString("title"),
						rs.getString("createdDate"), rs.getInt("countOfAnswer"));
			}
		};
		return jdbcTemplate.query(sql, rm);
	}

	public Question findByQuestionId(String questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS"
				+ " WHERE questionId = ?";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getString("questionId"), rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"), rs.getString("createdDate"), rs.getInt("countOfAnswer"));
			}
		};
		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

	public void addQuestion(Question question) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, 
				question.getWriter(), 
				question.getTitle(), 
				question.getContents(),
				0);
	}
}
