package deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDeadlock {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void thread1Work() {
        lock1.lock();
        try {
            System.out.println("Thread1: lock1 aldı");
            Thread.sleep(100);
            lock2.lock();
            try {
                System.out.println("Thread1: lock2 aldı və işini bitirdi");
            } finally {
                lock2.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();

        }
    }

    public void thread2Work() {
        lock2.lock();
        try {
            System.out.println("Thread2: lock2 aldı");
            Thread.sleep(100);
            lock1.lock();
            try {
                System.out.println("Thread2: lock1 aldı və işini bitirdi");
            } finally {
                lock1.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantDeadlock sd = new ReentrantDeadlock();
        Thread t1 = new Thread(sd::thread1Work);
        Thread t2 = new Thread(sd::thread2Work);

        t1.start();
        t2.start();
    }
}
