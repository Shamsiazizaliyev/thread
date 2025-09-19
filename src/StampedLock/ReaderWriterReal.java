package StampedLock;

import java.util.concurrent.Semaphore;

public class ReaderWriterReal {
    private static int readCount = 0;
    private static final Semaphore mutex = new Semaphore(1); // readCount üçün
    private static final Semaphore write = new Semaphore(1); // writer üçün

    static class Reader extends Thread {
        private final int id;

        Reader(int id) { this.id = id; }

        @Override
        public void run() {

            try {
                // readCount update
                mutex.acquire();
                readCount++;
                if (readCount == 1) write.acquire(); // ilk reader writer-i blok edir
                mutex.release();


                System.out.println("Reader " + id + " oxuyur...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 5000) {
                   // System.out.println(Thread.currentThread().getName() + " running");
                }

                mutex.acquire();
                readCount--;
                if (readCount == 0) write.release(); // son reader çıxanda writer ala bilər
                mutex.release();

                System.out.println("Reader " + id + " çıxdı.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer extends Thread {
        private final int id;

        Writer(int id) { this.id = id; }

        @Override
        public void run() {
            try {
                write.acquire(); // writer lock
                System.out.println("Writer " + id + " yazır...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 5000) {
                    // System.out.println(Thread.currentThread().getName() + " running");
                }

                write.release();
                System.out.println("Writer " + id + " çıxdı.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Reader-lər əvvəl
        Thread r1 = new Reader(1);
        Thread r2 = new Reader(2);
        r1.start(); r2.start();

        Thread.sleep(500); // reader-lər oxumağa başlayır

        // Writer daxil olur
        Thread w1 = new Writer(1);
        w1.start();

        // Daha sonra reader-lər yenidən oxuyur
        Thread.sleep(8000); // writer bitməsini gözləyirik
        Thread r3 = new Reader(3);
        Thread r4 = new Reader(4);
        r3.start(); r4.start();
    }
}
