package c2;

public class Main {

	private static String mac="ada141975ed739fe27e50cab4b5dd5a7c96553b1";	
	private static String s = "34567891 987654 300";
	
	public static void main(String[] args) {
		MacDecoder th1 =new MacDecoder(mac,s,new byte[1]);
		 th1.start();
		 MacDecoder th2 =new MacDecoder(mac,s,new byte[1]);
		 th2.start();
		 MacDecoder th3 =new MacDecoder(mac,s,new byte[1]);
		 th3.start();
		 MacDecoder th4 =new MacDecoder(mac,s,new byte[1]);
		 th4.start();
		

	}

}
