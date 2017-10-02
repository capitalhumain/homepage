package first;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Lab15 {
    private static final CompositeDisposable disposables = new CompositeDisposable();

    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable1 = seconds.subscribe(l -> System.out.println("Observer1: " + l));
        Disposable disposable2 = seconds.subscribe(l -> System.out.println("Observer2: " + l));

        disposables.addAll(disposable1, disposable2);

        sleep(5000);

        disposables.dispose();

        sleep(5000);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
