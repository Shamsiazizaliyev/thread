package reentranctlock;

import java.util.concurrent.locks.ReentrantLock;

public class Interrupt {
    private final ReentrantLock lock = new ReentrantLock();

    public void doWork(String threadName) {
        try {
            System.out.println(threadName + " lock almağa çalışır...");
            lock.lockInterruptibly();
            lock.lock();// Sonsuz gözləyir, amma interruptable
            try {
                System.out.println(threadName + " lock aldı və işi icra edir");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 10000) {

                }
            } finally {
                lock.unlock();
                System.out.println(threadName + " lock-u buraxdı");
            }
        } catch (InterruptedException e) {
                         e.printStackTrace();
            //System.out.println(threadName + " interrupt edildi və iş dayandırıldı");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Interrupt obj = new Interrupt();

        Thread t1 = new Thread(() -> obj.doWork("Thread-1"));
        Thread t2 = new Thread(() -> obj.doWork("Thread-2"));

        t1.start();
        Thread.sleep(100); // t1 lock-u almağa başlayır
        t2.start();

        Thread.sleep(12000); // t2 lock gözləyərkən interrupt edirik
        t2.interrupt();
    }
}
