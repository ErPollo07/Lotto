import java.util.Random;
import java.util.Scanner;

public class Nicola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] specificWheelsMenu = {
                "Su quale ruota vuoi puntare?",
                "[1] - BARI",
                "[2] - CAGLIARI",
                "[3] - FIRENZE",
                "[4] - GENOVA",
                "[5] - MILANO",
                "[6] - NAPOLI",
                "[7] - PALERMO",
                "[8] - ROMA",
                "[9] - TORINO",
                "[10] - VENEZIA"
        };

        String[] numberOfWheelMenu = {
                "Su quante ruote vuoi puntare?",
                "[1] - Scelta libera di una ruota",
                "[2] - Tutte e 10 le ruote"
        };

        String[] betTypeMenu = {
                "Quale puntate vuoi scegliere?",
                "[1] - SINGOLO",
                "[2] - AMBO",
                "[3] - TERNA",
                "[4] - QUATERNA",
                "[5] - CINQUINA"
        };

        int[] playerNumbers; // Number of the player
        int[] playerBetTypes; // The bets of the player (singolo, ambo, terna, quaterna, cinquina)
        int[][] wheels = new int[10][5]; // The 2d array for store all the wheels

        int numberOfWheels; // How many wheels the player chooses to player on
        int whatWheel = 0; // number of the wheel that the player wants to play on
        int counterOfPlayedNumbers = 0; // The number of the numbers the player played
        double amount; // How much money the player has bet
        double price = 0; // How much money the player wins

        // Ask the player to insert the amount of money he wants to bet
        System.out.println("Inserisci l'importo: ");
        amount = scanner.nextInt();

        // Ask the player how many wheels he what to bet on
        numberOfWheels = takeNumberOfWheels(numberOfWheelMenu);

        // if the player wants to play on one wheel, then I make him choose which wheel
        if (numberOfWheels <= 1)
            whatWheel = takeSpecificWheel(specificWheelsMenu);

        // If the player chose to play on all the wheel, the whatWheel variable is 0.
        // Generate the wheel numbers
        if (whatWheel == 0) {
            for (int i = 0; i < numberOfWheels; i++)
                wheels[i] = extractedWheel();
        }
        else  {
            wheels[whatWheel] = extractedWheel();
        }

        // clrSrc

        // take the player numbers
        playerNumbers = takePlayerNumbers();

        /* calculate the number of numbers who player played */
        for (int i:playerNumbers) {
            if (i != 0) counterOfPlayedNumbers++;
            else break; // If the number is 0, the array is finished, so interrupt the cycle
        }
        /* END CALCULATION */

        // Take the bets of the player
        playerBetTypes = takePlayerBetTypes(counterOfPlayedNumbers, betTypeMenu);

        if (whatWheel == 0) {
            for (int i = 0; i < numberOfWheels; i++) {
                price += calculationWinningPrice(playerNumbers, wheels[i], playerBetTypes, amount, numberOfWheels, counterOfPlayedNumbers);
            }
        }
        else {
            price += calculationWinningPrice(playerNumbers, wheels[whatWheel], playerBetTypes, amount, numberOfWheels, counterOfPlayedNumbers);
        }

        System.out.println("Price: " + price);
    }




    /* --------------
     * PLAYER METHODS
     * --------------
     */

    private static int[] takePlayerNumbers() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        boolean[] n = new boolean[90];

        int number;

        System.out.println("Inserisci i numeri su cui vuoi scommettere.\nSe non vuoi inserire altri numeri inserisci 0.");

        for (int i = 0; i < numbers.length; i++) {
            // if the insert number is grader then 90, the program makes you enter the number again.
            do {
                System.out.print("\nInserisci un numero: ");
                number = scanner.nextInt();

                if (number > 90)
                    System.out.println("Devi inserire un numero tra 0 e 90");
            } while (number > 90);

            if (number >= 1) {
                // if in the array of boolean there is a true, it's indicating that there is already a number
                while (n[number - 1]) {
                    // Ask to reinsert the number
                    System.out.println("Numero gia inserito. Iserisci un altro numero: ");
                    number = scanner.nextInt();
                }

                // Set the correct pos in the array to true to indicate that there is the number
                n[number - 1] = true;

                numbers[i] = number;
            }
            else break; // If the player writes 0, the cycle stops
        }

        return numbers;
    }

    private static int[] takePlayerBetTypes(int playedNumbers, String[] menuOptions) {
        Scanner scanner = new Scanner(System.in);
        int[] betTypes = new int[5];
        boolean[] betChecker = new boolean[6];
        int userBet = 1;


        for (int i = 0; i < betTypes.length && userBet != 0; i++) {

            printMenu(menuOptions);

            do {
                System.out.println("\nInserisci scelta (inserisci 0 se vuoi smettere di inserire scelte): ");
                userBet = scanner.nextInt();


                /* ERROR MESSAGE */
                // check if the number it's available in the list of bet
                // if it's not available, tell him that isn't a correct number
                if (userBet < 0 || userBet > 5) {
                    System.out.println("Numero non valido. Inserire un numero tra 1 e 5");
                // if the player inserts a number which is already insert
                // tell him that the number is already insert
                } else if (betChecker[userBet]) {
                    System.out.println("Numero gia' inserito.");
                // if the player inserts a number, which is bigger than the numbers of numbers that he plays,
                // Tell him that the number he can't afford the bet type
                } else if (userBet > playedNumbers)
                    System.out.println(
                            "La quantitá dei numeri che hai inserito é troppo piccola per porter scegliere questa opzione."
                    );
                /* END ERROR MESSAGE */
            } while ((userBet  > playedNumbers) || (userBet < 0 || userBet > 5));

            // If the userBet is different from 0
            if (userBet != 0) {
                betTypes[userBet - 1] = userBet; // Insert the number in the correct place
                betChecker[userBet] = true; // set the checker for the insert number true
            }
        }

        return betTypes;
    }

    /* -------------
     * WHEEL METHODS
     * -------------
     */

    public static int[] extractedWheel() {
        int[] numbers = new int[5];
        int minValue = 1, maxValue = 6;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = randomValue(minValue, maxValue); // Assign random value to numbers[i]

            if (i > 0) valueChecker(numbers, i, minValue, maxValue); // check if the value isn't repeating
        }

        return numbers; // return array
    }

    private static int takeNumberOfWheels(String[] menuOption) {
        Scanner scanner = new Scanner(System.in);
        int numberOfWheels;

        do {
        printMenu(menuOption); // print the menu

        System.out.println("Inserisci la tua scelta: ");
        numberOfWheels = scanner.nextInt();

        // If the user types a wrong choice, tell him that it's wrong
        if (numberOfWheels != 1 && numberOfWheels != 2)
            System.out.println("Devi scegire 1 ruota o 10");
        } while (numberOfWheels != 1 && numberOfWheels != 2);

        return numberOfWheels;
    }

    private static int takeSpecificWheel(String[] menuOption) {
        Scanner scanner = new Scanner(System.in);
        int specificWheel;

        do {
            printMenu(menuOption); // print the menu

            System.out.println("Inserisci la tua scelta: ");
            specificWheel = scanner.nextInt();

            // If the user types a wrong choice, tell him that it's wrong
            if (specificWheel < 1 || specificWheel > 10)
                System.out.println("Devi scegire 1 ruota o 10");
        } while (specificWheel < 1 || specificWheel > 10);

        return specificWheel;
    }

    // Check if the value is available
    private static void valueChecker(int[] array, int i, int minValue, int maxValue) {
        for (int k = 0; k < i; k++) {
            // If the value is already in the array, reassign it and recheck all array
            if (array[i] == array[k]) {
                array[i] = randomValue(minValue, maxValue); // Assign another random value to array[i]
                k = -1; // Restart the check
            }
        }
    }

    /* ---------------
    * WINNING METHODS
    * ---------------
    */

    private static double calculationWinningPrice(int[] playerNumbers, int[] wheel, int[] playerBetTypes,
                                                  double amount, int numberOfWheels, int counterOfPlayedNumbers) {
        int betFromWheel;
        int howManyBet;
        double price = 0;

        betFromWheel = returnBetFromWheel(playerNumbers, wheel);

        // For every type of bet that the player bet on the cycle continue
        for (int i = 1; i < playerBetTypes.length; i++) {
            // Check if the betFromWheel is greater than i and if the player bet on i
            if (betFromWheel >= i && playerBetTypes[i - 1] != 0) {
                // only if the betFromWheel is equal to i add to the price the amount of cash that the player wins.
                if (betFromWheel == i)
                    price += winningPrize(amount, numberOfWheels, counterOfPlayedNumbers, playerBetTypes[i - 1]);

                // Cycle from betFromWheel to 1
                for (int j = betFromWheel; j >= 1; j--) {
                    if (playerBetTypes[j - 1] != 0) {
                        // Calculate all the possibility to do a bet minor then the effective bet
                        howManyBet = retriveWinAmount(j, betFromWheel);
                        price += winningPrize(amount, numberOfWheels, counterOfPlayedNumbers, j) * howManyBet;
                    }
                }

            }
        }

        return price;
    }

    private static int returnBetFromWheel(int[] playerNumbers, int[] wheelNumbers) {
        int betTypeOfWheel = 0;

        // For every number in playerNumbers check if in the array of the wheel there is another number that is equal to it.
        // If there is then update the bet counter
        for (int playerNumber : playerNumbers) {
            for (int wheelNumber : wheelNumbers) {
                if (playerNumber == wheelNumber)
                    betTypeOfWheel++; // update the bet counter
            }
        }

        return betTypeOfWheel;
    }

    public static double winningPrize(double amount, int numberOfWheels, int counterOfPlayedNumbers, int betType) {

        // 2d array for prizes if the player inserts 1 euro
        // The raw represent the played numbers of the player
        // The column represents the bet types
        double[][] prizes = {
                {11.23},
                {5.62, 250.00},
                {3.74, 83.33, 4500.00},
                {2.81, 41.67, 1125.00, 120000.00},
                {2.25, 25.00, 450.00,  24000.00, 6000000.00},
                {1.87, 16.67, 225.00,  8000.00,  1000000.00},
                {1.60, 11.90, 128.57,  3428.57,  285714.29},
                {1.40, 8.93,  80.36,   1714.29,  107142.86},
                {1.25, 6.94,  53.57,   952.38,   47619.05},
                {1.12, 5.56,  37.50,   571.43,   23809.52}
        };

        double winning;

        // Calculate the winning
        winning = prizes[counterOfPlayedNumbers - 1][betType - 1] * amount / numberOfWheels;

        return winning;
    }

    /*
    userWins = player numbers equals to the wheel
    betType = sing, ambo, terna, quaterna, cinquina
    */
    private static int retriveWinAmount(int betType, int userWins) {
        return (factorial(userWins)) / (factorial(betType) * factorial(userWins - betType));
    }

    /* Give the factorial of a number */
    private static int factorial(int input) {
        for (int inputMultiplier = input; inputMultiplier > 0; inputMultiplier--)
            input *= inputMultiplier;

        return input;
    }

    /* --------------
    * SERVICE METHODS
    * ---------------
    */

    private static void printMenu(String[] option) {
        clrScr();

        System.out.println("=============");
        System.out.println(option[0]);
        System.out.println("=============");

        for (int i = 1; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }

    private static int randomValue(int minValue, int maxValue)
    {
        Random random = new Random();

        return  random.nextInt(minValue,maxValue+1);
    }

    private static void clrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    private static void wait(int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}