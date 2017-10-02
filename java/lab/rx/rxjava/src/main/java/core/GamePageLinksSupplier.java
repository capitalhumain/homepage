package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.text.NumberFormatter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GamePageLinksSupplier implements Supplier<List<String>> {
    private static final String BASE = "http://gd2.mlb.com/components/game/mlb";
    private LocalDate startDate;
    private int days;
    DecimalFormat decimalFormat = new DecimalFormat("00");
    NumberFormatter textFormatter = new NumberFormatter(decimalFormat);

    public GamePageLinksSupplier(LocalDate startDate, int days) {
        this.startDate = startDate;
        this.days = days;
    }

    private String makeMLBURL(LocalDate localDate) throws ParseException {
        return String.format("%s/year_%s/month_%s/day_%s", BASE, localDate.getYear(), textFormatter.valueToString(localDate.getMonthValue()), textFormatter.valueToString(localDate.getDayOfMonth()));
    }

    public List<String> getGamePageLinks(LocalDate localDate) {
        try {
            Document doc = Jsoup.connect(makeMLBURL(localDate)).get();
            return doc.getElementsByTag("a").stream()
                    .filter(elem -> elem.text().trim().startsWith("gid"))
                    .map(elem -> doc.baseUri() + elem.attr("href"))
                    .collect(Collectors.toList());
        } catch(ParseException | IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<String> get() {
        return Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(days)
                .map(this::getGamePageLinks)
                .flatMap(list -> list.isEmpty() ? Stream.empty() : list.stream())
                .collect(Collectors.toList());
    }
}
