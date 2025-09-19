package StampedLock;



import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterLockDemo {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    static class Reader extends Thread {
        private final int id;

        Reader(int id) { this.id = id; }

        @Override
        public void run() {
            readLock.lock(); // oxu lock-u
            try {
                System.out.println("Reader " + id + " oxuyur...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 10000) {
                    // oxuma əməliyyatı
                }
                System.out.println("Reader " + id + " çıxdı.");
            } finally {
                readLock.unlock();
            }
        }
    }

    static class Writer extends Thread {
        private final int id;

        Writer(int id) { this.id = id; }

        @Override
        public void run() {
            writeLock.lock(); // yazı lock-u
            try {
                System.out.println("Writer " + id + " yazır...");
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 5000) {
                    // yazma əməliyyatı
                }
                System.out.println("Writer " + id + " çıxdı.");
            } finally {
                writeLock.unlock();
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
        Thread.sleep(12000); // writer bitməsini gözləyirik
        Thread r3 = new Reader(3);
        Thread r4 = new Reader(4);
        r3.start(); r4.start();
    }
}
