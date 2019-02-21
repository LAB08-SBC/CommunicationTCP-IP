import java.io.*;
import java.net.*;
import java.util.Scanner;

public class myClient{
	
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		System.out.printf("\nDigite o numero de IP do servidor: ");
		String ipServer = keyboard.nextLine();
		System.out.printf("\nDigite o numero da porta: ");
		int port = keyboard.nextInt();
	
		Socket sock = new Socket(ipServer, port);
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);  
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
		System.out.println("Start the chitchat, type and press Enter key");
		String receiveMessage, sendMessage;
		while(true) {
			sendMessage = keyRead.readLine();
			pwrite.println(sendMessage);
			pwrite.flush();
			if((receiveMessage = receiveRead.readLine()) != null){
				System.out.println("Servidor: "+receiveMessage); 
			}
		} 
	} 
}   