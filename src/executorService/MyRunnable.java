package executorService;

public class MyRunnable implements Runnable{


    private int index;

    public MyRunnable(int index) {
        this.index = index;
    }


    @Override
    public void run() {
        System.out.println(index + " .runnable başladı, 10 saniyə gözləyəcək");

        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < 10000) {


//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                System.out.println(index + " sleep-dən interrupted oldu, çıxıram...");
//                return;
//            }
        }

        System.out.println(index + " runnable işi bitirdi");
    }
}


