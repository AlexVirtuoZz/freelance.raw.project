package console;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Cli implements CommandListener {

	static OSConsole console;
	SystemCall systemCall;

	public static OSConsole getConsole() {
		return console;
	}

	public static void main(String[] args) {

		new Cli();
	}

	public Cli() {

		try {
			systemCall = new SystemCall(1025, 10);
			systemCall.start();
		} catch (IOException e) {
			console.writeLine("IO Exception occurred while instantiating SystemCall");
		}
		console = new OSConsole("Batch Sequencer");
		console.setCommandListener(this);
		console.login();
	}

	@Override
	public void commandReceived(String command) {
		String[] argData = command.split(" ");
		argData[0] = Character.toUpperCase(argData[0].charAt(0)) + argData[0].substring(1);
		String[] args = new String[argData.length - 1];
		System.arraycopy(argData, 1, args, 0, args.length);
		try {
			Class<?> className = Class.forName("commands." + argData[0]);
			if (argData[0].equalsIgnoreCase("logout"))
				systemCall.close();
			CliCommand cli = (CliCommand) className.newInstance();
			console.writeLine(cli.execute(args));
		} catch (Throwable thrown) {
			if (thrown instanceof ClassNotFoundException) {
				console.writeLine("java.lang.ClassNotFoundException for " + command);
			} else if (thrown instanceof IOException) {
				console.writeLine("java.io.IOException for " + command);
			} else if (thrown instanceof InstantiationException) {
				console.writeLine("java.lang.InstantiationException for " + command);
			} else if (thrown instanceof IllegalAccessException) {
				console.writeLine("java.lang.IllegalAccessException for " + command);
			} else if (thrown instanceof FileNotFoundException) {
				console.writeLine("java.lang.FileNotFoundException for " + command);
			} else {
				console.writeLine("java.Throwable Exception for " + command);
			}
		}
	}
}
