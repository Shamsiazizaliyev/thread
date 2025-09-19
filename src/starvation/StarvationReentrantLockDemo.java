package starvation;

import java.util.concurrent.locks.ReentrantLock;

public class StarvationReentrantLockDemo {

    private static int counter = 0;

    public static void main(String[] args) {

        // fairness = false → starvation üçün ideal
        ReentrantLock lock = new ReentrantLock(true);

        for (int i = 1; i <= 40; i++) {
            int threadNumber = i;

            Thread thread = new Thread(() -> {
                while (true) {
                    if (lock.tryLock()) { // lock alıb counter artırır
                        try {
                            counter++;
                            if (counter % 1000 == 0) {
                                if (threadNumber >= 30) {
                                    System.out.println("\u001B[32m" + Thread.currentThread().getName() +
                                            " increased counter to " + counter + "\u001B[0m");
                                } else {
                                    System.out.println("\u001B[31m" + Thread.currentThread().getName() +
                                            " increased counter to " + counter + "\u001B[0m");
                                }
                            }
                        } finally {
                            lock.unlock();
                        }
                    }
                    // aşağı prioritetli thread CPU almağa çalışacaq → starve olacaq
                }
            }, "Thread-" + i);

            if (i >= 30) {
                thread.setPriority(Thread.MAX_PRIORITY); // 90-100 = 10
            } else {
                thread.setPriority(3); // 1-89 = 3
            }

            thread.start();
        }
    }
}
