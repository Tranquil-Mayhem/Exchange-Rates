import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/***
 * This is the CurrencySet class of the program. It contains a set of Currencies called "data" as a HashSet. This set
 * iterates through each currency within its dataset several times for various methods in this class.
 */
public class CurrencySet {
    private final Set<Currency> data;

    /***
     * This is the constructor of the CurrencySet class. It initializes data to be a new HashSet.
     */
    public CurrencySet() {
        this.data = new HashSet<>();
    }

    /***
     * This is the parse method for the CurrencySet class. It parses the entire file and creates a new currency set by
     * parsing each line within the set.
     * @param theWholeFile the entire file in a string format
     * @return the completed currencySet
     */
    public static CurrencySet parse(String theWholeFile)
    {
        var currencySet = new CurrencySet();

        String lines[] = theWholeFile.split("\\r?\\n");
        //skips the first line because it was the format explanation
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            var currency = Currency.parse(line);
            currencySet.add(currency);
        }

        return currencySet;
    }


    /***
     * This method adds each individual currency to the data set.
     * @param currency represents an individual currency
     */
    public void add(Currency currency) {
        data.add(currency);
    }

    /***
     * This method gets the currencies that are contained within the data set.
     * @return a new arraylist that contains the data in this class.
     */
    public List<Currency> getCurrencies() {
        return new ArrayList<>(this.data);
    }

    /***
     * This is the save method of the CurrencySet class. IT calls CurrencyDAO.save, and passes along information through
     * parameters.
     * @param originalAmount the original user-input currency amount in UK Pounds
     * @param filename the user-input name of the csv file they wish to export to
     */
    public void save(double originalAmount, String filename) {
        new CurrencyDAO().save(this, originalAmount, filename);
    }


    /***
     * This is a find method that finds if a countryName exists in the list of currencies.
     * @param theCountryName This is the string that represents the user input.
     * @return null if a country name is not found for the theCountryName, represented by the user input.
     */
    public Currency find(String theCountryName) {
        for(Currency currency : data) {
            if (currency.countryName.equals(theCountryName))
                return currency;
        }

        return null;
    }


    /***
     * This is the formatOfString method. Its only purpose serves to mimic the toString method, but be able to pass
     * a parameter. It iterates through a list of currency and appends information to a StringBuilder.
     * @param originalAmount the original currency amount in UK Pounds
     * @return the final string with all data from the StringBuilder
     */
    public String formatOfString(double originalAmount) {
        var builder = new StringBuilder();
        for (var currency : this.data) {
            builder.append(currency.formatOfString(originalAmount)).append(System.lineSeparator());
        }
        return builder.toString();
    }


    /***
     * This is the overridden toString() method. It iterates through a list of currency and appends information to a
     * StringBuilder.
     * @return the final string with all data from the StringBuilder
     */
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var currency : this.data) {
            builder.append(currency.toString()).append(System.lineSeparator());
        }
        return builder.toString();
    }
}