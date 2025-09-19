package threadcreate;



class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread işə düşdü!");

    }
}


class MyRunnable implements Runnable {





    @Override

    public void run() {
        System.out.println("Runnable ilə yaradılan Thread işə düşdü!");
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 4000) {
            System.out.println(Thread.currentThread().getName() + " running");
        }

        System.out.println("Thread isi bitirdi");
    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException {

//        MyThread thread = new MyThread();
//        thread.start();

//
//        MyRunnable myRunnable = new MyRunnable();
//        Thread thread = new Thread(myRunnable);
//        thread.setName("MyRunnable Thread");
//        thread.start();

//
//      Thread.sleep(1000000);
//
       Runnable myRunnable = () -> {
            System.out.println("LambdaThread işləyir: " + Thread.currentThread().getName());
       };

      Thread myThread = new Thread(myRunnable);
       myThread.start();  // Thread işə düşür
    }

}
