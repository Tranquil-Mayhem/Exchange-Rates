import java.util.Objects;

/***
 * This is the Currency class of the program. It contains the elements that make up each individual currency : including
 * name, country, code, exchange rate, and date valid.
 */
public class Currency {

     private final String currencyCode;

     final String countryName;

     private final String currencyName;

     private final double currencyUnitsPerEuro;

     private final String startDate;

     private final String endDate;


    /***
     * This is the Currency constructor. It initializes the final values.
     * @param countryName the name of the country that uses this currency
     * @param currencyName the name of the currency
     * @param currencyCode the 3-symbol code that represents the country and currency
     * @param currencyUnitsPerEuro the currency exchange rate
     * @param startDate the start date this exchange rate is valid
     * @param endDate the end date this exchange rate is valid
     */
    public Currency(String countryName,  String currencyName, String currencyCode, double currencyUnitsPerEuro, String startDate, String endDate) {
        Objects.requireNonNull(countryName);

        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.currencyUnitsPerEuro = currencyUnitsPerEuro;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /***
     * This method gets the specific currency code element for this currency object
     * @return the 3-symbol code that represents this country and currency
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /***
     * This method gets the specific currency name element for this currency object
     * @return the name of this currency
     */
    public String getCurrencyName() {
        return this.currencyName;
    }

    /***
     * This method gets the specific country name element for this currency object
     * @return the name of the country that uses this currency
     */
    public String getCountryName() {
        return this.countryName;
    }


    /***
     * This method gets the specific exchange rate element for this currency object
     * @return the currency exchange rate
     */
    public double getCurrencyUnitsPerEuro() {
        return this.currencyUnitsPerEuro;
    }

    /***
     * This method gets the specific start date element for this currency object
     * @return the start date this exchange rate is valid
     */
    public String getStartDate() {
        return this.startDate;
    }

    /***
     * This method gets the specific end date element for this currency object
     * @return the end date this exchange rate is valid
     */
    public String getEndDate() {
        return this.endDate;
    }

    /***
     * This method gets the specific country name element for the currency that the user wants
     * @return the name of the country that the user is requesting
     */
    public static String getTheCountryNameFromUser() {
        var theCountryName = Main.getString("Please enter the country name to convert currency > ");
        return theCountryName;
    }


    /***
     * This is the parse method of the Currency class. It parses each individual line it is given, and returns
     * the currency as a whole with comma separated values.
     * @param line the line that this currency represents
     * @return a new Currency as the line that was just parsed
     */
    public static Currency parse(String line) {

            String elementsOfCurrencies[] = line.split(",");

            var countryName = elementsOfCurrencies[0];
            var currencyName = elementsOfCurrencies[1];
            var currencyCode = elementsOfCurrencies[2];
            var currencyUnitsPerEuro = elementsOfCurrencies[3];
            var startDate = elementsOfCurrencies[4];
            var endDate = elementsOfCurrencies[5];
            return new Currency(countryName, currencyName, currencyCode, Double.parseDouble(currencyUnitsPerEuro), startDate, endDate);
    }

    /***
     * This is the formatOfString method. Its only purpose serves to mimic the toString method, but be able to pass
     * a parameter.
     * @param originalAmount the original currency amount in UK Pounds
     * @return the format of the string / the specific order these elements will appear
     */
    public String formatOfString(double originalAmount) {
        return String.format(
                "%s,%s,%f,%f,%s,%s",
                this.getCurrencyCode(),
                this.getCountryName(),
                this.getCurrencyUnitsPerEuro() * originalAmount,
                this.getCurrencyUnitsPerEuro(),
                this.getStartDate(),
                this.getEndDate());
    }

    /***
     * This is the overridden equals() method. It makes sure that there are no instances of object Currency
     * @param obj a variable object
     * @return this currencyCode is equal to that currencyCode
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Currency)) return false;

        var that = (Currency) obj;

        return this.currencyCode.equals(that.currencyCode);
    }

    /***
     * This is the overridden hashCode() method.
     * @return this currencyCode as a hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.currencyCode);
    }

    /***
     * This is the overridden toString() method.
     * @return string format for when using generic println statements
     */
    @Override
    public String toString() {
        return String.format(
                "%s,%s,%f,%s,%s",
                this.getCurrencyCode(),
                this.getCountryName(),
                this.getCurrencyUnitsPerEuro(),
                this.getStartDate(),
                this.getEndDate());
    }
}