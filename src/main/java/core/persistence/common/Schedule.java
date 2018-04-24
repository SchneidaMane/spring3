package main.java.core.persistence.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Schedule {

	@Id
	@GeneratedValue
	@Column(name = "scheduleid")
	private int scheduleId;

	@Column(name = "schedulestring")
	private String scheduleString;

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleString() {
		return scheduleString;
	}

	public void setScheduleString(String scheduleString) {
		this.scheduleString = scheduleString;
	}

	@Override
	public String toString() {
		return "SCHEDULE[Schedule Id: " + this.getScheduleId() //
				+ " Schedule String: " + this.getScheduleString() + "]"; //
	}

}
