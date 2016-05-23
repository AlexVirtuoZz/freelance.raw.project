package commands;
import console.CliCommand;

public class Priority extends CliCommand{
	public Priority(){}
	public String execute(String[] args){
		int intPriority =  Integer.parseInt(args[1]);
		if(intPriority < 1 || intPriority > 9)
			return "ERROR: Priority is only 1 - 9!";
		runList.setPriority(Long.parseLong(args[0]), intPriority);
		return process.toString();
	}

}
