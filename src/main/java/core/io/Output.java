package main.java.core.io;

import java.io.PrintWriter;
import java.util.Scanner;

public class Output extends Thread {

	public PrintWriter out = null;
	public Scanner scanner = null;
	// hasLine must be volatile because it's written to by the Output and Start
	// threads
	public volatile boolean hasLine = false;
	public String nextLine = null;
	private boolean terminate = false;

	public Output(PrintWriter out) {
		this.out = out;
		scanner = new Scanner(System.in);
	}

	public void run() {
		while (!Thread.interrupted()) {
			if (hasLine) {
				out.println(nextLine);
				out.flush();
				hasLine = false;
			}
			if (terminate == true) {
				return;
			}
		}
		out.close();
	}

	public void setLine(String nextLine) {
		this.nextLine = nextLine;
		hasLine = true;
	}

	public void end() {
		terminate = true;
	}

}