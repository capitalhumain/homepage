package core;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkJoinPoolSize {
    public static void main(String[] args) {
        long total = LongStream.rangeClosed(1, 3000000)
                               .parallel()
                               .sum();
        System.out.println("Total: " + total);
        int poolSize = ForkJoinPool.commonPool().getPoolSize();
        System.out.println("Pool size: " + poolSize);
    }
}
