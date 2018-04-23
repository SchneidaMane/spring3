package main.java.vm.google;

import main.java.core.constants.RegexConstants;
import main.java.core.io.CommandLine;

public class MachineManager {

	String serviceAccountName;
	String machineName;

	public MachineManager(String serviceAccountName, String machineName) {
		this.serviceAccountName = serviceAccountName;
		this.machineName = machineName;

	}

	public void turnOn() {
		CommandLine commandLine = new CommandLine();
		//setaccount will automatically choose the project as well
		String line1 = "gcloud config set account " + serviceAccountName;
		//io

		//No fail state for this command. Could implement fail state later or just allow fail state for line2
		try {
			boolean success = commandLine.setupProcess("C:\\Windows\\system32\\cmd.exe").startThreads() //
					.setLine(line1, "Error", RegexConstants.GOOGLESERVICEACCOUNTACTIVATEDSUCCESS, null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//success string: Updated property [core/account].
		//failure string: 

		//String line2 = "gcloud config set project " + machineName;
		//io

		String line2 = "gcloud compute instances start " + machineName;
		//io
	}

}
