package main.java.quartz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import main.java.core.persistence.common.MachineSchedule;

@Component
public class DatabaseReaderJob implements Job {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;
	static ArrayList<MachineSchedule> rawSchedules;
	static HashMap<MachineSchedule, Boolean> machineToActivationState = new HashMap<MachineSchedule, Boolean>();

	@Autowired
	public DatabaseReaderJob() {
		// Try injecting jdbc template
		// this.jdbcTemplate = jdbcTemplate;
		// PersistenceConfig c = new PersistenceConfig();
		// this.jdbc = c.jdbcTemplate();
	}

	// TODO: Move all persistence stuff to MachineScheduleRepository and only
	// call those methods from this job.
	// TODO: Move all instance variables to a class that is written to by
	// MachineScheduleRepository

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SchedulerContext schedulerContext = null;
		try {
			schedulerContext = context.getScheduler().getContext();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// this.jdbcTemplate = (JdbcTemplate)
		// schedulerContext.get("jdbcTemplate");
		jdbcTemplate.getDataSource();
		System.out.println("Reading database");
		rawSchedules = (ArrayList<MachineSchedule>) findAll();
		/*-
		for (MachineSchedule schedule : rawSchedules) {
			System.out.println(schedule);
		}
		*/

		// processSchedules();
		// context.put("processedSchedules", getMachineToActivationState());
	}

	// public void selectDatabase(String database) {
	// jdbc.execute("use " + database + ";");
	// }

	public List<MachineSchedule> findAll() {
		return jdbcTemplate.query("select id, ip, machine, zone, schedule " + "from machineSchedule order by ip", mapRows());
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

	public static ArrayList<MachineSchedule> getRawSchedules() {
		return rawSchedules;
	}

	// Creates map of MachineSchedules to ON/OFF states for this hour
	public static void processSchedules() {
		machineToActivationState = new HashMap<MachineSchedule, Boolean>();
		for (MachineSchedule schedule : rawSchedules) {
			Calendar calendar = Calendar.getInstance();
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			// Schedule string contains all 168 hours of the week
			int currentHour = calendar.get(Calendar.HOUR_OF_DAY) + (24 * dayOfWeek);
			int nextHour = currentHour + 1;
			// Fix if currentHour is 12AM-1AM on Sunday
			// TODO: Double check this
			if (nextHour == 168) {
				nextHour = 0;
			}
			String scheduleString = schedule.getSchedule();
			boolean on = scheduleString.charAt(nextHour) == '1' ? true : false;
			boolean changed = (scheduleString.charAt(currentHour) != scheduleString.charAt(nextHour)) ? true : false;
			// Here, if it's not changed, don't put it into the map
			if (!changed) {
				continue;
			}
			machineToActivationState.put(schedule, on);
		}
	}

	public static HashMap<MachineSchedule, Boolean> getMachineToActivationState() {
		return machineToActivationState;
	}
}
