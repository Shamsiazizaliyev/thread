package threadlifecycle;

class Accountt {
    private int balance = 0;

    public synchronized void withdraw(int amount) throws InterruptedException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 12000) {

            System.out.println(Thread.currentThread().getName() + "running");


            System.out.println(2);
        }
        while (balance < amount) {


            System.out.println(Thread.currentThread().getName() + ": Balance not enough, waiting...");
            wait();  // Gözləyir ki, balance dolsun

        }
//
//        if(amount>=balance){
//            throw new InterruptedException("Not enough balance");
//        }
        balance -= amount;
//
        System.out.println(Thread.currentThread().getName() + ": Withdrawn " + amount + ", Balance left: " + balance);
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + ": Deposited " + amount + ", New balance: " + balance);
        notify();  // Gözləyən thread-ə xəbər verir
    }
}

public class Notify {

    public static void main(String[] args) throws InterruptedException {

        Accountt account = new Accountt();

        Thread withdrawThread = new Thread(() -> {
            try {
                account.withdraw(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "WithdrawThread");

        withdrawThread.start();
        Thread depositThread = new Thread(() -> {
            try {
                Thread.sleep(8000);  // 2 saniyə gözləyir, sonra balans əlavə edir
                account.deposit(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "DepositThread");



        depositThread.start();


        Thread.sleep(200000);










        Thread.sleep(1000000);
    }
}
