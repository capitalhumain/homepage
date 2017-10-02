package observable;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservableLab1 {
    public static void main(String[] args) {
        ConnectableObservable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").publish();

        // set observer1
        source.subscribe(s -> System.out.println("Observer1: " + s));

        // set observer2
        source.map(String::length)
                .subscribe(s -> System.out.println("Observer2: " + s));

        source.connect();
    }
}
