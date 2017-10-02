package operator;

import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static utils.Utils.sleep;

public class TransformOperatorLab1 {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(i -> System.out.println("RECEIVE: " + i));

        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");

        menu.startWith("COFFEE SHOP MENU")
                .subscribe(s -> System.out.println(s));

        menu.startWithArray("COFFEE SHOP MENU", "------------------------")
                .subscribe(System.out::println);

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(s -> System.out.println("RECEIVE: " + s),
                        e -> System.out.println("RECEIVE ERROR: " + e));

        Observable.just(6, 2, 5, 7, 1,4, 9, 8, 3)
                .sorted()
                .subscribe(System.out::println);

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println("Received: " + s));
        sleep(5000);

        // scan(BiFunction<T,T,T> accumulator)
        Observable.just(5, 3, 7, 10, 2, 14)
                .scan((accumulator, next) -> {
                    System.out.println(String.format("Acc: %d, Next: %s", accumulator, next));
                    return accumulator + next;
                })
                .subscribe(s -> System.out.println("Received: " + s));

        // scan(R initialValue, BiFunction<R,? super T,R> accumulator)
        Observable.just(0, 1, 2, 3, 4)
                .scan(10, (total, next) -> total + next)
                .subscribe(s -> System.out.println("Receive: " + s));

        // TODO:
        // 1. Content
        // 2. new group id generator: 64*64*64*64
        //       gid和internal talend 共用id generator
        // 3. UnitGroup和UserGroup分開，不使用繼承
    }
}
