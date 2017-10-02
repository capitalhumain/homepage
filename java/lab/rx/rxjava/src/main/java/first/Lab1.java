package first;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Lab1 {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("one", "two", "three", "four", "five");

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String element) {
                System.out.println(element);
            }
        });

        System.out.println("Done.");
    }
}
