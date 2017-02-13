package consultoria;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int option;
        String hash, password;
        do {
            Interface.cleanScreen();
            System.out.print("Enter the password hash: ");
            hash = in.nextLine();
            password = null;
            option = Interface.calcPasswordOption();
            switch (option) {
            case 1:
                Interface.cleanScreen();
                password = Interface.lookupDictionary(hash, "./dicRealAcademia.txt");
                Interface.showPassword(password);
                Interface.pressKey();
                break;
            case 2:
                Interface.cleanScreen();
                password = Interface.lookupDictionary(hash, "./dicSimpl.txt");
                Interface.showPassword(password);
                Interface.pressKey();
                break;
            case 3:
                Interface.cleanScreen();
                Interface.pressKey();
                break;
            }
        } while (option != 4);
    }
}
