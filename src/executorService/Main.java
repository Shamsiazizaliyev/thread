package executorService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {


        newSingleThreadExecutor();


        //   Thread.sleep(70000);


        // executorService.submit(new MyRunnable(30));


       // Thread.sleep(100000000);

    }


    public static void newSingleThreadExecutor() throws InterruptedException {


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<?> future = executorService.submit(new MyRunnable(1));
        executorService.submit(new MyRunnable(2));
        executorService.submit(new MyRunnable(3));
        executorService.submit(new MyRunnable(4));
        executorService.submit(new MyRunnable(5));


        Thread.sleep(1000);
        if (!future.isDone()) {
            System.out.println("Task hələ icra olunur");
            //future.cancel(true); // bu halda run() içində return olacaq
            executorService.shutdown();
        }



//executorService.shutdownNow();

//
//        Thread.sleep(2000);
//        Object result = null;
//            result = future.get();
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("test");
//        System.out.println("Nəticə: " + result);
//        System.out.println("test 2");


//
//           future.cancel(true);
//        if (!future.isDone()) {
//            System.out.println("Task hələ icra olunur");
//        }

    }
// Task-ı ləğv etmək olar
// future.cancel(true);


    public static void newFixedThreadPool() {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new MyRunnable(1));
        executorService.submit(new MyRunnable(2));
        executorService.submit(new MyRunnable(3));
        executorService.submit(new MyRunnable(4));
        executorService.submit(new MyRunnable(5));
        executorService.submit(new MyRunnable(6));
        executorService.submit(new MyRunnable(7));
        executorService.submit(new MyRunnable(8));
        executorService.submit(new MyRunnable(9));
        //executorService.shutdown();


        //        if (!future.isDone()) {
//            System.out.println("Task hələ icra olunur");
//            future.cancel(true);
////        }
//        List<Callable<String>> tasks = Arrays.asList(
//                new MyCallable(1),
//                new MyCallable(2),
//                new MyCallable(3),
//                new MyCallable(4),
//                new MyCallable(5),
//                new MyCallable(6),
//                new MyCallable(7),
//                new MyCallable(8)
//        );
//
//        List<Future<String>> futures = executorService.invokeAll(tasks);
//
//        for (Future<String> f : futures) {
//            System.out.println(f.get()); // Task nəticələrini çap edir
//        }
//
//        executorService.shutdown();


    }

    public static void newCachedThreadPool() {



        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {

            executorService.submit(new MyRunnable(i));


        }


    }

    public static void customThreadPool() {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4, 6,
                30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2)
        );

        for (int i = 1; i < 7; i++) {
            threadPoolExecutor.submit(new MyRunnable(i));
        }


    }
}
