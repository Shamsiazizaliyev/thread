package deadlock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantDeadlockSolution {
    private final Lock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void threadWork(String threadName) throws InterruptedException {
        while (true) {


            if (lock1.tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(threadName + " lock1 aldı");


                    if (lock2.tryLock(500, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(threadName + " lock2 aldı və işini bitirdi");
                            break; // is bitdi loopdan cixdi
                        } finally {
                            System.out.println("lock2 unlock");
                            lock2.unlock();
                        }
                    } else {
                        System.out.println(threadName + " lock2 ala bilmədi, yenidən cəhd edir");
                    }
                } finally {
                    lock1.unlock();
                }


            } else {
                System.out.println(threadName + " lock1 ala bilmədi, yenidən cəhd edir");
            }
            Thread.sleep(100); // qisa fasile
        }
    }

    public static void main(String[] args) {
        ReentrantDeadlockSolution rds = new ReentrantDeadlockSolution();


        Thread t1 = new Thread(() -> {
            try {
                rds.threadWork("Thread1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread t2 = new Thread(() -> {
            try {
                rds.threadWork("Thread2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();


        t2.start();
    }
}
