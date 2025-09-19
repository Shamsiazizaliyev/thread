package deadlock;

public class LiveLock {

    static class Robot {
        String name;
        boolean active = true;

        Robot(String name) {
            this.name = name;
        }

        void tryPass(Robot other) {
            while (active) {
                if (other.active) {
                    // Qarşı robotu buraxmaq üçün geri çəkilir
                    System.out.println(name + ": Sən keç, mən buraxıram " + other.name);
                    long start = System.currentTimeMillis();
                    while (System.currentTimeMillis() - start < 2000) {
                        System.out.println(Thread.currentThread().getName() + " running");
                    }
                    continue;
                }
                // Əgər qarşı robot aktiv deyilsə, keçə bilir
                System.out.println(name + " qapıdan keçdi!");
                active = false;
            }
        }
    }

    public static void main(String[] args) {
        Robot r1 = new Robot("Person-1");
        Robot r2 = new Robot("Person-2");

        new Thread(() -> r1.tryPass(r2)).start();
        new Thread(() -> r2.tryPass(r1)).start();
    }
}
