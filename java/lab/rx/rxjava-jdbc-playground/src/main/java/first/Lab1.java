package first;

import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import rx.Observable;

import java.sql.Connection;

public class Lab1 {
    public static void main(String[] args) {
        Connection conn = new ConnectionProviderFromUrl("jdbc:h2:~/data/test", "sa", "").get();

        Database db = Database.from(conn);

        Observable<String> customerNames = db.select("select name from customer").getAs(String.class);

        customerNames.subscribe(s -> System.out.println(s));
    }
}
