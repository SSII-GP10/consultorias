package c2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MacDecoder extends Thread {
	private String mac;
	private String message;
	private byte[] fixedHeader;
    public MacDecoder(String mac, String message,byte[] fixedHeader) {
        super();
        this.mac= mac;
        this.message = message;
        this.fixedHeader=fixedHeader;
    }
    
   
    public void run() {
    	System.out.println(Thread.currentThread().getName());
    	System.out.println("Buscando clave para "+message+" con mac "+mac);
    	
    	
		try {
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA1");
			kg.init(32);
			Mac mac1 = Mac.getInstance("HmacSHA1");
			for(;;){
				SecretKey clave = kg.generateKey();
				mac1.init(clave);
				mac1.update(message.getBytes());
				byte[] hashedMessage = mac1.doFinal();
				String hMessage= Conversion.byteArrayToHexString(hashedMessage);
				if( hMessage.equals(mac)){
					String claveDecodificada = Conversion.byteArrayToHexString(clave.getEncoded());
					System.out.println("la clave es:"+claveDecodificada);
					writeResult(hMessage, message, claveDecodificada);
					break;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    		
    }
    public static SecretKey divideProblem(byte[] fixedHeader) throws NoSuchAlgorithmException{
    	KeyGenerator kg = KeyGenerator.getInstance("HmacSHA1");
		kg.init(32-fixedHeader.length);
		SecretKey clave = kg.generateKey();
		byte[] randomPart = clave.getEncoded();
		byte[] result = new byte[32];
		for(int i=0;i<fixedHeader.length;i++){
			result[i]= fixedHeader[i];
		}
		for(int i=0;i<randomPart.length;i++){
			result[fixedHeader.length+i]= randomPart[i];
		}
		return new SecretKeySpec(result,"HmacSHA1");
		
    }
    
    public static void writeResult(String mac, String message, String key){
    	File file = new File("Macs.txt");
    	try {
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Message: "+message+"   mac: "+mac+"   key: "+key);
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public static void hash(String message){
    	
    	try {
			Mac mac1 = Mac.getInstance("HmacSHA1");
			KeyGenerator kg = KeyGenerator.getInstance("HmacSHA1");
			kg.init(32);
			SecretKey clave = kg.generateKey();
			mac1.init(clave);
			mac1.update(message.getBytes());
			byte[] res = mac1.doFinal();
			String res2= Conversion.byteArrayToHexString(res);
			System.out.println("la mac es: "+res2.toString());
			System.out.println("el mensaje era "+message);
			System.out.println("la clave es "+clave.toString());
			
			System.out.println(clave.getFormat());
			String laclave = Conversion.byteArrayToHexString(clave.getEncoded());
			System.out.println(laclave);
			System.out.println();
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}