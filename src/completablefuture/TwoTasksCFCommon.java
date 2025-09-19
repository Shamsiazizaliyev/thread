package completablefuture;

import java.util.concurrent.CompletableFuture;

public class TwoTasksCFCommon {

    public static int doWork(int taskId,  int result) {
        System.out.println("Task " + taskId + " işə düşdü");
        long start = System.currentTimeMillis();


        while (System.currentTimeMillis() - start < 5000) {

        }
        System.out.println("Task " + taskId + " bitdi");
        return result;
    }

    public static void main(String[] args) {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> doWork(1 , 10));
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> doWork(2, 20));

        int total = future1.join() + future2.join();
        System.out.println("Cəmi: " + total);
    }
}
