package os;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;

import programs.Program;

//Class to represent a process.
public class Process extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int RUNNING = 0;
	public static final int WAIT = 1;
	public static final int IDLE = 2;
	public static final int TERMINATED = 3;
	public static final int ABORTED = 4;

	private String[] args; // The calling arguments.
	private long id; // Process Id number (pid).
	private int priority; // Priority.
	private long startTime; // Process start time.
	private long endTime; // End time after process completes.
	private int status; // Process status.
	private boolean isInterrupted; 	//Returns true if a process was interrupted


	Class<?> className; // Byte code name of class.
	Program classInstance; // Class instance that can be called.

	// Links for next and previous list entries.
	public Process next;
	public Process previous;

	// Constructors and methods.
	public Process(String[] args, int priority, long id, int status) throws ClassNotFoundException, IOException {
		this.setName(args[0]);
		this.args = args;
		this.priority = priority;
		this.id = id;
		this.status = status;
		className = Class.forName("programs." + getName());
	}

	public void validity() {

	}
	
	public void interrupt(){
		this.isInterrupted = true;
	}
	
	public String run(PrintWriter out) throws InstantiationException, IllegalAccessException {
		StringBuffer message = new StringBuffer();
		System.out.println("ibe");
		classInstance = (Program) className.newInstance();
		System.out.println("two");
		startTime = System.currentTimeMillis();
		status = RUNNING;
		try {
			classInstance.run(out, args);
		} catch (InterruptedException e){
			status = 4;
			message.append("Process " + this.getName() + " failed\n").toString();
		}
		status = 3;
		endTime = System.currentTimeMillis();
		message.append(getName() + " ran successfully time = " + (endTime - startTime) + " milliseconds\n");
		return message.toString();
	}

	public long getId() {
		return id;
	}


	public String[] getArgs(String[] args) {
		String[] a = new String[args.length - 1];
		System.arraycopy(args, 1, a, 0, a.length);
		return a;
	}

	public long getRunTime() {
		return endTime - startTime;
	}

	public String getStatus() {
		switch (this.status) {
		case 0:
			return "Running";
		case 1:
			return "Wait";
		case 2:
			return "Idle";
		case 3:
			return "Terminated";
		case 4:
			return "Aborted";
		default:
			return "ERROR: No Status!";

		}
	}

	public String toString() {
		return getName() + "\t\t" + getId() + "\t" + getStatus() + "\t" + getRunTime() + "\t" + getPriority() + "\t " + Arrays.toString(getArgs(this.args));
	}
}