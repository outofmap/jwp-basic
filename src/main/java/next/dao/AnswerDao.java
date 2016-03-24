package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;

public class AnswerDao {
	 public List<Answer> findAllByQuestionId(String questionId) {
	        JdbcTemplate jdbcTemplate = new JdbcTemplate();
	        String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
	                + "order by answerId DESC";

	        RowMapper<Answer> rm = new RowMapper<Answer>() {
	            @Override
	            public Answer mapRow(ResultSet rs) throws SQLException {
	                return new Answer(rs.getString("answerId"), 
	                		rs.getString("writer"), 
	                		rs.getString("contents"),
	                        rs.getString("createdDate"), 
	                        questionId);
	            }
	        };
	        return jdbcTemplate.query(sql, rm, questionId);
	    }
}
