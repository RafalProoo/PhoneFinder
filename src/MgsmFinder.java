import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MgsmFinder {

    private static final String finderUrl = "https://www.mgsm.pl/pl/katalog/";

    public static String findPhone(String companyName, String model) {
        String url = "null";
        try {
            Document document = Jsoup.connect(finderUrl + companyName + "/" + model.replaceAll(" ", "")).get();
            url = document.baseUri();
        } catch (HttpStatusException e) {
            //Site not found - return null string
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
