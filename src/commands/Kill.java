package commands;

import console.CliCommand;

public class Kill extends CliCommand {
	/**
	 * A method to remove a batch from the list
	 * Check if required batch exists. If not, @return error message
	 * @return updated list
	 */
	@Override
	public String execute(String[] args) {
		if (listOfBatches.containsKey(args[0])) {
			listOfBatches.remove(args[0]);
			return CliCommand.getList();
		}
		return "Batch " + args[0] + " does not exist";
	}

}
