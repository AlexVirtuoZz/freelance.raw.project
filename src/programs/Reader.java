package programs;


import java.io.PrintWriter;

public class Reader extends Program{
    private DataBase db;
    int workingElement;
    PrintWriter out;

    public Reader (DataBase db, int workingElement, PrintWriter out){
        super("Reader");
        this.db = db;
        this.workingElement = workingElement;
        this.out = out;
    }

    void doRead(PrintWriter out, int number){
        String value = system("read "+String.valueOf(number));
        out.println("Reader read "+ number +" value = "+ value);
    }

    public void run() {
            System.out.println(this.getName() +" started");
            try {
                sleep((long) (Math.random()*500));
                System.out.println(this.getName() + "woke up");
                //db.acquireRead();
                out.println("Reader locked record "+ workingElement +" for read");
                System.out.println(this.getName() + "acquired read");
                doRead(out, workingElement);
                System.out.println(this.getName() + "made do read");
                //db.releaseRead();
                out.println("Reader released record "+ workingElement +" for read");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Override
    public int run(PrintWriter out, String[] args) throws InterruptedException {
        return 0;
    }
}
