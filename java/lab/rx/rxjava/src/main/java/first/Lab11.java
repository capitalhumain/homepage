package first;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Optional;

import static java.util.Optional.of;

/**
 * Defer example
 */
public class Lab11 {
    private static int start = 1;
    private static int count = 5;

    public static void main(String[] args) {
        Observable<Integer> source = Observable.defer(() -> Observable.range(start, count));
        source.subscribe(s -> System.out.println("Observer1: " + s));

        // modify count
        count = 10;

        source.subscribe(s -> System.out.println("Observer2: " + s));

        // Not work?!
        System.out.println("ConnectableObservable + defer observable playground");
        count = 5;
        ConnectableObservable<Integer> source2 = Observable.defer(() -> Observable.range(start, count)).publish();

        source2.subscribe(s -> System.out.println("Observer3: " + s));
        source2.connect();

        //sleep(5000);

        count = 10;
        source2.subscribe(s -> System.out.println("Observer4: " + s));

        //sleep(5000);

        // Optional Playground
        Optional<String> r = testOptional();
        System.out.println(r.orElse("No Data"));
    }

    private static Optional<String> testOptional() {
        return Optional.empty();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
