package semapore;

import java.util.concurrent.Semaphore;

public class Ownership {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(0); // başlanğıcda icazə = 0

        Thread t1 = new Thread(() -> {
            System.out.println("T1: icazə (permit) almaq istəyir...");
            try {
                Thread.sleep(5000);
                sem.acquire(); // gözləyir, hələ icazə yoxdur

                System.out.println("T1: icazəni aldı və davam edir!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {

            try {
                Thread.sleep(2000); // T1 bir az gözləsin
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2: T1 üçün icazə buraxır (release).");

            sem.release(); // icazəni T2 verir
//            sem.release(); // icazəni T2 verir
//            sem.release();
//            int available = sem.availablePermits();
//            System.out.println("semapore=" + available + "");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // icazəni T2 verir
        });

        t1.start();
        t2.start();
    }
}
