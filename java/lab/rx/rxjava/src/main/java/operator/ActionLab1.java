package operator;

import io.reactivex.Observable;

public class ActionLab1 {
    public static void main(String[] args) {
        System.out.println("===> doOnNext playground");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnNext(s -> System.out.println("Processing: " + s))
                .map(String::length)
                .subscribe(i -> System.out.println("Receive: " + i));

        System.out.println("===> doOnComplete playground");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnComplete(() -> System.out.println("Source is done emitting!"))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));

        System.out.println("===> doOnError playground");
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .doOnError(e -> System.out.println("Source failed!"))
                .map(i -> 10/i)
                .doOnError(e -> System.out.println("Division Failed!!!" + e))
                .subscribe(i -> System.out.println("Received: " + i),
                        e -> System.out.println("Received Error: " + e));

        System.out.println("===> doOnSubscribe and doOnDispose playground");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnSubscribe(d -> System.out.println("Subscribing!" + d))
                .doOnDispose(() -> System.out.println("Disposing!!"))       // not work current rxjava 2.1.3
                .doFinally(() -> System.out.println("Do Finally!!!"))
                .subscribe(i -> System.out.println("Receive: " + i));

        System.out.println("===> doOnSuccess playground");
        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("Receive: " + i));
    }
}
