//Jamie Gashler
//Java Programming I
//Homework 8 - Exchange Rates

import java.util.Scanner;

/***
 * This is the main class of the program. It performs the main actions for the program which include displaying the menu,
 * asking for user input and displaying the corresponding responses.
 */
public class Main
{


    /***
     * This is the getString method, which validates whether or not the user input a string value
     * @param prompt prompts the user to enter a string value
     * @return the user input as a string
     */
    public static String getString(String prompt) {
        System.out.printf(prompt);
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextLine()) {
            scanner.nextLine();
            System.out.println("Invalid, try again");
            System.out.print(prompt);
        }
        return scanner.nextLine();
    }


    /***
     * This is the getDouble method, which validates whether or not the user input a double value
     * @param prompt prompts the user to enter a double value
     * @return the user input as a double
     */
    public static Double getDouble(String prompt) {
        System.out.printf(prompt);
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.println("Invalid, try again");
            System.out.print(prompt);
        }
        return scanner.nextDouble();
    }


    /** This is the main method of the program. It includes the launch to the main menu and initiates the CurrencySet
     * parsing the online file of the exchange rates.
     * @param args contains the supplied command-line arguments as an array of String objects
     */
    public static void main(String[] args) {
        var theWholeFile = CurrencyDAO.retrieveFile();
        var currencySet = CurrencySet.parse(theWholeFile);
              launch(currencySet);

        }


    /***
     * This method prompts the user with the start menu response options: convert currency or exit the program.
     * @return This returns a string as the user input for what menu response they input.
     */
    public static String getMenuResponse() {
        return getString("%nWould you like to (c)onvert currency or (q)uit? > ");
    }


    /***
     * This is the main menu of the program that contains the options the user can pick from as well as corresponding
     * responses. It validates the user input, and allows the user to export or convert their original currency amount.
     */
    public static void launch(CurrencySet currencySet) {
        var originalAmount = 0.0;
        while(true) {
            var menuResponse = getMenuResponse();
            if(menuResponse.equalsIgnoreCase("q")) {
                break;

            }else if(menuResponse.equalsIgnoreCase("c")) {

                 originalAmount = getDouble("Please enter an amount in UK Pounds > ");

            } else {
                System.out.printf("%n");
                System.out.println("Invalid Input, try again.");
                continue;
            }

            var response = getString("Would you like to (e)xport your data? or enter currency (t)ype? > ");


            if (response.equalsIgnoreCase("e")) {

                var filenameForCSV = Main.getString("Please input a filename to export your data > ");

                System.out.printf("%nHere is the information stored in your CSV file :%n");

                System.out.println(currencySet.formatOfString(originalAmount));
                currencySet.save(originalAmount, filenameForCSV);

            } else if(response.equalsIgnoreCase("t")) {
                String countryName = Currency.getTheCountryNameFromUser();
                var countryCurrency = currencySet.find(countryName);

                if (countryCurrency == null)
                {
                    System.out.printf("%nThis country does not exist!");

                } else{
                    Currency currency = currencySet.find(countryName);
                    var exchangeRate = currency.getCurrencyUnitsPerEuro();
                    var calculatedValue = currency.getCurrencyUnitsPerEuro() * originalAmount;
                    var nameOfCurrency = currency.getCurrencyName();
                    var startDateRange = currency.getStartDate();
                    var endDateRange = currency.getEndDate();

                    System.out.printf("%nThe original price is: %.2f UK Pounds%n", originalAmount);
                    System.out.printf("The the calculated value is: %.2f %s%n", calculatedValue, nameOfCurrency);
                    System.out.printf("The exchange rate is: %.2f%n", exchangeRate);
                    System.out.printf("The date range this is valid for is from: %s to %s %n", startDateRange, endDateRange);

                }

            } else {
                System.out.printf("%n");
                System.out.println("Invalid Input, try again.");

            }

        }

    }

}
