import java.util.Random;
import java.util.Scanner;

public class Nicola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] playerNumbers;
        int[] wheelNumbers;
        int[] playerBetTypes;
        int[][] wheels = new int[10][5];

        int nWheels;

        System.out.println("inserisci su quante ruote vuoi giocare: ");
        nWheels = scanner.nextInt();

        for (int i = 0; i < nWheels; i++) wheels[i] = extractedWheel();

        wheelNumbers = extractedWheel();
        
        playerNumbers = playerNumbers();

        playerBetTypes = takePlayerBetypes();


    }


    private static double calculationWinningPrice(int[] playerNumbers, int[][] wheels, int[] playerBetTypes) {


        return 0;
    }

    private static int returnBetFromWheel(int[] playerNumbers, int[] wheelNumbers) {
        int betTypeOfWheel = 0;

        // For every number in playerNumbers check if in the array of the wheel
        // there is another which is equal.
        for (int playerNumber : playerNumbers) {
            for (int wheelNumber : wheelNumbers) {
                if (playerNumber == wheelNumber) betTypeOfWheel++; // update the bet counter
            }
        }

        return betTypeOfWheel;
    }

    private static int[] playerNumbers() {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        boolean[] n = new boolean[90];

        for (int i = 0; i < numbers.length; i++) {
            System.out.println("inserisci un numero: ");
            numbers[i] = scanner.nextInt();

            // if in the array of boolean there is a true it's indicate that there is already a number
            while (n[numbers[i] - 1]) {
                // Ask to reinsert the number
                System.out.println("Numero gia inserito. Iserisci un altro numero: ");
                numbers[i] = scanner.nextInt();
            }

            // Set the correct pos in the array to true to indicate that there is the number
            n[numbers[i] - 1] = true;
        }

        return numbers;
    }

    public static int[] extractedWheel() {
        int[] numeri = new int[5];
        int minValue = 1, maxValue = 90;

        for (int i = 0; i < numeri.length; i++) {
            numeri[i] = valoreRandom(minValue, maxValue); // Assign random value to numeri[i]

            if (i > 0) valueChecker(numeri, i, minValue, maxValue); // check if the value isn't repeating
        }

        return numeri; // return array
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

    public static double winningPrize(int amount, int wheels, int playedNumbers, int betType) {
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

        winning = prizes[playedNumbers][betType] * amount / wheels;

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
}