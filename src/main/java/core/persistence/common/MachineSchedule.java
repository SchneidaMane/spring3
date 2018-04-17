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
	@Column(name = "id")
	private int id;

	@Column(name = "ip")
	private String ip;

	@Column(name = "machine")
	private String machine;

	@Column(name = "zone")
	private String zone;

	@Column(name = "schedule")
	private String schedule;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ip is in decimal version, need to run it through Utilities.ipToLong();
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "id: " + this.getId() //
				+ " ip: " + this.getIp() //
				+ " machine: " + this.getMachine() //
				+ " zone: " + this.getZone() //
				+ " schedule: " + this.getSchedule(); //
	}
}
