package console;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import os.Process;
import os.ProcessList;

public abstract class CliCommand {
	public final static String ROOT = "user";

	protected static ProcessList runList = null;
	protected static Process process = null;
	protected static PrintWriter trace = null;
	protected static String prompt = "--->>";
	protected static Hashtable<String, ProcessList> listOfBatches = new Hashtable<String, ProcessList>();

	public static String getList() {
		return listOfBatches.keySet().toString();
	}

	public static void outLog(String data) {
	}

	public static void outTrace(String data) {
		if (trace != null)
			trace.println(data);
	}

	public abstract String execute(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, InterruptedException;
}
