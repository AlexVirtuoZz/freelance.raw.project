package os;

import console.Cli;

public class MaxTime extends Thread {

	BatchThread thread;
	long limit;

	public MaxTime(BatchThread thread, long limit) {
		this.thread = thread;
		this.limit = limit;
		this.setPriority(MAX_PRIORITY);
	}

	public void run() {
		try {
			sleep(limit);
			if (!thread.isInterrupted()) {
				thread.interrupt();
				thread.isStopped = true;
				Cli.getConsole().write("Executing time has ran out!\n");		
				throw new InterruptedException();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			Cli.getConsole().write("Max time finished\n");
		}

	}

}
