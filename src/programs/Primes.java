package programs;

import java.io.PrintWriter;
import java.io.Serializable;

public class Primes extends Program implements Serializable{

	private static final long serialVersionUID = 1L;

	public Primes() {
		super("Find Prime Numbers");
	}

	public int run(PrintWriter out, String[] args) throws InterruptedException {
		int maxNumber = Integer.parseInt(args[1]);
		StringBuffer output = new StringBuffer();
		output.append(args[0] + ": First " + maxNumber + " prime numbers\n");
		if (maxNumber >= 1)
			output.append(1+"\n");
		if (maxNumber >= 2)
			output.append(3+"\n");
		if (maxNumber >= 3)
			output.append(5+"\n");
		if (maxNumber >= 4)
			output.append(7+"\n");
		if (maxNumber >= 5)
			output.append(11+"\n");
		if (maxNumber >= 6) {
			long currentNum = 12;
			int count = 6;
			int div = 2;
			while (maxNumber >= count) {
				schedule();
				if (currentNum % div != 0) {
					div++;
					if (currentNum % div != 0) {
						div++;
						if (currentNum % div != 0) {
							div++;
							if (currentNum % div != 0) {
								div++;
								if (currentNum % div != 0) {
									div++;
									if (currentNum % div != 0) {
										div++;
										if (currentNum % div != 0) {
											div++;
											if (currentNum % div != 0) {
												output.append(currentNum+"\n");
												count++;
											}
										}
									}
								}
							}
						}
					}
				}
				div = 2;
				currentNum++;
			}
		}
		if (!isInterrupted()){
			out.println(output.toString());
			return SUCCESS;}
		return FAILED;
	}

}