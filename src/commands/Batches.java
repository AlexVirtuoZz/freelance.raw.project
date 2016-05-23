package commands;

import java.io.File;

import console.CliCommand;

public class Batches extends CliCommand {
	/**
	 * A method to show a list of saved batches
	 * Append all files in directory, which ends with .sav
	 * @return error message, if 0 files were found
	 * @return list of batches
	 */
	@Override
	public String execute(String[] args){
		StringBuffer message = new StringBuffer();
		File file = new File (ROOT);
		for (String f: file.list()){
			if(f.endsWith(".sav"))
			message.append(f+"\n");
		}
		if (message.length() == 0)
			return "No saved batches found";
		return message.toString();
	}

}
