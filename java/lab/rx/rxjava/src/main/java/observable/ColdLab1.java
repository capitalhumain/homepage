package observable;

import io.reactivex.Observable;

public class ColdLab1 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source.subscribe(s -> System.out.println("Observer1: " + s));
        source.map(String::length).filter(i -> i >= 5).subscribe(s -> System.out.println("Observer2: " + s));
    }
}
