package main.java.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import main.java.core.io.Error;
import main.java.core.io.Input;
import main.java.core.io.Output;

public class OnOffExecutorJob implements Job {

	Runtime rt = null;
	Process proc = null;
	BufferedReader in = null;
	PrintWriter out = null;
	BufferedReader err = null;
	Input input = null;
	Output output = null;
	Error error = null;

	public OnOffExecutorJob() {
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Running windows process");
		rt = Runtime.getRuntime();
		try {
			proc = rt.exec("C:\\Windows\\system32\\cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		Input input = new Input(in);
		input.start();
		PrintWriter out = new PrintWriter(proc.getOutputStream());
		Output output = new Output(out);
		output.start();
		BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		Error error = new Error(err);
		error.start();

		// Get commands to print here
		String instanceName = "micro1";
		String zone = "us-east1-b";
		output.setLine("gcloud compute instances start " + instanceName + " --zone " + zone);
		/*- //Commented because getReadyToTerminate() doesn't exist anymore
				while (!error.getReadyToTerminate()) {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				*/
		input.end();
		output.end();
		error.end();
		proc.destroy();

		// JobDataMap map = context.getMergedJobDataMap();
		// ArrayList<MachineSchedule> schedules =
		// DatabaseReader.getRawSchedules();
		// if (schedules != null) {
		// for (MachineSchedule schedule : schedules) {
		// System.out.println(schedule);
		// }
		// }
		// Only machines that will change state next hour should be included in
		// the below map

		/*-
		HashMap<MachineSchedule, Boolean> machineToActivationState = DatabaseReaderJob.getMachineToActivationState();
		if (machineToActivationState != null) {
			for (MachineSchedule schedule : machineToActivationState.keySet()) {
				System.out.println(schedule);
				System.out.println(machineToActivationState.get(schedule));
			}
		}
		Set<Thread> threads = Thread.getAllStackTraces().keySet();
		for (Thread t : threads) {
			String name = t.getName();
			Thread.State state = t.getState();
			int priority = t.getPriority();
			String type = t.isDaemon() ? "Daemon" : "Normal";
			System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
		}
		/*-
		String machine = map.getString("machine");
		String zone = map.getString("zone");
		String onOff = map.getString("onOff");
		
		
		
		*/
	}
}
