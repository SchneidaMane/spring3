package main.java.vm.google;

import main.java.core.constants.RegexConstants;
import main.java.core.io.CommandLine;

public class GoogleMachineManager {

	String serviceAccountName;
	String machineName;
	String zone;

	public GoogleMachineManager(String serviceAccountName, String machineName, String zone) {
		this.serviceAccountName = serviceAccountName;
		this.machineName = machineName;
		this.zone = zone;
	}

	public void selectAccount() {
		CommandLine commandLine = new CommandLine();
		boolean success = false;

		//setaccount will automatically choose the project as well
		String line1 = "gcloud config set account " + serviceAccountName;

		//No fail state for this command. Could implement fail state later or just allow fail state for line2
		try {
			success = commandLine.setupProcess("C:\\Windows\\system32\\cmd.exe").startThreads() //
					.setLine(line1, "Error", RegexConstants.GOOGLESETACCOUNTSUCCESS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!success) {
			//bad stuff
		}
	}

	public void turnOn() {
		CommandLine commandLine = new CommandLine();
		boolean success = false;

		String line2 = "gcloud compute instances start " + machineName + " --zone " + zone;
		try {
			success = commandLine.setupProcess("C:\\Windows\\system32\\cmd.exe").startThreads() //
					.setLine(line2, "Error", RegexConstants.GOOGLESTARTSTOPSUCCESS, RegexConstants.GOOGLESTARTSTOPFAILURE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!success) {
			//bad stuff
		}
	}

	public void turnOff() {
		CommandLine commandLine = new CommandLine();
		boolean success = false;

		String line2 = "gcloud compute instances stop " + machineName + " --zone " + zone;
		try {
			success = commandLine.setupProcess("C:\\Windows\\system32\\cmd.exe").startThreads() //
					.setLine(line2, "Error", RegexConstants.GOOGLESTARTSTOPSUCCESS, RegexConstants.GOOGLESTARTSTOPFAILURE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!success) {
			//bad stuff
		}
	}

}
