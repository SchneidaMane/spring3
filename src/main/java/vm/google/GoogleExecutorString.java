package main.java.vm.google;

public class GoogleExecutorString {

	String instanceName = null;
	String zone = null;

	public GoogleExecutorString(String instanceName, String zone) {
		this.instanceName = instanceName;
		this.zone = zone;
	}

	public String turnOn() {
		return "gcloud compute instances start " + instanceName + " --zone " + zone;
	}

	public String turnOff() {
		return "gcloud compute instances stop " + instanceName + " --zone " + zone;
	}

}
