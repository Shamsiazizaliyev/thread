package callable;

import java.util.concurrent.*;
import java.util.*;

public class InvokeAll {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> tasks = Arrays.asList(
                new MyCallable(1),
                new MyCallable(2),
                new MyCallable(3)
        );

        System.out.println("Task-lər göndərildi");

        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        System.out.println("davam");

        int totalSum = 0;
        for (Future<Integer> f : futures) {
            totalSum += f.get();

        }

        System.out.println("Bütün task-lərin cəmi: " + totalSum);

        executorService.shutdown();
    }
}
