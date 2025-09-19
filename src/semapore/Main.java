package semapore;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2); // 2 icazə var

        Runnable task = () -> {
            int available = sem.availablePermits();
            String ad = Thread.currentThread().getName();
            try {
                sem.acquire(); // icazə alır
                System.out.println(ad + " resursdan istifadə edir...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 20000) {

                }
                System.out.println(ad + " işi bitirdi.");




            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release(); // icazəni geri qaytarır
                System.out.println("available=" + available + "");


            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");


        t1.start();
        t2.start();
        t3.start();
    }
}
