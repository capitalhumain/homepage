package demo;

import java.util.concurrent.Semaphore;

public class DBTestLock {
    private static Semaphore semaphore = new Semaphore(1);

    public static void acquire() throws InterruptedException {
        semaphore.acquire();
    }

    public static void release() {
        semaphore.release();
    }
}
