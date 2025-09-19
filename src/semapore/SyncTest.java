package semapore;

public class SyncTest {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread-1 lock aldı, wait edir...");
                    lock.wait(); // düzgün, çünki lock sahibidir
                    System.out.println("Thread-1 davam edir...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {

                System.out.println("Thread-2 lock sahibi deyil, notify çağırır...");
                lock.notify(); // burda xəta olacaq

        });

        t1.start();

        // T2-ni gecikdiririk ki, t1 lock-u alsın
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        t2.start();
    }

}
