package programs;

import java.io.PrintWriter;
import java.util.Random;

public class Writer extends Program{
    private DataBase db;
    int workingElement;
    PrintWriter out;

    public Writer(DataBase db, int workingElement, PrintWriter out){
        super("Writer");
        this.db = db;
        this.workingElement = workingElement;
        this.out = out;
    }

    void doWrite(PrintWriter out, int number){
        out.println("Writer locked record "+ number +" for write");
        system("write "+number+" "+Math.random());
        out.println("Writer wrote "+ number +" value = "+ system("read "+number));
        out.println("Write released record "+ number +" for write");
    }

    public void run() {
            try {
                sleep((long) (Math.random()*500));
               //db.acquireWrite();
                doWrite(out, workingElement);
                //db.releaseWrite();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    @Override
    public int run(PrintWriter out, String[] args) throws InterruptedException {
        return 0;
    }
}

