package syncronized;

public class Monitor {

    public synchronized void methodA() {
        System.out.println(Thread.currentThread().getName() + " Method A start");
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 20000) {

        }

    }


    public   synchronized void methodB() {
        System.out.println(Thread.currentThread().getName() + " Method B start");

        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 20000) {

        }
    }

    public static void main(String[] args) {
        Monitor obj = new Monitor();
        Monitor obj2 = new Monitor();
        new Thread(() -> obj.methodA(), "Thread A").start();
        new Thread(() -> obj2.methodB(), "Thread B").start();
    }
}
