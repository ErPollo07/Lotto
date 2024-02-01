import java.util.Random;
import java.util.Scanner;

public class Nicola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] playerNumbers; // Number of the player
        int[] playerBetTypes; // The bets of the player (singolo, ambo, terna, quaterna, cinquina)
        int[][] wheels = new int[10][5]; // The 2d array for store all the wheels

        int numberOfWheels; // How many wheels the player chooses to player on
        int counterOfPlayedNumbers = 0; // The number of the numbers the player played
        double amount; // How much money the player has bet
        double price = 0; // How much money the player wins

        // Ask the player to insert the amount of money he wants to bet
        System.out.println("Inserisci l'importo: ");
        amount = scanner.nextInt();

        /*
        quante ruote
        - 1
            - elenco ruote
        - 10

        if (numberOfWheels == 1)
            returnArray = {numberOfWheels, wheel};
            returnArray[0] = numberOfWheels;
            returnArray[1] = wheel;
        if (numberOfWheels == 10)
            returnArray = {numberOfWheels, 0};

        return returnArray;
        */


        // Ask the player how many wheels he what to bet on
        System.out.println("inserisci su quante ruote vuoi giocare: ");
        numberOfWheels = scanner.nextInt();

        // Generate the wheel numbers
        for (int i = 0; i < numberOfWheels; i++) wheels[i] = extractedWheel();

        // take the player numbers
        playerNumbers = takePlayerNumbers();

        /* calculate the number of numbers who player played */
        for (int i:playerNumbers) {
            if (i != 0) counterOfPlayedNumbers++;
            else break; // If the number is 0, the array is finished, so interrupt the cycle
        }
        /* END CALCULATION */

        // Take the bets of the player
        playerBetTypes = takePlayerBetypes();

        for (int i = 0; i < numberOfWheels; i++) {
            price += calculationWinningPrice(playerNumbers, wheels[i], playerBetTypes, amount, numberOfWheels, counterOfPlayedNumbers);
        }

        System.out.println("Price: " + price);
    }


    private static double calculationWinningPrice(int[] playerNumbers, int[] wheel, int[] playerBetTypes,
                                                  double amount, int numberOfWheels, int counterOfPlayedNumbers) {
        int betFromWheel;
        double price = 0;

        betFromWheel = returnBetFromWheel(playerNumbers, wheel);

        // For every type of bet that the player bet on the cycle continue
        for (int i = 1; i < playerBetTypes.length; i++) {
            // If the player bet in a specific bet type, so the number in the array is different form 0
            if (betFromWheel == i && playerBetTypes[i - 1] != 0) {
                price += winningPrize(amount, numberOfWheels, counterOfPlayedNumbers, playerBetTypes[i - 1]);
            }
        }

        return price;
    }

    private static int returnBetFromWheel(int[] playerNumbers, int[] wheelNumbers) {
        int betTypeOfWheel = 0;

        // For every number in playerNumbers check if in the array of the wheel
        // there is another that is equal.
        for (int playerNumber : playerNumbers) {
            for (int wheelNumber : wheelNumbers) {
                if (playerNumber == wheelNumber) betTypeOfWheel++; // update the bet counter
            }
        }

        return betTypeOfWheel;
    }

    private static int[] takePlayerNumbers() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        boolean[] n = new boolean[90];

        int number;

        for (int i = 0; i < numbers.length; i++) {
            System.out.println("inserisci un numero: ");
            number = scanner.nextInt();

            if (number > 0) {
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
        }

        return numbers;
    }

    public static int[] extractedWheel() {
        int[] numbers = new int[5];
        int minValue = 1, maxValue = 20;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = valoreRandom(minValue, maxValue); // Assign random value to numbers[i]

            if (i > 0) valueChecker(numbers, i, minValue, maxValue); // check if the value isn't repeating
        }

        return numbers; // return array
    }

    // Check if the value is available
    private static void valueChecker(int[] array, int i, int minValue, int maxValue) {
        for (int k = 0; k < i; k++) {
            // If the value is already in the array, reassign it and recheck all array
            if (array[i] == array[k]) {
                array[i] = valoreRandom(minValue, maxValue); // Assign another random value to array[i]
                k = -1; // Restart the check
            }
        }
    }

    private static int valoreRandom(int minValue, int maxValue)
    {
        Random casuale = new Random();

        return  casuale.nextInt(minValue,maxValue+1);
    }

    private static int[] takePlayerBetypes() {
        Scanner scanner = new Scanner(System.in);
        int[] betTypes = new int[5];
        int userBet = 1;


        for (int i = 0; i < betTypes.length && userBet != 0; i++) {
            do {
                System.out.println("Inserisci il " + (i+1) + " numero: ");
                userBet = scanner.nextInt();

                if (userBet < 0 || userBet > 5) System.out.println("Numero non valido");
            } while (userBet < 0 || userBet > 5);

            // If the userBet is different from 0
            if (userBet != 0)
                betTypes[userBet - 1] = userBet; // Insert in the correct place the number
        }

        return betTypes;
    }

    public static double winningPrize(double amount, int numberOfWheels, int counterOfPlayedNumbers, int betType) {
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

    private static void ClrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    private static void Wait(int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}