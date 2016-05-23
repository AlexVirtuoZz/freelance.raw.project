package programs;

import java.io.PrintWriter;
import java.io.Serializable;

public class Tower extends Program implements Serializable {
	private static final long serialVersionUID = 1L;
	public Tower() {
		super("Tower of Hanoi");
	}

	public int run(PrintWriter out, String[] args) throws InterruptedException {
		int numRings = Integer.parseInt(args[1]);
		
		out.print(args[0] + ": With " + numRings + " rings\n");

		doTowers(out, numRings, 'A', 'B', 'C');
		if (isInterrupted()) return FAILED;		
		return SUCCESS;
	}

	public void doTowers(PrintWriter out, int topN, char from, char inter, char to) throws InterruptedException {
		schedule();
		if (topN == 1) {
			out.print("Ring 1 from " + from + " to " + to+"\n");
		} else {
			doTowers(out, topN - 1, from, to, inter);
			out.print("Ring " + topN + " from " + from + " to " + to+"\n");
			doTowers(out, topN - 1, inter, from, to);
		}
	}
}