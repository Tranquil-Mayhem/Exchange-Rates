import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

/***
 * This is the CurrencyDAO class of the program. It contains the Data Access Objects for the other classes to utilize. It
 * mainly deals with reading and writing the files from the web, and the user-named csv.
 */
public class CurrencyDAO {

    private static final String CURRENCY_URL = "https://www.gov.uk/government/uploads/system/uploads/attachment_data/file/937009/exrates-monthly-1220.csv";

    private static final String CSV_FORMAT = "%s,%s,%f,%f,%s,%s";

    /***
     * This method retrieves a file from the web that contains the exchange rate information. It scans each line and
     * appends it to a string. If the url cannot be loaded, it displays a user-friendly message.
     * @return the entire online file as a string
     */
    public static String retrieveFile() {
        StringBuilder sb = new StringBuilder();
        try{
        var url = new URL(CURRENCY_URL);

        InputStream in = url.openStream();
        Scanner scan = new Scanner(in);

        while(scan.hasNext()){
            String str = scan.nextLine();
            sb.append(str + "\n");
        }

        }catch(IOException e){
            System.out.println("The file could not be loaded, sorry :(");

        }
        return sb.toString();
    }


    /***
     * This is the toCSV method. It creates the format that the CSV file will implement when saving.
     * @param currency the currency that is being represented
     * @param originalAmount the value of the original user-input currency in UK pounds
     * @return the format that the CSV file will follow
     */
    public static String toCSV(Currency currency, double originalAmount) {

        Objects.requireNonNull(currency);
        return String.format(
                CSV_FORMAT,
                currency.getCurrencyCode(),
                currency.getCountryName(),
                currency.getCurrencyUnitsPerEuro() * originalAmount,
                currency.getCurrencyUnitsPerEuro(),
                currency.getStartDate(),
                currency.getEndDate()
                );
    }

    /***
     * This is the save method of the CurrencyDAO class. It writes to a file and ensures that any exceptions are caught.
     * @param data the data within the currencySet
     * @param originalAmount the value of the original currency in UK pounds
     * @param filename the user-named csv file
     */
    public void save(CurrencySet data, double originalAmount, String filename) {

        var currencies = data.getCurrencies();
        try (var out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(filename)))) {
            for (var currency : currencies) {
                var line = this.toCSV(currency, originalAmount);

                out.println(line);
            }
        } catch (IOException iOEx) {
            System.err.println(iOEx.getMessage());
        }
    }
}