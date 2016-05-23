package commands;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import console.CliCommand;

public class Save extends CliCommand {
	/**
	 * A method to save a batch on the disk
	 * Set a directory and a file name (a batch name describes file name) 
	 * Set all required input streams
	 * Save a batch
	 * @return confirmation
	 */
	@Override
	public String execute (String[] args) throws IOException{
		
		FileOutputStream outStream = new FileOutputStream(ROOT+"\\"+ runList.getName()+".sav");
		ObjectOutputStream oos = new ObjectOutputStream(outStream);
		oos.writeObject(runList);
		oos.flush();
		oos.close();
		return "Batch "+runList.getName()+" saved successfully";
	}
	
}
