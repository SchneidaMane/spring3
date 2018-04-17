package main.java.core.persistence.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATECHANGETHISHOUR")
public class StateChangeThisHour {

	public StateChangeThisHour() {

	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "machineId")
	private int machineId;

	@Column(name = "turnOn")
	private boolean turnOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMachineId() {
		return machineId;
	}

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "machineScheduleId")
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public boolean getTurnOn() {
		return turnOn;
	}

	public void setTurnOn(boolean turnOn) {
		this.turnOn = turnOn;
	}

	@Override
	public String toString() {
		return "StateChangeThisHour [id=" + id + ", machineId=" + machineId + ", turnOn=" + turnOn + "]";
	}
}
