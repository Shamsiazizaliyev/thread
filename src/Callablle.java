import java.util.concurrent.*;

public class Callablle {

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Integer> callable = new Callable() {
            @Override
            public Object call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(100);
                    sum += i;
                }

                return sum;
            }

            ;
        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(callable);
        Integer i = future.get();
        System.out.println(i);
    }
}