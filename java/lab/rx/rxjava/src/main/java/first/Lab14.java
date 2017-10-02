package first;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab14 {
    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable = seconds.subscribe(l -> System.out.println("Observer1 receive: " + l));

        seconds.subscribe(l -> System.out.println("Observer2 receive: " + l));

        sleep(5000);

        // Observer1 stop receiving emission
        disposable.dispose();

        sleep(5000);

        System.out.println("Done.");

        // other
        Pattern pattern = Pattern.compile(".*D0001.*");
        Matcher matcher = pattern.matcher("1D000109");
        System.out.println(matcher.matches());
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
