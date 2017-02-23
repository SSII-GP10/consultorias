package c2;

public class Main {

	private static String mac1="e9686f3ab7307eabe34e5aa516dc5f70d24f2b7b";
	private static String mac2="77aa327d428aead75287724472e4f685b65a19a5";
	private static String mac3="e501b9c699ca8c502ccc03d696ef1e0506fdf328";
	private static String s1 = "137568 48765486 650";
	private static String s2 = "500168 5486631 495";
	private static String s3 = "715493 8563111 973";
	
	public static void main(String[] args) {
		MacDecoder th1 =new MacDecoder(mac1,s1,new byte[1]);
		 th1.start();
		 MacDecoder th2 =new MacDecoder(mac1,s1,new byte[1]);
		 th2.start();
		 MacDecoder th3 =new MacDecoder(mac1,s1,new byte[1]);
		 th3.start();
		 MacDecoder th4 =new MacDecoder(mac1,s1,new byte[1]);
		 th4.start();
		/* MacDecoder th5 =new MacDecoder(mac,s,new byte[1]);
		 th5.start();
		 MacDecoder th6 =new MacDecoder(mac,s,new byte[1]);
		 th6.start();
		 MacDecoder th7 =new MacDecoder(mac,s,new byte[1]);
		 th7.start();
		 MacDecoder th8 =new MacDecoder(mac,s,new byte[1]);
		 th8.start();*/

	}

}
