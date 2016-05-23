package os;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import console.Cli;
import console.CliCommand;

public class BatchThread extends Thread {

	private String batchName;
	private String fileName;
	private long limit;
	private ProcessList runList;
	private PrintWriter printWriter;
	boolean isStopped = false;

	public BatchThread(String batchName, String fileName, long limit, ProcessList runList, StringBuffer sb, PrintWriter pw) {
		this.batchName = batchName;
		this.fileName = fileName;
		//Covert millisecond to seconds
		this.limit = 1000 * limit;
		this.runList = runList;
		this.printWriter = pw;
		this.setPriority(NORM_PRIORITY);
	}
	
	public void run() {	
		Process process = null;	
		try {
			MaxTime maxTime = new MaxTime(this, limit);
			File file = new File(CliCommand.ROOT, fileName + ".bat");
			printWriter = new PrintWriter(file.getCanonicalFile());
			maxTime.start();
			while (!this.isInterrupted()) {
				if (!runList.isEmpty()) {
					process = runList.peek();
					process.setPriority(7);
					Cli.getConsole().write("Process "+process.getName()+" started\n");
					Cli.getConsole().write(process.run(printWriter));
					runList.deQueue();
				}
				if (this.isStopped) throw new InterruptedException();
			}
			maxTime.interrupt();
		} catch (IllegalArgumentException | IOException | IllegalAccessException | InterruptedException | InstantiationException e) {
			Cli.getConsole().write("EXCEPTION CAUGHT\n");
			return;
		} finally {
			Cli.getConsole().write("Flush and close\n");
			printWriter.flush();
			printWriter.close();
		}
		
	}
}
