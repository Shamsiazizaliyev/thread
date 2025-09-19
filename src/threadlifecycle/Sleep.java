package threadlifecycle;

public class Sleep {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " sleepdir");
            try {

                Thread.sleep(20000);

                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 20000) {
                    System.out.println(Thread.currentThread().getName() + " running");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " sona çatdı");
        }, "MyThread");


        t1.start();

        System.out.println("main thread bitti");
        Thread.sleep(1000000);




    }
}
