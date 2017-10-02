package operator;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class Lab1 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .take(3)
                .subscribe(s -> System.out.println("RECEIVE: " + s));

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("RECEIVE: " + i));

        sleep(5000);

        Observable.range(1, 100)
                .skip(90)
                .subscribe(i -> System.out.println("RECEIVE: " + i));

        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVE: " + i));

        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma")
                .distinctUntilChanged(String::length)
                .subscribe(g -> System.out.println("RECEIVE: " + g));
    }
}
