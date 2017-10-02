package combining;

import io.reactivex.Observable;

public class CachingPlayground {
    public static void main(String[] args) {
        Observable<Integer> cachedRollingTotal = Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .scan(0, (total, next) -> total + next)
                //.cache();
                .cacheWithInitialCapacity(3);

        cachedRollingTotal.subscribe(System.out::println);
    }
}
