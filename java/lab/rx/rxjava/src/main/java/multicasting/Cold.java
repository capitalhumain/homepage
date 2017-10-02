package multicasting;

import io.reactivex.Observable;

public class Cold {
    public static void main(String[] args) {
        Observable<Integer> threeIntegers = Observable.range(1, 3);

        threeIntegers.subscribe(i -> System.out.println("Observer 1: " + i));
        threeIntegers.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}
