package commands;

import console.CliCommand;
public class Remove extends CliCommand{
	/**
	 * A method to remove process from current queue
	 * Remove a process by its ID
	 * @return confirmation
	 */
	public String execute(String[] args){
		return runList.remove(Long.parseLong(args[0])).toString() + "\t---removed from queue.";
	}
}
