package completablefuture;

import java.util.concurrent.CompletableFuture;

public class AsyncThreeTasks {

    public static int doWork(int taskId, int result) {
        System.out.println("Task " + taskId + " işə düşdü");
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < 5000) {
        }
        System.out.println("Task " + taskId + " bitdi");
        return result;
    }
    public static int doWork1(int taskId, int result) {
        System.out.println("Task " + taskId + " işə düşdü");
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < 10000) {
        }
        System.out.println("Task " + taskId + " bitdi");
        return result;
    }

    public static int doWork2(int taskId, int result) {
        System.out.println("Task " + taskId + " işə düşdü");
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < 15000) {
        }
        System.out.println("Task " + taskId + " bitdi");
        return result;
    }
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Integer> future1 =
                CompletableFuture.supplyAsync(() -> doWork(1, 10));
        CompletableFuture<Integer> future2 =
                CompletableFuture.supplyAsync(() -> doWork1(2, 20));
        CompletableFuture<Integer> future3 =
                CompletableFuture.supplyAsync(() -> doWork2(3, 30));



        future1.thenAccept(r -> System.out.println(Thread.currentThread().getName()+"Task1 nəticə: " + r));
        future2.thenAccept(r -> System.out.println("Task2 nəticə: " + r));
        future3.thenAccept(r -> System.out.println("Task3 nəticə: " + r));

     //  System.out.println("netice="+future2.join());

        System.out.println("Main thread davam edir...");

         //Hamısı bitəndən sonra toplamı hesabla
        CompletableFuture<Void> all =
                CompletableFuture.allOf(future1, future2, future3);

        System.out.println("Main thread davam edir 2...");
        all.thenRun(() -> {
            int sum = future1.join() + future2.join() + future3.join();
            System.out.println("block");
            System.out.println("Cəmi: " + sum);


        });


        Thread.sleep(50000);
        try {
        //    all.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
