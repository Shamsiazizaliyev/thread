package semapore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SemaphoreFair {

        public static void main(String[] args) throws InterruptedException {
            // 2 icazə var, fairness = true
            Semaphore sem = new Semaphore(1);

            Runnable task = () -> {


                while(true) {


                    String ad = Thread.currentThread().getName();
                    try {
                        System.out.println(ad + " icazə almağa çalışır...");

                        sem.acquire(); // FIFO prinsipinə görə alır eger true olarsa
                        System.out.println(ad + " resursdan istifadə edir...");
                        long start = System.currentTimeMillis();
                        while (System.currentTimeMillis() - start < 4000) {
                           // System.out.println(Thread.currentThread().getName() + " running");
                        }


                        System.out.println(ad + " işi bitirdi.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        sem.release(); // icazəni geri qaytarır
                        System.out.println(ad + " icazəni buraxdı.");

                    }
                }
            };




            for (int i = 0; i < 10; i++) {
                new Thread(task,"Thread-"+i).start();
            }





            Thread.sleep(10000000);
        }




}