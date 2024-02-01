import java.util.Scanner;
public class Michele {
    public static void main(String[] args) {
        Scanner keyboard=new Scanner(System.in);

        MenuWheels(keyboard);
        BetSelectionmenu(keyboard);
    }
    private static int MenuWheels(Scanner keyboard) {       //Menu per la scelta delle ruote      //ClrScr
        String[] lottoWord = {"         ..:---::.         .:----:..         .:----:.         ..:----:.         .::---:..          \n" +
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
        int[] Array = new int[9];           //Non sapevo come chiamare l'array quindi fai tu
        int wheel = 0;
        int numberOfWheels=0;

        String[] menuWheelsChoice = {
                "[1] - Scelta libera di una ruota\n" +
                "[2] - Tutte e 10 le ruote\n"};
        String[] wheelsCity = {
                "[1] - BARI\n",
                "[2] - CAGLIARI\n",
                "[3] - FIRENZE\n",
                "[4] - GENOVA\n",
                "[5] - MILANO\n",
                "[6] - NAPOLI\n",
                "[7] - PALERMO\n",
                "[8] - ROMA\n",
                "[9] - TORINO\n",
                "[10] - VENEZIA"};

        do {
            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
            for (int i = 0; i < lottoWord.length; i++) {
                System.out.println(lottoWord[i]);
            }
            for (int i = 0; i < menuWheelsChoice.length; i++) {
                System.out.println(menuWheelsChoice[i]);
            }
            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
            numberOfWheels = keyboard.nextInt();

            if (numberOfWheels < 1 || numberOfWheels > 2) {
                System.out.println("Numero non valido, riprova"); //ClrScr
                for (int i = 0; i < menuWheelsChoice.length; i++) {
                    System.out.println(menuWheelsChoice[i]);
                }
                System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
            } else {
                if (numberOfWheels == 1) {
                    do {
                        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-"); //ClrScr
                        for (int i = 0; i < lottoWord.length; i++) {
                            System.out.println(lottoWord[i]);
                        }
                        for(int i=0; i< wheelsCity.length; i++){
                            System.out.println(wheelsCity[i]);
                        }
                        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
                        wheel = keyboard.nextInt();
                        if (wheel < 1 || wheel > 10) {                          //Modifica qui perchè quello che mi hai detto non l'ho capito
                            System.out.print("Numero non valido, riprova");     //In sisntesi in base alla scelta, viene messe nell'array
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
                /*
        if(numbersOfWheels == 1)
            return [numbersOfWheels, wheel];
        if(nubersOfwheels == 10)
            return [numbersOfWheels, 0];
         */
    }
    private static void BetSelectionmenu(Scanner keyboard){         //Menu per la scelta delle puntate
        int bet=0;
        String[] lottoWord = {"        ..:---::.         .:----:..         .:----:.         ..:----:.         .::---:..          \n" +
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
        String[] betselection= {
                "[1] - SINGOLO",
                "[2] - AMBO",
                "[3] - TERNA",
                "[4] - QUATERNA",
                "[5] - CINQUINA"
        };
        //ClrScr
        do {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
        for(int i =0; i<lottoWord.length; i++){
            System.out.println(lottoWord[i]);
        }
        for(int i=0; i< betselection.length; i++){
            System.out.println(betselection[i]);
            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-");
        }
            bet = keyboard.nextInt();
            if(bet<1 || bet>5){
                //ClrSrc
                System.out.println("Numero non valido, riprova");
            }
        } while(bet<1 || bet>5);
    }
}