package commands;

import java.io.IOException;

import console.CliCommand;
import os.Process;

public class Add extends CliCommand

{
	public Add() {
	}
	/**
	 * A method to add a new process into process list
	 * Check number parameter so there wont be an error
	 * Check if process list actually exists.
	 * Add a process
	 * @return confirmation message
	 */
	@SuppressWarnings("static-access")
	@Override
	public String execute(String[] arg) throws ClassNotFoundException, IOException {
		long pid = System.currentTimeMillis() % 100000;
		if (Long.parseLong(arg[1]) < 1)
			return "Illegal number parameter";
		if (runList == null) 
			 return "ERROR: Batch doesn't exist!\n";
		process = new Process(arg, Thread.NORM_PRIORITY, pid, process.IDLE);
		runList.enQueue(process);
		return "process " + process.getName() + " added to batch\n";
	}

}
