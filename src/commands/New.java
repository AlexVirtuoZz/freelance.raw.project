package commands;

import console.CliCommand;
import os.ProcessList;

public class New extends CliCommand {
	public New() {
		// create a ProcessList or queue
	}
	/**
	 * A method to create new batch
	 * Check if a batch already exists
	 * @return error, if a batch already exists
	 * Else, put a new batch into list
	 * @return confirmation
	 */
	@Override
	public String execute(String[] args) {
		if (!listOfBatches.containsKey(args[0])) {
			listOfBatches.put(args[0], new ProcessList(args[0]));
			runList = listOfBatches.get(args[0]);
			return "New batch initialized";
		}
		return "Batch already exists";
	}
}
