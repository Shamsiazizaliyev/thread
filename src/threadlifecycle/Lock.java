package threadlifecycle;

 ;

public class Lock {

    private int count = 0;

    public synchronized void increment() {
        count++;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 4000) {

            //System.out.println(Thread.currentThread().getName() + " running");
        }
        System.out.println(Thread.currentThread().getName() + " count: " + count);

    }

    public static void main(String[] args) throws InterruptedException {
        Lock obj = new Lock();

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                obj.increment();

            }
        }, "Thread-A");

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                obj.increment();

            }
        }, "Thread-B");
        System.out.println(" threadler basladi");
        t1.start();
        t2.start();
        System.out.println(" Main thread  bitdi");

        Thread.sleep(10000000);



    }
}
