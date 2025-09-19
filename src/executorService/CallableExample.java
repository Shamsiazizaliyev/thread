package executorService;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(new MyCallable(1));
        Future<String> future2 = executorService.submit(new MyCallable(2));
        Future<String> future3 = executorService.submit(new MyCallable(3));

        System.out.println("Task göndərildi");


        if (!future.isDone()) {
            System.out.println("Task hələ icra olunur");
        }

        String result = null;
        try {
            result = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("timeout exception");
        }
        System.out.println("davam");

        result = future.get();
        System.out.println("Nəticə: " + result);

       // executorService.shutdown();
    }
}
