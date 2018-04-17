package main.java.core.persistence.google;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GoogleRepository {
	private JdbcTemplate jdbc;

	@Autowired
	public GoogleRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public void selectDatabase(String database) {
		jdbc.execute("use " + database + ";");
	}

	public void save(GoogleJson googleJson) {
		jdbc.update("insert into googlejson" + "(username, json) " + "values (?,?)", googleJson.getUsername(), googleJson.getJson());
	}

	public List<GoogleJson> findAllJson() {
		return jdbc.query("select id, username, json " + "from googlejson order by id", mapRowsJson());
	}

	public RowMapper<GoogleJson> mapRowsJson() {
		return new RowMapper<GoogleJson>() {
			public GoogleJson mapRow(ResultSet rs, int rowNum) throws SQLException {
				GoogleJson googleJson = new GoogleJson();
				googleJson.setId(rs.getInt(1));
				googleJson.setUsername(rs.getString(2));
				googleJson.setJson(rs.getString(3));
				return googleJson;
			}
		};
	}
}
