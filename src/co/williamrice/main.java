/*******************************************************************************
 *  Name: William Rice
 *  Created Date: 9/9/2021
 *  Instructor: Krishna Nandanoor
 *  Class: CIT 149
 *  Purpose: The purpose of this program is to accept user input about a transaction
 *  that occurred at a vendor and display a receipt with a generated confirmation number,
 *  information about the transaction and buyer, the total, and the date.
 *  Special Notes: This program deals with currency. In order to preserve the accuracy of money
 *  and due to the program being relatively simple, we will be keeping track of money as integer
 *  value pennies. For example, 450 is stored to represent $4.50. We will convert to a dollar value
 *  for output by dividing the value by 100.0. The value is then formatted to a format appropriate for
 *  US currency. 
 *
 *******************************************************************************
 */

/*******************************************************************************
 * Package declaration section
 *******************************************************************************
 */
package co.williamrice;

/*******************************************************************************
 * Import declaration section
 *******************************************************************************
 */
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;

/*******************************************************************************
 * The following is a Main class that will contain the main method
 *******************************************************************************
 */
class Main{

     /*******************************************************************************
     * Main method section
     * This Main method is the entry point for this Java application
     *******************************************************************************
     */
    public static void main(String[] args){

        /*******************************************************************************
         * Declaration and assignment of constant values used in the program
         *******************************************************************************
         */
        final int RANDOM_MIN = 1000;
        final int RANDOM_MAX = 3999;
        final int POPCORN_PRICE = 450;
        final double POPCORN_PRICE_AS_DECIMAL = POPCORN_PRICE / 100.0;
        final int SOFT_DRINK_PRICE = 100;
        final double SOFT_DRINK_PRICE_AS_DECIMAL = SOFT_DRINK_PRICE / 100.0;
        final String VENDOR_NAME = "Kandy's Kountry Kettle Korn";

        /*******************************************************************************
         * Instantion of objects used in program
         *******************************************************************************
         */
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        DecimalFormat currencyFormat = new DecimalFormat("$###,##0.00"); 

        /*******************************************************************************
         * Program greeting and instructions
         *******************************************************************************
         */
        System.out.println("** Receipt Generator Application. **");
        System.out.println("************************************\n\n");
        System.out.println("Please read the directions carefully and enter your data when prompted...\n");

        /*******************************************************************************
         * Data prompts and user input section
         * 
         * Special Notes: Pretty standard and straight forward user input section. We do 
         * declare the variables to hold the data as we accept the input from the user. The Scanner
         * nextLine method that isn't assigned to a variable is used to clear the string buffer.  
         *******************************************************************************
         */
        System.out.println("Enter the date of purchase in the format of mmddyyyy (No spaces: ex. 09172016): " );
        String dateOfPurchase = scanner.nextLine();

        System.out.println("Enter the buyer's first name: ");
        String buyerFirstName = scanner.nextLine();

        System.out.println("Enter the buyer's last name: ");
        String buyerLastName = scanner.nextLine();

        System.out.println("Enter how many bags of popcorn the buyer purchased: ");
        int numberOfPopcornPurchased = scanner.nextInt();

        System.out.println("Enter how many drinks the buyer purchased: ");
        int numberOfDrinksPurchased = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter the street vendor's 5 digit ID");
        String streetVendorId = scanner.nextLine();

        /*******************************************************************************
         * Total calculation and formatting of total cost
         * 
         * Special Notes: The raw total cost is calculated as an integer and then implicitly cast
         * to a double as it is divided by 100.0 and assigned to a rawTotalCost variable of type double
         * We use the DecimalFormat object that was instantiated ealier in the program to format our 
         * total cost as US currency and stored in a String for future output 
         *******************************************************************************
         */
        double rawTotalCost = ((numberOfPopcornPurchased * POPCORN_PRICE) + (numberOfDrinksPurchased * SOFT_DRINK_PRICE)) / 100.0;
        String formattedTotalCost = currencyFormat.format(rawTotalCost);

        /*******************************************************************************
         * Confirmation number generation 
         * 
         * Special Notes: In the constants section above, a RANDOM_MAX and RANDOM_MIN is 
         * declared and assigned the values 3999 and 1000 respectively. The logic behind this
         * is to obtain a random integer between the values of 0 and 3999. We then add 1000 to 
         * that random integer to be in compliance with the requirements that the value be random 
         * between 1000 and 4999. 
         * We then format the confirmation number to match the requirements of a proper confirmation number. 
         * For example, POPCORN#TUCKER#C#3418#AB18A , is the example template provided. 
         *******************************************************************************
         */
        int randomNumberForConfirmation = rand.nextInt(RANDOM_MAX) + RANDOM_MIN;
        String confirmationNumber = "POPCORN#" + buyerLastName.toUpperCase() + "#" + buyerFirstName.toUpperCase().substring(0,1) + "#" +
                                    randomNumberForConfirmation + "#" + streetVendorId;

        
         /*******************************************************************************
         * Parsing the date entered by the user
         * 
         * Special Notes: Since we have very strict rules pertaining to the way that the user
         * enters the date. The following algorithm requires that the user follow those directions.
         * We substring each piece of the date and use the String class format method to insert the
         * substrings parts into the correct place
         *******************************************************************************
         */
        String parsedDate = String.format("%s-%s-%s", dateOfPurchase.substring(0, 2),
                                                      dateOfPurchase.substring(2, 4),
                                                      dateOfPurchase.substring(4));
        
        /*******************************************************************************
         * Printing the receipt out to the console (Top half of receipt)
         * 
         * Special Notes: Standard string concatenation to insert values into strings. A 
         * blank line is printed between the prints to match the required output and for user
         * readability. 
         *******************************************************************************
         */
        System.out.println("\n");

        System.out.println("** " + VENDOR_NAME + " **");
        System.out.println();

        System.out.println("Confirmation for " + buyerFirstName + " " + buyerLastName);
        System.out.println();

        System.out.println("Confirmation Number: " + confirmationNumber);
        System.out.println();

        /*******************************************************************************
         * Printing the receipt out to the console (Bottom half of receipt)
         * 
         * Special Notes: The bottom half of the receipt requires more formatting in order
         * to acheive consistent spacing. We make use of the String format() method. We use the
         * formatter %-20s to assign 20 character spaces and left align the String value. This will 
         * ensure that the output is properly aligned. By using this method, we create a formatted
         * helper string to hold the data about how many of each item was purchase. I chose to do 
         * this as opposed to using a string literal for the prices in order to have the ability to 
         * change the prices at the top of the file and not have to update the pricing everywhere. 
         * 
         *******************************************************************************
         */
        String popCornHelperString = String.format("%d @ %s each", numberOfPopcornPurchased,
                                                                   currencyFormat.format(POPCORN_PRICE_AS_DECIMAL));

        System.out.println(String.format("%-20s %-20s", "Popcorn:", popCornHelperString));

        String softDrinkHelperString = String.format("%d @ %s each", numberOfDrinksPurchased,
                                                                     currencyFormat.format(SOFT_DRINK_PRICE_AS_DECIMAL));
        
        System.out.println(String.format("%-20s %-20s", "Soft Drinks:", softDrinkHelperString));

        System.out.println();
        System.out.println(String.format("%-20s %-20s", "TOTAL:", formattedTotalCost));

        System.out.println();
        System.out.println("Thanks for visiting our booth on " + parsedDate);
    }
}
