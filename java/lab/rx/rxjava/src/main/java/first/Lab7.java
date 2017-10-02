package first;

import io.reactivex.Observable;

public class Lab7 {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .filter(i -> i%2 > 0)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
