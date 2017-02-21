package c2;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class ThreadEjemplo extends Thread {
    public ThreadEjemplo() {
        super();
    }
    
    private static String mac="ada141975ed739fe27e50cab4b5dd5a7c96553b1";
	private static String s = "34567891 987654 300";
    public void run() {
    	System.out.println(Thread.currentThread().getName());
    	for(;;){
    		try {
    			Mac mac1 = Mac.getInstance("HmacSHA1");
    			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA1");
    			kg.init(32);
    			SecretKey clave = kg.generateKey();
    			
    			mac1.init(clave);
    			mac1.update(s.getBytes());
    			byte[] res = mac1.doFinal();
    			//String macB= Conversion.byteArrayToHexString(mac.getBytes());
    			if( res== mac.getBytes()){
    				System.out.println("la clave es:"+clave.toString());
    				
    				break;
    			//System.out.println(macB);
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		}
    }
}