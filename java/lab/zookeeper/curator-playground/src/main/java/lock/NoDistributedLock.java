package lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class NoDistributedLock {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i=0; i<10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch(Exception e) {}
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println(String.format("產生的訂單號是: %s", orderNo));
                }
            }).start();
        }
        countDownLatch.countDown();
    }
}
