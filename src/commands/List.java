package commands;

import console.CliCommand;

public class List extends CliCommand {
	/**
	 * A method to show a list of batches
	 * @return a list, if it is not empty, 
	 * @return an appropriate message if it's empty
	 */
	@Override
	public String execute(String[] args) {
		if (!listOfBatches.isEmpty())
			return CliCommand.getList();
		return "No batches found";
	}

}
