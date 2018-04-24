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
	@Column(name = "statechangeid")
	private int stateChangeId;

	@Column(name = "machineId")
	private int machineId;

	@Column(name = "state")
	private String state;

	public int getStateChangeId() {
		return stateChangeId;
	}

	public void setStateChangeId(int stateChangeId) {
		this.stateChangeId = stateChangeId;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getState() {
		return state;
	}

	public void setTurnOn(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "STATECHANGETHISHOUR[StateChangeId: " + this.getStateChangeId() //
				+ " MachineId: " + this.getMachineId() //
				+ " State: " + this.getState() + "]"; //
	}
}
