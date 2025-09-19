package syncronized;

public class RaceCondition {
    private int counter = 0;


    public   void increment() {
        counter++;
        System.out.println(Thread.currentThread().getName() + " artırdı, counter = " + counter);

    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        RaceCondition example = new RaceCondition();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                example.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                example.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter: " + example.getCounter());
    }
}
