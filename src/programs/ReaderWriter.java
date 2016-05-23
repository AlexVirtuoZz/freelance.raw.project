package programs;

import java.io.PrintWriter;
import java.util.Random;

public class ReaderWriter extends Program {

    Reader[] readers;
    Writer[] writers;
    DataBase[] dataBases = new DataBase[Integer.parseInt(getSystemCallSize())];
    int numOfIterations;
    int records;

    public ReaderWriter(){
        super("Reader Writer");
        for (DataBase db : dataBases) {
            db = new DataBase();
        }
        System.out.println("RW Created");
    }

    @Override
    public void terminate() throws InterruptedException {
        for (Reader r : readers){
            if (r.isAlive())
            r.interrupt();
        }
        for (Writer w : writers){
            if (w.isAlive())
                w.interrupt();
        }
        for (Reader r : readers){
            if (r.isAlive())
                r.join();
        }
        for (Writer w : writers){
            if (w.isAlive())
                w.join();
        }
    }

    @Override
    public int run(PrintWriter out, String[] args) throws InterruptedException {
        this.readers = new Reader[Integer.parseInt(args[1])];
        this.writers = new Writer[Integer.parseInt(args[2])];
        this.numOfIterations = Integer.parseInt(args[3]);
        this.records = Integer.parseInt(args[4]);

        System.out.println("RUNNING RW!!! ALL ARE INITIATED");
        for (int i = 0 ; i < numOfIterations; i++) {
            System.out.println("For "+i+" iteration");
            for (int j = 0; j < readers.length; j++) {
                System.out.println("trying to initiate readers");
                readers[j] = new Reader(dataBases[j % dataBases.length], new Random().nextInt(records), out);
                System.out.println("reader "+j+" is starting");
                readers[j].start();
            }
            for (int j = 1; j < writers.length; j++) {
                System.out.println("trying to initiate writers");
                writers[j] = new Writer(dataBases[dataBases.length - j], new Random().nextInt(records), out);
                System.out.println("writer "+j+" starting");
                writers[j].start();
            }
            if (this.isInterrupted()) terminate();
            schedule();
        }
        return SUCCESS;
    }
}
