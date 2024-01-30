import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* NON TOCCATE QUESTO FILE */
        Scanner scanner = new Scanner(System.in);


        /* Var for */
        String[] menuPuntante = {

        };

        String[] menuSceltaDovePuntare = {

        };

        String[] menuRuote = {

        };

        int[] wheelBari = new int[5];
        int[] wheelCagliari = new int[5];
        int[] wheelFirenze = new int[5];
        int[] wheelGenova = new int[5];
        int[] wheelMilano = new int[5];
        int[] wheelNapoli = new int[5];
        int[] wheelPalermo = new int[5];
        int[] wheelRoma = new int[5];
        int[] wheelTorino = new int[5];
        int[] wheelVenezia = new int[5];

        int[] playerNumbers = new int[10];

        double amount;

        // Take amount

        System.out.println("Inserisci l'importo: ");
        amount = scanner.nextInt();

        // Take player numbers
        takePlayerNumbers();

        // Ask the player if he wants to be bet (vuole puntare) in: singolo, ambo, terna ...

        // Calculate the win
    }

    private static void takePlayerNumbers() {

    }
}