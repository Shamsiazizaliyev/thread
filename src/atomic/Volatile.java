package atomic;

public class Volatile {


    static  volatile boolean running = true;


    public static void main(String[] args) throws InterruptedException {



        new Thread(() -> {
            while (running) {


            }

            System.out.println(" thread terminate");

        },"Volite thread").start();

        Thread.sleep(15000);

  running = false;


    }

}
