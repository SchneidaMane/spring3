package main.java.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CommandLine extends Thread {

	Input input;
	Output output;
	Error error;
	String command = "";
	String nextLine = "";
	String stream = "";
	String successString = "";
	String failureString = "";

	public CommandLine() {

	}

	public CommandLine setupProcess(String command) {
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Input gets process' output. Output puts input into the process.
		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		input = new Input(in);
		PrintWriter out = new PrintWriter(proc.getOutputStream());
		output = new Output(out);
		BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		error = new Error(err);
		return this;
	}

	public CommandLine startThreads() {
		input.start();
		output.start();
		error.start();
		return this;
	}

	public CommandLine setLine(String nextLine) {
		output.setLine(nextLine);
		return this;
	}

	//Set the line and wait for input/error to match either the successString or the failureString. If successString is matched, the stream's getSuccess() method will return true, and false if failureString is matched
	public boolean setLine(String nextLine, String stream, String successString, String failureString) throws InterruptedException {

		boolean success = false;
		CountDownLatch latch = new CountDownLatch(1);
		output.setLine(nextLine);

		if (stream.equals("Input")) {
			input.blockForInput(latch, successString, failureString);
			latch.await();
			success = input.getSuccess();
		} else if (stream.equals("Error")) {
			error.blockForError(latch, successString, failureString);
			latch.await();
			success = error.getSuccess();
		}
		input.end();
		output.end();
		error.end();
		return success;
	}

	public CommandLine endThreads(long sleepTime) {
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		input.end();
		output.end();
		error.end();
		return this;
	}

	public CommandLine endThreads() {
		input.end();
		output.end();
		error.end();
		return this;
	}

	public Input getInput() {
		return this.input;
	}

	public Output getOutput() {
		return this.output;
	}

	public Error getError() {
		return this.error;
	}

	//Threaded variant
	//Doesn't have support for returning success/failure boolean out of stream
	public CommandLine withProcess(String command) {
		this.command = command;
		return this;
	}

	public CommandLine withLine(String nextLine) {
		this.nextLine = nextLine;
		return this;
	}

	public CommandLine withLine(String nextLine, String stream, String successString, String failureString) {
		this.nextLine = nextLine;
		this.stream = stream;
		this.successString = successString;
		this.failureString = failureString;
		return this;
	}

	@Override
	public void run() {
		Runtime rt = Runtime.getRuntime();
		Process proc = null;
		try {
			proc = rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Input gets process' output. Output puts input into the process.
		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		input = new Input(in);
		PrintWriter out = new PrintWriter(proc.getOutputStream());
		output = new Output(out);
		BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		error = new Error(err);
		input.start();
		output.start();
		error.start();

		CountDownLatch latch = new CountDownLatch(1);
		output.setLine(nextLine);
		try {
			if (stream.equals("Input")) {
				input.blockForInput(latch, successString, failureString);
				latch.await();
			} else if (stream.equals("Error")) {
				error.blockForError(latch, successString, failureString);
				latch.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		input.end();
		output.end();
		error.end();
	}
}
