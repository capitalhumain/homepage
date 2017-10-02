package first;

import io.reactivex.Observable;

public class Lab12 {
    private static void withoutUsingCallable() {
        Observable.just(1/0)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("Error Occurred: " + e));
    }

    private static void withCallable() {
        Observable.fromCallable(() -> 1/0)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("Error Captured: " + e));
    }

    public static void main(String[] args) {
        //withoutUsingCallable();

        withCallable();
    }
}
