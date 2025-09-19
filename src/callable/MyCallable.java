package callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private int index;

    public MyCallable(int index) {
        this.index = index;
    }

    @Override
    public Integer call() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println(index + " .Callable işə düşdü");
        int sum=0;
        while (System.currentTimeMillis() - start < 5000) {

        }
        sum += index;
        System.out.println(index + " .Callable işə isin bitirdi");
        return sum;
    }
}
