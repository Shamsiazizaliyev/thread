package reentranctlock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class TryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void doWork(String threadName) {
        try {

            if (lock.tryLock(15, TimeUnit.SECONDS)) {
                try {
                    System.out.println(threadName + " lock aldı və işi icra edir");
                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() - start < 10000) {

                    }

                } finally {
                    lock.unlock();
                    System.out.println(threadName + " lock-u buraxdı");
                }
            } else {
                System.out.println(threadName + " lock-u ala bilmədi, başqa iş görür");
            }
        } catch (InterruptedException e) {
           // Thread.currentThread().interrupt();
            System.out.println(threadName + " interrupt edildi");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TryLockExample obj = new TryLockExample();

        Thread t1 = new Thread(() -> obj.doWork("Thread-1"));
        Thread t2 = new Thread(() -> obj.doWork("Thread-2"));

        t1.start();
        t2.start();




        Thread.sleep(100000000);
    }
}
