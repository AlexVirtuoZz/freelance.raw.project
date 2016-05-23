package commands;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import os.BatchThread;
import console.CliCommand;

public class Run extends CliCommand {

	public Run() {

	}
	/**
	 * A method to run process list into file
	 * Create a file, into which a processes will write
	 * Check argument, which describes a limit of run time, to prevent errors
	 * @return error, if argument is negative
	 * Create new thread, in which a process list will execute
	 * @return confirmation
	 */
	public String execute(String[] arg) throws InstantiationException, IllegalAccessException, IOException, InterruptedException {
		File file = new File(ROOT, arg[0] + ".bat");
		trace = new PrintWriter(file.getCanonicalFile());
		StringBuffer str = new StringBuffer();
		long limit = Long.parseLong(arg[1]);
		if (limit < 0)
			return "Negative time limit is not available";
		else if (limit == 0)
			limit = (long) Double.POSITIVE_INFINITY;
		BatchThread batchThread = new BatchThread(arg[0], arg[0], limit, runList, str, trace);
		batchThread.start();
		return "Batch started successfully";
	}
}
