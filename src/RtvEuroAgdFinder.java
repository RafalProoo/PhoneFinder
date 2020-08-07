import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RtvEuroAgdFinder {

    private static final String finderUrl = "https://www.euro.com.pl/search.bhtml?keyword=";

    public static String[] findItemByEan(String ean) throws IOException {
        Document document = Jsoup.connect(finderUrl + ean).get();

        String[] data = new String[5];
        for (Element row : document.select("div.basic-tech-details tr")) {
            Elements elements = row.select("td");

            switch (elements.get(0).text()) {
                case "Pamięć RAM":
                    data[0] = elements.get(1).text().replaceAll(",", ".");
                    break;
                case "Pamięć wbudowana":
                    data[1] = elements.get(1).text().replaceAll(",", ".");
                    break;
                case "Dual SIM":
                    if (elements.get(1).text().equals("nie")) {
                        data[2] = "1";
                    } else if (elements.get(1).text().equals("tak")) {
                        data[2] = "2";
                    }
                    break;
                case "Kolor obudowy ":
                    data[3] = elements.get(1).text();
                    break;
            }

        }
        data[4] = document.baseUri().equals(finderUrl + ean) ? "null" : document.baseUri();
        return data;
    }
}
