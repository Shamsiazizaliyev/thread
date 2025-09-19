package syncronized;

public class Reentrancy {

    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + " Method A başladı");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        methodB();
        System.out.println(Thread.currentThread().getName() + " Method A bitdi");
    }

    public synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + " Method B icra olunur");
    }

    public static void main(String[] args) {
        Reentrancy obj = new Reentrancy();

        Thread t1 = new Thread(() -> obj.methodA(), "Thread-1");
        Thread t2 = new Thread(() -> obj.methodB(), "Thread-2");

        t1.start();
        t2.start();
    }
}
