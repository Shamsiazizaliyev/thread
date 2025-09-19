package threadlifecycle;


class Account {
    int balance = 80;

    public synchronized void withdraw(int amount) throws InterruptedException {

        while (balance < amount) {
            System.out.println(Thread.currentThread().getName() + ": Balance not enough, waiting...");
            wait();  // burda thread gözləyir və bloklanır
        }
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 40000) {
            System.out.println(Thread.currentThread().getName() + "running");
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + ": Withdrawn: " + amount + ", Balance: " + balance);
    }
}

public class Wait {


    public static void main(String[] args) throws InterruptedException {
        Account acc = new Account();

        Thread t1 = new Thread(() -> {
            try {
                acc.withdraw(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "WithdrawThread");

        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                acc.withdraw(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "WithdrawThread2");
//
//
//        System.out.println("15 saniye sonra  thread 2 basliyacaq");
//        Thread.sleep(15000);
      t2.start();




        Thread.sleep(1000000);
        // Heç bir notify çağırılmır, ona görə t1 sonsuz gözləyəcək
    }
}
