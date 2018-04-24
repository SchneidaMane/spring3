package main.java.core.persistence.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERSCHEDULE")
public class UserSchedule {

	@Id
	@GeneratedValue
	@Column(name = "userscheduleid")
	private int userScheduleId;

	@Column(name = "userid")
	private int userId;
	
	@Column(name = "scheduleString")
	private String scheduleString;

	public int getUserScheduleId() {
		return userScheduleId;
	}

	public void setUserScheduleId(int userScheduleId) {
		this.userScheduleId = userScheduleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getScheduleString() {
		return scheduleString;
	}

	public void setScheduleString(String scheduleString) {
		this.scheduleString = scheduleString;
	}
	
	public String toString() {
		return "USERSCHEDULE[UserScheduleId: " + this.getUserScheduleId() //
				+ " UserId: " + this.getUserId() //
				+ " ScheduleString: " + this.getScheduleString() + "]"; //
	}
}
