package operator;

import io.reactivex.Observable;

public class ReduceLab1 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .skip(2)
                .count()
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce((total, next) -> total + next)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce("", (total, next) -> total + (total.equals("")?"":",") + next)
                .subscribe(s -> System.out.println("Received: " + s));

        // all 全部滿足才是true
        Observable.just(5, 3, 7, 10, 2, 14)
                .all(i -> i > 10)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(5, 3, 7, 10, 2, 14)
                .all(i -> i > 0)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(5, 3, 7, 10, 2, 14)
                .any(i -> i > 10)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.range(1, 10000)
                .contains(9657)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
