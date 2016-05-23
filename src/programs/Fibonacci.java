package programs;

//Class to simulate a run time program.
//Generates fibonacci sequences of numbers.
import java.io.PrintWriter;
import java.io.Serializable;

public class Fibonacci extends Program implements Serializable{

	private static final long serialVersionUID = 1L;

	public Fibonacci() {
		super("Fibonacci Calculator");
	}

	public int run(PrintWriter out, String[] args) throws InterruptedException {
		StringBuffer output = new StringBuffer();
		long maxNumber = Long.parseLong(args[1]);
			
		output.append(args[0] + ": Sequence up to " + maxNumber + " numbers\n");
		if (maxNumber >= 1)
			output.append(1+"\n");
		if (maxNumber >= 2)
			output.append(1+"\n");
		long first = 1, second = 1, newNumber;
		for (int i = 2; i < maxNumber; i++) {
			schedule();
			newNumber = first + second;
			first = second;
			second = newNumber;
			output.append(newNumber+"\n");
		}
		if (!this.isInterrupted()){
			out.println(output.toString());
			return SUCCESS;}
		return FAILED;
	}
}