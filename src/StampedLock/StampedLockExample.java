package StampedLock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    private static final StampedLock lock = new StampedLock();

    static class Reader extends Thread {
        private final int id;

        Reader(int id) { this.id = id; }

        @Override
        public void run() {
            long stamp = lock.readLock(); // read lock
            try {
                System.out.println("Reader " + id + " oxuyur...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 5000) {

                }
                System.out.println("Reader " + id + " çıxdı.");
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }

    static class Writer extends Thread {
        private final int id;

        Writer(int id) { this.id = id; }

        @Override
        public void run() {
            long stamp = lock.writeLock(); // write lock
            try {
                System.out.println("Writer " + id + " yazır...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 5000) {

                }
                System.out.println("Writer " + id + " çıxdı.");
            } finally {
                lock.unlockWrite(stamp);
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
