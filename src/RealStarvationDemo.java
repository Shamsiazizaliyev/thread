import java.util.concurrent.locks.ReentrantLock;

public class RealStarvationDemo {
    private static final ReentrantLock lock = new ReentrantLock(false); // unfair lock

    public static void main(String[] args) {
        Thread highPriority = new Thread(() -> {
            int i = 0;
            while (true) {
                lock.lock();
                try {
                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() - start < 2000) {
                        System.out.println(Thread.currentThread().getName() + " running");
                    }
                    System.out.println("High-Priority working: " + i++);

                } finally {
                    lock.unlock();
                }
            }
        }, "High-Priority");


        Thread lowPriority = new Thread(() -> {
            int i = 0;
            while (true) {
                lock.lock();
                try {
                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() - start < 2000) {
                        System.out.println(Thread.currentThread().getName() + " running");
                    }
                    System.out.println("Low-Priority working: " + i++);

                } finally {
                    lock.unlock();
                }


            }
        }, "Low-Priority");
//
        highPriority.setPriority(8);
        lowPriority.setPriority(10);
        highPriority.start();
        lowPriority.start();


    }
}
