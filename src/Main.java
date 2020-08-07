import java.io.*;

public class Main {

    public static void main(String[] args) {
        String pathToCsvWithoutPhonesInfo = "ean.csv";
        String pathToCsvWithPhonesInfo = "ean_complete.csv";

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(pathToCsvWithoutPhonesInfo));
            writer = new BufferedWriter(new FileWriter(pathToCsvWithPhonesInfo));

            String columnNames = reader.readLine();
            writer.write(columnNames + "\n");

            String basicPhoneInfo;

            while ((basicPhoneInfo = reader.readLine()) != null) {
                basicPhoneInfo = basicPhoneInfo.replaceAll(",,,,,", "");
                String[] basicPhoneInfoArray = basicPhoneInfo.split(",");

                String[] infoAboutPhoneFromShop = RtvEuroAgdFinder.findItemByEan(basicPhoneInfoArray[3]);

                String urlToSiteWithPhoneOnMgsm = MgsmFinder.findPhone(basicPhoneInfoArray[1], basicPhoneInfoArray[2]);

                writer.write(basicPhoneInfo
                             + infoAboutPhoneFromShop[0]
                             + ","
                             + infoAboutPhoneFromShop[1]
                             + ","
                             + infoAboutPhoneFromShop[2]
                             + ","
                             + infoAboutPhoneFromShop[3]
                             + ","
                             + urlToSiteWithPhoneOnMgsm
                             + ","
                             + infoAboutPhoneFromShop[4]
                             + "\n");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
