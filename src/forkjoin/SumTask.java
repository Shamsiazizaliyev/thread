package forkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class SumTask extends RecursiveTask<Integer> {

    private int[] arr;
    private int start;
    private int end;
    private static final int THRESHOLD = 10;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;

        if (length <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        }

        // Əgər tapşırıq böyükdürsə, onu iki hissəyə bölürük
        int mid = start + length / 2;  // Massivin ortasını tapırıq

        // Sol hissə üçün yeni tapşırıq yaradırıq (start-dan mid-ə qədər)
        SumTask leftTask = new SumTask(arr, start, mid);

        // Sağ hissə üçün yeni tapşırıq yaradırıq (mid-dən end-ə qədər)
        SumTask rightTask = new SumTask(arr, mid, end);

        // Sol hissəni paralel işləmək üçün "fork" edirik
        leftTask.fork();

        // Sağ hissəni hazırda özü icra edir (rekursiv)
        int rightResult = rightTask.compute();

        // Sol hissənin nəticəsini gözləyir və alır ("join")
        int leftResult = leftTask.join();

        return leftResult + rightResult;
    }

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();

        int result = pool.invoke(new SumTask(arr, 0, arr.length));

        System.out.println("Toplam: " + result);
    }
}
