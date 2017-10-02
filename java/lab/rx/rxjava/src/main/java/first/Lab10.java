package first;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Lab10 {
    public static void main(String[] args) {
        ConnectableObservable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish();

        // instructs the ConnectableObservable to begin emitting items from its underlying observable to its observer.
        seconds.connect();

        // 這樣就沒有人會處理到0
        sleep(1000);

        seconds.subscribe(s -> System.out.println("Observer1: " + s));

        sleep(5000);

        seconds.subscribe(s -> System.out.println("Observer2: " + s));

        sleep(5000);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
