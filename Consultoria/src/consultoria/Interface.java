package consultoria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interface {
    public static int calcPasswordOption() {
        Scanner in = new Scanner(System.in);
        String[] options = {
            "Real Academia Dictionary",
            "Simple Word Dictionary",
            "Brute Force entre parentesis",
            "Exit"};
        System.out.println("");
        for (int i = 0; i < options.length; i++) {
            System.out.println("\t" + (i + 1) + ") " + options[i]);
        }
        System.out.println("");
        System.out.print("Please enter the option: ");
        int option = in.nextInt();
        return option;
    }
    
    public static String lookupDictionary(String hash, String dic){
        String password = null;
        try {
            password = Utility.lookupPassword(hash, "SHA-1", dic);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Cannont find the hashing algorithm.");
        } catch(FileNotFoundException ex) {
            System.out.println("Cannont find the file.");
        } catch(UnsupportedEncodingException ex){
            System.out.println("Cannont find the encoding type.");
        } catch (IOException ex) {
            System.out.println("Cannont read the line of file.");
        }
        return password;
    }
    
    public static String bruteForce(String hash){
        String password = null;
        try {
            password = Utility.bruteForcePassword(hash, "SHA-1");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Cannont find the encoding type.");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Cannont find the hashing algorithm.");
        }
        return password;
    }
    
    public static void showPassword(String password){
        if(password == null){
            System.out.println("Cannot find a password that matches the given hash");
        }else{
            System.out.println("The password was " + password);
            boolean passPolicy = Utility.policyPassword(password);
            if(passPolicy){
                System.out.println("The password does pass policy check");
            }else{
                System.out.println("The password does not pass policy check");
            }
        }
    }
    
    public static void pressKey(){
        System.out.println("\nPress any key to continue ...");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void cleanScreen() {
        for (int i = 0; i <= 30; i++) {
            System.out.println("");
        }
    }
}
