package reentranctlock;

import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment() {
        lock.lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " counter = " + counter);

            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 4000) {
            }

        } finally {
            System.out.println(Thread.currentThread().getName() + " holdCount (before unlock) = " + lock.getHoldCount());
            //lock.unlock();
        }
    }

    public static void main(String[] args) {
        SimpleLockExample obj = new SimpleLockExample();

        Runnable task = obj::increment;
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread holdCount at end = " + obj.lock.getHoldCount());
    }
}
