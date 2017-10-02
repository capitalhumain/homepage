package multicasting;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.ThreadLocalRandom;

public class MulticastingWithOperator {

    public static void main(String[] args) {
        cold();

        range_publish_map();
    }

    private static void range_publish_map() {
        ConnectableObservable<Integer> threeInts = Observable.range(1, 3).publish();
        Observable<Integer> threeRandoms = threeInts.map(i -> randomInt());
        threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));
        threeRandoms.subscribe(i -> System.out.println("Observer 2: " + i));
        threeInts.connect();
    }

    private static void cold() {
        Observable<Integer> threeRandoms = Observable.range(1, 3)
                .map(i -> randomInt());

        threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));
        threeRandoms.subscribe(i -> System.out.println("Observer 2: " + i));
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }
}
