package reentranctlock;



import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockFIFO {
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
           while (true){

                lock.lock();
                try {

                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() - start < 3000) {
                       //System.out.println(Thread.currentThread().getName() + " running");
                    }
                }
                finally {
                    lock.unlock();

                }

               System.out.println(name);
            }


        };

        for (int i = 1; i <= 20; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}
