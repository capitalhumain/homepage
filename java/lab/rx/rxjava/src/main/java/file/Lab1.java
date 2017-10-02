package file;

import io.reactivex.Observable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class Lab1 {
    public static void main(String[] args) {
        try(Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            lines.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .forEach(w -> System.out.printf("%s (%d)\n", w, w.length()));
        } catch(IOException e) {
            e.printStackTrace();
        }

        try(Stream<Path> list = Files.list(Paths.get("src/main/java"))) {
            list.forEach(System.out::println);
        } catch(IOException e) {
            e.printStackTrace();
        }

        try(Stream<Path> paths = Files.walk(Paths.get("src/main/java"))) {
            paths.forEach(System.out::println);
        } catch(IOException e) {
            e.printStackTrace();
        }

        try(Stream<Path> paths = Files.find(Paths.get("src/main/java"), Integer.MAX_VALUE, (path, attributes) -> !attributes.isDirectory() && path.toString().contains("operator"))) {
            paths.forEach(System.out::println);
        } catch(IOException e) {
            e.printStackTrace();
        }

        //concatMap_lab();
    }

    private static void concatMap_lab() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        System.out.println("flatMap ========>");
        source.flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);

        System.out.println("concatMap ========>");
        source.concatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }
}
