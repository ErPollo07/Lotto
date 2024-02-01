import java.util.Scanner;
public class Michele {
    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);
        int numberOfWheels=0;

        MenuWheels(keyboard, numberOfWheels);
    }
    private static int MenuWheels(Scanner keyboard, int numberOfWheels) {
        int[] Array = new int[9];
        int wheel = 0;
        String[] menuWheels = {"        ..:---::.         .:----:..         .:----:.         ..:----:.         .::---:..          \n" +
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
                "         .:--====-:..     .:--====--:.      .:--====--:.      .:--====--..     .:--====--:.         \n"};
        String[] menuWheelsChoice = {"[1] - Scelta libera di una ruota\n" + "[2] - Tutte e 10 le ruote\n"};
        String[] wheelsCity = {"[1] - BARI\n" + "[2] - CAGLIARI\n" + "[3] - FIRENZE\n" + "[4] - GENOVA\n" + "[5] - MILANO\n" +
                "[6] - NAPOLI\n" + "[7] - PALERMO\n" + "[8] - ROMA\n" + "[9] - TORINO\n" + "[10] - VENEZIA\n"};

        do {
            for (int i = 0; i < menuWheels.length; i++) {
                System.out.println(menuWheels[i]);
            }
            for (int i = 0; i < menuWheelsChoice.length; i++) {
                System.out.println(menuWheelsChoice[i]);
            }
            numberOfWheels = keyboard.nextInt();

            if (numberOfWheels < 1 || numberOfWheels > 2) {
                System.out.println("Numero non valido, riprova");
                for (int i = 0; i < menuWheelsChoice.length; i++) {
                    System.out.println(menuWheelsChoice[i]);
                }
            } else {
                if (numberOfWheels == 1) {
                    do {
                        for(int i=0; i< wheelsCity.length; i++){
                            System.out.println(wheelsCity[i]);
                        }
                        wheel = keyboard.nextInt();
                        if (wheel < 1 || wheel > 10) {
                            System.out.println("Numero non valido, riprova");
                        } else {
                            return Array[numberOfWheels];
                        }
                    } while (wheel < 1 || wheel > 10);
                } else {
                    return Array[numberOfWheels];
                }
            }
        } while (numberOfWheels < 1 || numberOfWheels > 2);
        return Array[wheel];
    }
}
        /*
        if(numbersOfWheels == 1)
            return [numbersOfWheels, wheel];
        if(nubersOfwheels == 10)
            return [numbersOfWheels, 0];
         */