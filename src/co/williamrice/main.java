package co.williamrice;


import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Random;


class Main{
    public static void main(String[] args){
        final int RANDOM_MIN = 1000;
        final int RANDOM_MAX = 3999;
        final int POPCORN_PRICE = 450;
        final double POPCORN_PRICE_AS_CURRENCY = POPCORN_PRICE / 100.0;
        final int SOFT_DRINK_PRICE = 100;
        final double SOFT_DRINK_PRICE_AS_CURRENCY = SOFT_DRINK_PRICE / 100.0;
        final String VENDOR_NAME = "Kandy's Kountry Kettle Korn";

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        DecimalFormat currencyFormat = new DecimalFormat("$###,##0.00"); 

        System.out.println("** Welcome to the receipt generator application. **");
        System.out.println("***************************************************\n\n");
        System.out.println("Please read the directions carefully and enter your data when prompted...\n");

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

        double rawTotalCost = ((numberOfPopcornPurchased * POPCORN_PRICE) + (numberOfDrinksPurchased * SOFT_DRINK_PRICE)) / 100.0;
        String formattedTotalCost = currencyFormat.format(rawTotalCost);


        int randomNumberForConfirmation = rand.nextInt(RANDOM_MAX) + RANDOM_MIN;
        String confirmationNumber = "POPCORN#" + buyerLastName.toUpperCase() + "#" + buyerFirstName.toUpperCase().substring(0,1) + "#" +
                                    randomNumberForConfirmation + "#" + streetVendorId;

        String parsedDate = String.format("%s-%s-%s", dateOfPurchase.substring(0, 2),
                                                      dateOfPurchase.substring(2, 4),
                                                      dateOfPurchase.substring(4));
        
         
        System.out.println("\n");

        System.out.println("** " + VENDOR_NAME + " **");
        System.out.println();

        System.out.println("Confirmation for " + buyerFirstName + " " + buyerLastName);
        System.out.println();

        System.out.println("Confirmation Number: " + confirmationNumber);
        System.out.println();

        String popCornHelperString = String.format("%d @ %s each", numberOfPopcornPurchased,
                                                                   currencyFormat.format(POPCORN_PRICE_AS_CURRENCY));

        System.out.println(String.format("%-20s %-20s", "Popcorn:", popCornHelperString));

        String softDrinkHelperString = String.format("%d @ %s each", numberOfDrinksPurchased,
                                                                     currencyFormat.format(SOFT_DRINK_PRICE_AS_CURRENCY));
        
        System.out.println(String.format("%-20s %-20s", "Soft Drinks:", softDrinkHelperString));

        System.out.println();
        System.out.println(String.format("%-20s %-20s", "TOTAL:", formattedTotalCost));

        System.out.println();
        System.out.println("Thanks for visiting our booth on " + parsedDate);
        

        


    }
}
