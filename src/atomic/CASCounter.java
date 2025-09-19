package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CASCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        boolean success = false;
        while (!success) {

            int current = count.get();
            int newValue = current + 1;

            success = count.compareAndSet(current, newValue);

        }
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASCounter counter = new CASCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
