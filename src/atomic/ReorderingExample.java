package atomic;

public class ReorderingExample {


    static int a = 0, b = 0;
    static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;

        while (true) {
            count++;

            a = b = x = y = 0;

            Thread thread1 = new Thread(() -> {
                a = 1;        // yaz
                x = b;        // oxu
            });

            Thread thread2 = new Thread(() -> {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                b = 1;        // yaz
                y = a;        // oxu
            });

            thread1.start();

            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("a = " + a + ", b = " + b + ", x = " + x + ", y = " + y);
            if (x == 0 && y == 0) {
                System.out.println("Reordering baş verdi! Iterasiya: " + count);
                System.out.println("x = " + x + ", y = " + y);
                break;
            }
        }
    }
}
