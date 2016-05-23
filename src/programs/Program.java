package programs;

import java.io.*;
import java.net.Socket;

//Parent class for programs to be run by the sequencer.
public abstract class Program  extends Thread implements Serializable {
	public final static long serialVersionUID = 1;

	public final static int SUCCESS = 0;
	public final static int FAILED = 1;
	private String name;
	
	public Program(String name) {
		this.name = name;
	}

	// Abstract run method
	public abstract int run(PrintWriter out, String[] args) throws InterruptedException;

	// Fair share scheduling and check time limit interrupts
	public void schedule() throws InterruptedException {
		this.sleep(500);
		if (this.isInterrupted())
			throw new InterruptedException("ERROR");
		Thread.yield();
		}

	public String getSystemCallSize(){
		return system("size");
	}

	// OS System call interface
	public String system(String call) {
		try {
			System.out.println("program side trying to connect");
			Socket sock = new Socket("127.0.0.1", 1025);
			BufferedReader inputScanner = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			PrintWriter outputWriter = new PrintWriter(sock.getOutputStream(), true);
			System.out.println("Program side scanners initiated");
			outputWriter.println(call);
			System.out.println("Program side wrote lane "+call);
			String value = inputScanner.readLine();
			System.out.println("Program side Obtaining value");
			sock.close();
			System.out.println("Program side closed");
			return value;
		} catch (IOException e){
			return "IO exception occurred!";
		}
	}

	// Method for orderly shutdown (overridden in applications in a later lab)
	public void terminate() throws InterruptedException {
	}
}