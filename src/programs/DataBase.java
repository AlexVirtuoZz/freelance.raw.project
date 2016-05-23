package programs;

import java.util.concurrent.Semaphore;

public class DataBase {
    private int readers;
    private Semaphore mutex, db;

    public DataBase(){
        readers = 0;
        mutex = new Semaphore(1);
        db = new Semaphore(1);
    }

    public void acquireRead() throws InterruptedException {
        mutex.acquire();
        if (++readers == 1) db.acquire();
        mutex.release();
    }

    public void releaseRead() throws InterruptedException {
        mutex.acquire();
        if (--readers == 0) db.release();
        mutex.release();
    }

    public void acquireWrite() throws InterruptedException { db.acquire(); }
    public void releaseWrite() throws InterruptedException { db.release(); }
}
