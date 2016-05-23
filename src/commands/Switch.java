package commands;

import console.CliCommand;

public class Switch extends CliCommand {
	/**
	 * A method to change current batch
	 * Check if required batch exists 
	 * @return error message if a batch does not exist
	 * @return current process list
	 */
	@Override
	public String execute(String[] args) {
		if (listOfBatches.containsKey(args[0]))
			runList = listOfBatches.get(args[0]);
		else return args[0]+" is not a recognized batch";
		return runList.toString();
	}

}
