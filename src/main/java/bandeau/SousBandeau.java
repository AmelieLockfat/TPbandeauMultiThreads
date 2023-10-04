package bandeau;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SousBandeau extends Bandeau {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private static int sbCount = 0;
    private boolean iAmFree = true;
    private final int myNumber;

    public SousBandeau() {
        myNumber = ++sbCount;
    }

    synchronized public void take() throws InterruptedException {
        while (!iAmFree) {
            wait();
        }
        // assert iAmFree;
        iAmFree = false;
        System.out.println("Bandeau " + myNumber + " Taken");

    }


    synchronized public void release() {
        // assert !iAmFree;
        System.out.println("Bandeau " + myNumber + " Released");
        iAmFree = true;
        notifyAll();
    }
}


