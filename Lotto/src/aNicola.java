import java.util.Random;
import java.util.Scanner;

public class aNicola {
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

        clrScr();
        printLottoWord();

        // Ask the player to insert the amount of money he wants to bet
        amount = takeAmount();

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
            wheels[whatWheel - 1] = extractedWheel();
        }

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

        // if the player choose to play on all the wheels whatWheel will be 0 so calculate the winning prize for all the wheel
        if (whatWheel == 0) {
            System.out.println("Ecco le ruote: \n");
            for (int i = 0; i < numberOfWheels; i++) {
                printWheel(wheels[i]);
                System.out.println("\n");
                price += calculationWinningPrice(playerNumbers, wheels[i], playerBetTypes, amount, numberOfWheels, counterOfPlayedNumbers);
            }
        }
        // if the player choose to play on specific wheel the whatWheel number will be a number so calculate the prize for that specific wheel
        else {
            System.out.println("\n\nEcco la ruota che hai scelto: ");
            printWheel(wheels[whatWheel - 1]);
            price += calculationWinningPrice(playerNumbers, wheels[whatWheel - 1], playerBetTypes, amount, numberOfWheels, counterOfPlayedNumbers);
        }

        System.out.println("\n\nEcco i tuoi numeri: ");
        printWheel(playerNumbers);


        System.out.println("\n\nHai vinto: " + price + " euro");
    }

    /* --------------
     * PLAYER METHODS
     * --------------
     */

    /*
    This function return the amount which the player wants to bet
    */
    private static double takeAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount;

        do {
            System.out.println("Inserisci importo: ");
            amount = scanner.nextInt();

            // if the player insert a number out of range, tell him that is a not vvalid number
            if (amount < 0 || amount > 200)
                System.out.println("L'importo deve essere compreso tra 0 e 200.");
        } while(amount < 0 || amount > 200);

        return amount;
    }

    // This function return the array of numbers who player wants to bet
    private static int[] takePlayerNumbers() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        boolean[] numbersChecker = new boolean[90];
        boolean correctInserction, continueToInsert = true;

        int number;

        clrScr();
        printLottoWord();

        System.out.println("Inserisci i numeri su cui si vuole scommettere.\nSe non vuoi inserire altri numeri inserire 0.");

        for (int i = 0; i < numbers.length && continueToInsert; i++) {
            do {
                correctInserction = true;

                // Ask the player to insert a number
                System.out.print("\nInserisci un numero (inserire 0 per smettere di inserire): ");
                number = scanner.nextInt();

                /* ERROR MESSAGE */
                // Check if the number is different from 0
                if (number != 0) {
                    // Check if the number is in the correct interval
                    if (number < 0 || number > 90) {
                        System.out.println("Devi inserire un numero tra 1 e 90");
                        correctInserction = false;
                    }
                    // check if the number is already insert
                    else if (numbersChecker[number - 1]) {
                        System.out.println("Numero gia inserito.");
                        correctInserction = false;
                    }
                    // if the number is in the right interval and isn't already insert 
                    else {
                        numbersChecker[number - 1] = true; // set the variable of the number to true,
                        // so it means that is already insert
                        numbers[i] = number; // put the number in the right place
                    }
                }
                /* END ERROR MESSAGE */
                else
                    continueToInsert = false;
            } while(!correctInserction);
        }

        return numbers;
    }

    // This function return the array that contains all the player betTypes 
    private static int[] takePlayerBetTypes(int playedNumbers, String[] menuOptions) {
        Scanner scanner = new Scanner(System.in);
        int[] betTypes = new int[5];
        boolean[] betChecker = new boolean[5];
        int userBet;
        boolean continueToInsert = true, correctInserction;

        clrScr();
        printLottoWord();

        printMenu(menuOptions);

        for (int i = 0; i < betTypes.length && continueToInsert; i++) {
            do {
                correctInserction = true;

                System.out.println("\nInserisci scelta (inserisci 0 per smettere di inserire): ");
                userBet = scanner.nextInt();

                // Check if the number is different from 0
                if (userBet != 0) {
                    // check if the number it's available in the list of bet
                    // if it's not available, tell him that isn't a correct number
                    if (userBet < 0 || userBet > 5) {
                        System.out.println("Devi inserire un numero tra 1 e 5");
                        correctInserction = false;
                    }
                    // if the player inserts a bet which is already insert
                    // tell him that the number is already insert
                    else if (betChecker[userBet - 1]) {
                        System.out.println("Numero gia inserito.");
                        correctInserction = false;
                    }
                    // if the player inserts a number, which is bigger than the numbers of numbers that he plays,
                    // Tell him that the number he can't afford the bet type
                    else if (userBet > playedNumbers) {
                        System.out.println(
                                "La quantita' dei numeri che hai inserito e' troppo piccola per porter scegliere questa opzione."
                        );
                        correctInserction = false;
                    }
                    // if the number is in the correct interval, isn't already insert and is minor then the count of played numbers
                    else {
                        betChecker[userBet - 1] = true; // set the variable of the number to true,
                        // so it means that is already insert
                        betTypes[userBet - 1] = userBet; // put the number in the right place
                    }
                }
                /* END ERROR MESSAGE */
                else
                    continueToInsert = false;
            } while (!correctInserction);
        }

        return betTypes;
    }

    /* -------------
     * WHEEL METHODS
     * -------------
     */


    /**
    This function return if the player wants to play on 1 or 10 wheels
    @param menOption array of string for the menu
    */
    private static int takeNumberOfWheels(String[] menuOption) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int numberOfWheels;
        boolean correctInserction;

        clrScr();
        printLottoWord();

        printMenu(menuOption);

        do {
            correctInserction = true;

            System.out.print("\nInserisci la tua scelta: ");
            choice = scanner.nextInt();

            // If the user types a wrong choice, tell him that it's wrong
            if (choice != 1 && choice != 2) {
                System.out.println("Puoi scegliere solo 1 o 2");
                correctInserction = false;
            }
        } while (!correctInserction);

        // if the choice is 2
        if (choice == 2)
            numberOfWheels = 10; // set number of wheel to ten
        // if the player wnats to bet on 1 wheel the choice is 1, so set the nummberOfWheel equal to choice
        else
            numberOfWheels = choice;

        return numberOfWheels;
    }

    // This function 
    private static int takeSpecificWheel(String[] menuOption) {
        Scanner scanner = new Scanner(System.in);
        int specificWheel;
        boolean correctInserction;

        do {
            correctInserction = true;

            clrScr();
            printLottoWord();

            printMenu(menuOption); // print the menu

            System.out.println("\nInserisci la tua scelta: ");
            specificWheel = scanner.nextInt();

            // If the user types a wrong choice, tell him that it's wrong
            if (specificWheel < 1 || specificWheel > 10) {
                System.out.println("Devi scegire 1 ruota o 10");
                correctInserction = false;
            }
        } while (!correctInserction);

        return specificWheel;
    }

    public static int[] extractedWheel() {
        int[] numbers = new int[5];
        int minValue = 1, maxValue = 7;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = randomValue(minValue, maxValue); // Assign random value to numbers[i]

            if (i > 0) valueChecker(numbers, i, minValue, maxValue); // check if the value isn't repeating
        }

        return numbers; // return array
    }
    private static void printWheel(int[] array) {
        for (int i:array) {
            if (i == 0)
                break;
            System.out.print(i + " ");
        }
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

        if (betFromWheel == 0)
            return price;

        for (int i = betFromWheel; i >= 1; i--) {

            // I have guessed 4 numbers, but I have bet on ambo.
            // So because guessed 4 numbers, I have done 6 ambo.
            // This for return the winning of the 6 ambo.
            if (playerBetTypes[i - 1] != 0) {
                // Calculate all the possibility to do a bet minor then the effective bet
                howManyBet = retriveWinAmount(i, betFromWheel);
                price += winningPrize(amount, numberOfWheels, counterOfPlayedNumbers, i) * howManyBet;
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
        return factorial(userWins) / (factorial(betType) * factorial(userWins - betType));
    }

    /* Give the factorial of a number */
    private static int factorial(int input) {
        if (input <= 1) {
            return 1;
        }

        int result = 1;

        for (int inputMultiplier = 2; inputMultiplier <= input; inputMultiplier++) {
            result *= inputMultiplier;
        }

        return result;
    }

    // counterOfPlayedNummbers / 90 = prob. singolo
    // counterOfPlayedNummbers / 90 * 89 = prob. ambo
    // counterOfPlayedNummbers / 90 * 89 * 88 = prob. terna
    // counterOfPlayedNummbers / 90 * 89 * 88 * 87 = prob. quaterna
    // counterOfPlayedNummbers / 90 * 89 * 88 * 87 * 86 = prob. cinquina
    private static double winningProbability() {
        
    }

    /* --------------
    * SERVICE METHODS
    * ---------------
    */

    private static void printMenu(String[] option) {
        System.out.println("=============");
        System.out.println(option[0]);
        System.out.println("=============");

        for (int i = 1; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }

    private static void printLottoWord() {
        System.out.println(
                "          ..:---::.         .:----:..         .:----:.         ..:----:.         .::---:..          \n" +
                "        :-=+++++++=-:.   .:=++++++++=-.    .-=++++++++=-.    :-=++++++++=:.   .:==+++++++=-:        \n" +
                "      .=++-:.   .:-=+=:.:=+=-..   .:=++-..-++=:..  ..:=+=-..=++=:.   .:-=+=: :=+=-:.   .:-++=.      \n" +
                "     :++-. =*=     .-++=++-. :-::--. .=+==+=:.-++++++-.:=+=++=..=++++++:.-++=++-.         .=++:     \n" +
                "    .=+-.  +**       -+++: .===+==+*=..=++=.  -++**++-  .+++=. .=+***++:  -+++:   .-=+=:.  .=+=.    \n" +
                "    :++.   +**       .++= .+=++=-+*-*+ :++=     -**-     =++:     =**.    .+++.   +*****=   :++:    \n" +
                "    -++.   +**        ++= .*-*=..-++== :++=     -**-     =++:     =**.     =++    ******=   .++-    \n" +
                "    :++-   +**:::.   :+++..+*==+-=*-+. -++=.    -**-    .=++-     =**.    :+++:   -****+:   -++:    \n" +
                "     =++:  +******  :=+++=..-++++===. -++++=.   :**:   .=++++:    =**.   .=+++=.           :++-     \n" +
                "     .-++-..:::::..-++=:=+=-..::.. .:=++-:=+=:. .--. .:=+=:-++-:. .-: ..-=+=:=+=-..     .:-++-.     \n" +
                "       :=+++=====+++=:. .:=++======+++-.  .-+++======++=-.  :=+++======++=:. .:=++======+++=:       \n" +
                "         .:--====-:..     .:--====--:.      .:--====--:.      .:--====--..     .:--====--:.         \n"
        );

        System.out.println("\n\n");
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
