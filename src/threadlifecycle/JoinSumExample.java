package threadlifecycle;


public class JoinSumExample {

    static int sum1 = 0;
    static int sum2 = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sum1 += i;
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
            System.out.println("Thread 1: Sum 1-5 = " + sum1);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                sum2 += i;
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
            System.out.println("Thread 2: Sum 6-10 = " + sum2);
        });

        t1.start();
        t2.start();


        try {
            System.out.println("main gozliyir");
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int totalSum = sum1 + sum2;
        System.out.println("Main thread: Total sum = " + totalSum);
    }
}
