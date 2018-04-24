package main.java.core.persistence.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MACHINESCHEDULE")
public class MachineSchedule {
	/*
	 * public static void main(String... args) { long myLong =
	 * Utilities.ipToLong("243.200.0.210"); String myString =
	 * Utilities.longToIp(myLong); System.out.println(myLong);
	 * System.out.println(myString);
	 * 
	 * long milliseconds = System.currentTimeMillis(); SimpleDateFormat sdf =
	 * new SimpleDateFormat("MMM dd,yyyy HH:mm"); Date resultDate = new
	 * Date(milliseconds); System.out.println(sdf.format(resultDate));
	 * 
	 * Calendar calendar = Calendar.getInstance();
	 * System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
	 * Utilities.convertOoString("1000" + "0000" + "1110" + "0000" + "0000" +
	 * "0001", true);
	 * 
	 * Pattern p = Pattern.compile("^[a-z][-a-z0-9]*$"); if
	 * (p.matcher("asdf").find()) System.out.println("found");
	 * 
	 * }
	 */
	public MachineSchedule() {

	}

	@Id
	@GeneratedValue
	@Column(name = "machinescheduleid")
	private int machineScheduleId;

	@Column(name = "machineId")
	private int machineId;

	@Column(name = "scheduleId")
	private int scheduleId;

	public int getMachineScheduleId() {
		return machineScheduleId;
	}

	public void setMachineScheduleId(int machineScheduleId) {
		this.machineScheduleId = machineScheduleId;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public String toString() {
		return "MACHINESCHEDULE[MachineScheduleId: " + this.getMachineScheduleId() //
				+ " MachineId: " + this.getMachineId() //
				+ " ScheduleId: " + this.getScheduleId() + "]"; //
	}
}
