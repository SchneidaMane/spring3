package main.java.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

public class Error extends Thread {

	BufferedReader errorReader = null;
	String lastString = "";
	boolean terminate = false;
	boolean success = false;

	public Error(BufferedReader errorReader) {
		this.errorReader = errorReader;
	}

	public void run() {
		while (!Thread.interrupted()) {
			try {
				if (errorReader.ready()) {
					lastString = errorReader.readLine();
					System.err.println(lastString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (terminate == true) {
				return;
			}
		}
		try {
			errorReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blockForError(CountDownLatch latch, String successString, String failureString) {
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
	public void blockForError(CountDownLatch latch, String successString) {
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
