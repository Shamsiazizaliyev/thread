package deadlock;

public class RealDeadlock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();




    public void thread1Work() {
        synchronized (this) {
            System.out.println("Thread1: lock1 aldı");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (this) {
                System.out.println("Thread1: lock2 aldı");
            }
        }
    }

    public void thread2Work() {
        synchronized (this) {
            System.out.println("Thread2: lock2 aldı");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (this) {
                System.out.println("Thread2: lock1 aldı");
            }
        }
    }

    public static void main(String[] args) {
        RealDeadlock rd = new RealDeadlock();

        Thread t1 = new Thread(rd::thread1Work);
        Thread t2 = new Thread(rd::thread2Work);

        t1.start();
        t2.start();
    }
}

