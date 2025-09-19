package reentranctlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(ReentrantExample::outerMethod, "Thread-1");
        t1.start();
    }

    private static void outerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " outerMethod() daxil oldu.");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    private static void innerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " innerMethod() daxil oldu.");
            System.out.println(lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}
