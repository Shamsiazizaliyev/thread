package executorService;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    private int index;

    public MyCallable(int index) {
        this.index = index;
    }

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println(index + " .Callable işə düşdü");
        while (System.currentTimeMillis() - start < 1000) {

        }
        System.out.println(index + " .Callable işə isin bitirdi");
        return index + " nəticə";
    }
}
