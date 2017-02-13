package consultoria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static String[] hexDigits ={"0", "1", "2", "3","4", "5", "6", "7","8", "9", "a", "b","c", "d", "e", "f"};
    
    //Calcula el password del hash recibido utilizando el diccionario de ataque recibido por parametro
    public static String lookupPassword(String hash, String algorithm, String dicFile) throws FileNotFoundException, IOException, UnsupportedEncodingException, NoSuchAlgorithmException {
        BufferedReader in = new BufferedReader(new FileReader(dicFile));
        String line = in.readLine();
        while(line != null){
            String lineHash = hashWord(line, algorithm);
            if(lineHash.equals(hash)){
                return line;
            }
            line = in.readLine();
        }
        return null;
    }
    
    //Calcule el hash de una cadena segun el algoritmo recibido por parametro
    public static String hashWord(String word, String algorithm) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] hash = md.digest(word.getBytes("UTF-8"));
        return Utility.byteArrayToHexString(hash);
    }
        
    //Convierte un byte a una cadena hexadecimal
    public static String byteToHexString(byte b) {
        int n=b;
        if(n <0) n=256 +n;
        int d1= n/16;
        int d2=n%16;
        return hexDigits[d1]+hexDigits[d2];
    }
    
    //Convierte un array de bytes a una cadena hexadecimal
    public static String byteArrayToHexString(byte[] b) {
        String result="";
        for(int i=0; i< b.length;++i){
            result+= byteToHexString(b[i]);
        }
        return result;
    }

    //Checkea si un password pasa la politica de seguridad de la empresa
    public static boolean policyPassword(String password) {
        Matcher matchOne = Pattern.compile("[A-Za-z]").matcher(password);
        Matcher matchTwo = Pattern.compile("[0-9]").matcher(password);
        Matcher matchThree = Pattern.compile("[~\\!@#\\$%\\^&\\*\\(\\)_\\+{}\\\":;'\\[\\]]").matcher(password);
        return matchOne.find() && matchTwo.find() && matchThree.find() && password.length() >= 8;
    }
}
