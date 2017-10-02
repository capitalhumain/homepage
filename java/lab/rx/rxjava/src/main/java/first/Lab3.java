package first;

import io.reactivex.Observable;

public class Lab3 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
           emitter.onNext("Alpha");
           emitter.onNext("Beta");
           emitter.onNext("Gamma");
           emitter.onNext("Delta");
           emitter.onNext("Epsilon");
           emitter.onComplete();
        });

//        Observable<Integer> lengths = source.map(String::length);
//
//        Observable<Integer> filtered = lengths.filter(i -> i>=5);
//
//        filtered.subscribe(s -> System.out.println("RECEIVED: " +s));
        source.map(String::length)
                .filter(i -> i>= 5)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
