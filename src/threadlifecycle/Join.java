package threadlifecycle;

public class Join {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 başladı.");
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 30000) {
                System.out.println(Thread.currentThread().getName() + " running");
            }
            System.out.println("Thread 1 bitdi.");
        });



        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Thread 2, Thread 1-in bitməsini gözləyir...");
                t1.join();
                System.out.println("Thread 2 başladı.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2 sonlandi.");
        });

        t1.start();
        t2.start();
        System.out.println("Mainde isin bitiridi");
    }
}
