package starvation;

class PriorityStarvationDemo {



    // Shared synchronized method
    public synchronized void method(String threadName) {
        System.out.println(threadName + " lock aldı və işləyir...");
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 6000) {
            // Busy work: CPU 2 saniyə tutulur
        }
        System.out.println(threadName + " lock buraxdı");
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityStarvationDemo demo = new PriorityStarvationDemo();

        Runnable task = () -> {
            while (true) {
                demo.method(Thread.currentThread().getName());
                try {
                    Thread.sleep(10); // çox qısa fasilə
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadA = new Thread(task, "Thread A (LOW)");
        Thread threadB = new Thread(task, "Thread B (HIGH)");
        Thread threadC = new Thread(task, "Thread C (HIGH)");
        Thread threadD = new Thread(task, "Thread D (HIGH)");
        Thread threadE = new Thread(task, "Thread E (HIGH)");
        Thread threadF = new Thread(task, "Thread F (HIGH)");

        threadA.setPriority(Thread.MIN_PRIORITY); // 1
        threadB.setPriority(Thread.MAX_PRIORITY); // 10
        threadC.setPriority(Thread.MAX_PRIORITY); // 10
        threadD.setPriority(Thread.MAX_PRIORITY); // 10
        threadE.setPriority(Thread.MAX_PRIORITY); // 10
        threadF.setPriority(Thread.MAX_PRIORITY); // 10

// priorty eslinde  jvm den os schedule  teklif kimi qebul olunur ve  cox vaxt qebul olunmur yeni nesil os lerde fair prisipine gore

        Thread.sleep(1000);
        threadA.start();
        threadF.start();
        threadC.start();
        threadD.start();
        threadE.start();



    }
}
