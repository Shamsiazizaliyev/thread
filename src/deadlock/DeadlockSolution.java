package deadlock;

public class DeadlockSolution {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void thread1Work() {
        synchronized (lock1) {
            System.out.println("Thread1: lock1 aldı");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock2) {      // Sonra lock2
                System.out.println("Thread1: lock2 aldı və işini tamamladı");
            }
        }
    }

    // Thread-2 işi
    public void thread2Work() {
        synchronized (lock1) {
            System.out.println("Thread2: lock1 aldı");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock2) {      // Sonra lock2
                System.out.println("Thread2: lock2 aldı və işini tamamladı");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockSolution ds = new DeadlockSolution();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                ds.thread1Work();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                ds.thread2Work();
            }
        });

        t1.start();
        t2.start();
    }
}
