package core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureLab1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(() -> {
            Thread.sleep(10);
            return "Hello, World!";
        });
        System.out.println("Processing...");
        while(!future.isDone()) {
            System.out.println("waiting...");
        }
        getIfNotCancelled(future);
//        service.shutdown();
    }

    private static void getIfNotCancelled(Future<String> future) {
        try {
            if(!future.isCancelled()) {
                System.out.println(future.get());
            } else {
                System.out.println("Cancelled");
            }
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
