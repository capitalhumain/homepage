package first;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * You just learned about the cold Observable, which works much like a music CD.
 * A hot Observable is more like a radio station. It broadcasts the same emissions
 * to all Observers at the same time.
 *
 * => This is cold
 */
public class Lab9 {
    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        seconds.subscribe(s -> System.out.println("Observer 1: " + s));

        sleep(5000);

        seconds.subscribe(s -> System.out.println("Observer 2: " + s));

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
