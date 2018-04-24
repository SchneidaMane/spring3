package main.java.core.persistence.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MACHINE")
public class Machine {

	@Id
	@GeneratedValue
	@Column(name = "machineid")
	private int machineId;

	@Column(name = "machinename")
	private String machineName;

	@Column(name = "userid")
	private int userId;

	@Column(name = "region")
	private String region;

	@Column(name = "state")
	private String state;

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {
		return "MACHINE[MachineId: " + this.getMachineId() //
				+ " Name: " + this.getMachineName() //
				+ " User: " + this.getUserId() //
				+ " Region: " + this.getRegion() //
				+ " State: " + this.getState() + "]"; //
	}

}
