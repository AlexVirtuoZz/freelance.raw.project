package commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import os.ProcessList;
import console.CliCommand;

public class Load extends CliCommand {
	/**
	 * A method to load a batch from the disk file
	 * Check if required file exists. If not, return appropriate message
	 * Initialize required output streams to a root directory
	 * Upload a file
	 * Check if this batch already exists in a list
	 * If it exists, remove current batch and put uploaded batch into list
	 * @return confirmation message 
	 */
	@Override
	public String execute(String[] args) throws IOException, ClassNotFoundException  {
		StringBuffer message = new StringBuffer();
		if (!new File (ROOT+"\\"+args[0]+".sav").exists())
			return message.append("Batch not found").toString();
		FileInputStream inputStream = new FileInputStream(ROOT+"\\"+args[0]+".sav");
		ObjectInputStream ois = new ObjectInputStream(inputStream);
		ProcessList newProcessList = (ProcessList) ois.readObject();
		ois.close();
		message.append("File loaded successfully\n");
		
		if (listOfBatches.containsKey(newProcessList.getName())) {
			listOfBatches.remove(runList.getName(), runList);}
		
		listOfBatches.put(newProcessList.getName(), newProcessList);
		runList = listOfBatches.get(newProcessList.getName());
		message.append("Batch "+newProcessList.getName()+" initialized");
		return message.toString();
	}
	
}
