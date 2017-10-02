package first;

import io.reactivex.Observable;

public class Lab6 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(i -> System.out.println("RECIEVED: " + i),
                           Throwable::printStackTrace,
                           () -> System.out.println("Done."));
    }
}
