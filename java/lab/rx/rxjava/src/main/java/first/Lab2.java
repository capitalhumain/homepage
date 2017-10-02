package first;

import io.reactivex.Observable;

public class Lab2 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter-> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onNext("Delta");
            emitter.onNext("Epsilon");
            emitter.onComplete();
        });

        source.subscribe(s -> System.out.println("Received: " + s));
    }
}
