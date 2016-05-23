package commands;

import console.CliCommand;

public class Queue extends CliCommand {
	public Queue() {
	}
	/**
	 * A method to show current batch
	 * Check If current batch exists and is not null
	 * @return current batch
	 * Else
	 * @return error message
	 */
	public String execute(String[] args) {
		if (listOfBatches.contains(runList) && runList != null)
			return runList.toString();
		return "No batch is chosen";
	}
}
