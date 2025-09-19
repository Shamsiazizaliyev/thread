package StampedLock;

import java.util.concurrent.locks.StampedLock;

public class OptimistRetry {

    private static final StampedLock lock = new StampedLock();
    private static int sharedData = 0;

    static class Reader extends Thread {
        private final int id;

        Reader(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            int value;
            while (true) {
                long stamp = lock.tryOptimisticRead(); // optimistic oxuma
                //stamp bir nov evvelki kopyasin alirki sonra muqayse etsin
                value = sharedData;
                try {
                    System.out.println("Reader " + id + " optimistic oxuyur: " + value);
                    Thread.sleep(1000); // oxuma əməliyyatı simulyasiyası
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (lock.validate(stamp)) {
                    System.out.println("Reader " + id + " oxuma tamamlandı (optimistic ok): " + value);
                    break; // oxuma etibarlı oldu → çıxırıq
                } else {
                    System.out.println("Reader " + id + " retry edir, writer yazdı!");
                    // retry: yazı olub, yenidən oxumaq lazım
                }
            }
            System.out.println("Reader " + id + " çıxdı.");
        }
    }

    static class Writer extends Thread {
        private final int id;

        Writer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            long stamp = lock.writeLock(); // write lock
            try {
                System.out.println("Writer " + id + " yazır...");

                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 2000) {
              sharedData ++;
                } // yazma əməliyyatı simulyasiyası

            } finally {
                lock.unlockWrite(stamp);
            }
            System.out.println("Writer " + id + " çıxdı.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread r1 = new Reader(1);
        Thread r2 = new Reader(2);
        r1.start();
        r2.start();

        Thread.sleep(500); // reader-lər oxumağa başlayır

        Thread w1 = new Writer(1);
        w1.start();
    }
}
