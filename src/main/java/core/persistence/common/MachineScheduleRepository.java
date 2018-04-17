package main.java.core.persistence.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MachineScheduleRepository {
	private JdbcTemplate jdbc;

	@Autowired
	public MachineScheduleRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public void selectDatabase(String database) {
		jdbc.execute("use " + database + ";");
	}

	public void save(MachineSchedule machineSchedule) {
		jdbc.update("insert into machineschedule" + "(ip, machine, zone, schedule) " + "values (?, ?, ?, ?)", machineSchedule.getIp(), machineSchedule.getMachine(), machineSchedule.getZone(), machineSchedule.getSchedule());
	}

	public List<MachineSchedule> findAll() {
		return jdbc.query("select id, ip, machine, zone, schedule " + "from machineSchedule order by ip", mapRows());
	}

	public RowMapper<MachineSchedule> mapRows() {
		return new RowMapper<MachineSchedule>() {
			public MachineSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				MachineSchedule machineSchedule = new MachineSchedule();
				machineSchedule.setId(rs.getInt(1));
				machineSchedule.setIp(rs.getString(2));
				machineSchedule.setMachine(rs.getString(3));
				machineSchedule.setZone(rs.getString(4));
				machineSchedule.setSchedule(rs.getString(5));
				return machineSchedule;
			}
		};
	}
}