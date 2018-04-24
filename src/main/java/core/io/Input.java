package main.java.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

public class Input extends Thread {

	private BufferedReader inputReader = null;
	private String lastString = "";
	private boolean terminate = false;
	private boolean success = false;

	public Input(BufferedReader in) {
		this.inputReader = in;
	}

	public void run() {
		while (!Thread.interrupted()) {
			try {
				if (inputReader.ready()) {
					lastString = inputReader.readLine();
					System.out.println(lastString);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			if (terminate == true) {
				return;
			}
		}
		try {
			inputReader.close();
		} catch (IOException e) {
		}
	}

	public void blockForInput(CountDownLatch latch, String successString, String failureString) {
		Pattern pSuccess = Pattern.compile(successString);
		Pattern pFailure = Pattern.compile(failureString);
		while (true) {
			if (pSuccess.matcher(lastString).find()) {
				this.success = true;
				latch.countDown();
				break;
			}
			if (pFailure.matcher(lastString).find()) {
				this.success = false;
				latch.countDown();
				break;
			}
		}
	}

	//successString only variant
	public void blockForInput(CountDownLatch latch, String successString) {
		Pattern pSuccess = Pattern.compile(successString);
		while (true) {
			if (pSuccess.matcher(lastString).find()) {
				this.success = true;
				latch.countDown();
				break;
			}
		}
	}

	public boolean getSuccess() {
		return success;
	}

	public void end() {
		terminate = true;
	}
}
