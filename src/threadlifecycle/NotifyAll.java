package threadlifecycle;



class Account2 {
    private int balance = 0;

    public synchronized void withdraw(int amount) throws InterruptedException {
        while (balance < amount) {
            System.out.println(Thread.currentThread().getName() + ": Balance not enough, waiting...");
            wait();  // Gözləyir ki, balance dolsun
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + ": Withdrawn " + amount + ", Balance left: " + balance);
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + ": Deposited " + amount + ", New balance: " + balance);
        notifyAll(); // BÜTÜN gözləyən thread-lərə xəbər verir
    }
}

public class NotifyAll {

    public static void main(String[] args) {

        Account2 account = new Account2();

        // 3 nəfər pullarını çəkməyə çalışır
        for (int i = 1; i <= 3; i++) {
            Thread withdrawThread = new Thread(() -> {
                try {
                    account.withdraw(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "WithdrawThread-" + i);
            withdrawThread.start();
        }

        // 2 saniyədən sonra bir nəfər balans artırır
        Thread depositThread = new Thread(() -> {
            try {
                Thread.sleep(20000); // 2 saniyə sonra
                account.deposit(300);  // kifayət qədər pul əlavə olunur
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "DepositThread");

        depositThread.start();
    }
}
