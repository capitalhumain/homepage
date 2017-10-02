package first;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Lab13 {
    public static void main(String[] args) {
        Single<String> source = Single.just("Hello");

        source.map(String::length).subscribe(System.out::println);
        source.subscribe(System.out::println);
    }
}
