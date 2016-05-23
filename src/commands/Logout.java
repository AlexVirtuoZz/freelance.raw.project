package commands;

import console.Cli;
import console.CliCommand;

import java.io.IOException;

public class Logout extends CliCommand{

	@Override
	public String execute(String[] args) throws IOException{
		System.exit(0);
		return "logout";
	}
}
