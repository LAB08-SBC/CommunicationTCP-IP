import java.io.*;
import java.net.*;
public class myServer{ 

	private static ServerSocket sersock;
	private static String ipAddress = "192.168.43.186";

	public static void main(String[] args) throws Exception{ 
		
		
		if (ipAddress != null && !ipAddress.isEmpty()) 
          sersock = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
        else 
          sersock = new ServerSocket(0, 1, InetAddress.getLocalHost());
	  
		System.out.println("Server ready for chatting with: " +sersock.getInetAddress()+" port: " + sersock.getLocalPort());       //instruction 8
		Socket sock = sersock.accept( );                                //instruction 9
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		OutputStream ostream = sock.getOutputStream();
		PrintWriter pwrite = new PrintWriter(ostream, true);
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
		String receiveMessage, sendMessage;
		while(true){
			if((receiveMessage = receiveRead.readLine()) != null){
				System.out.println("Client: " + receiveMessage);
				sendMessage = keyRead.readLine();
				pwrite.println(sendMessage); pwrite.flush(); 
			}
		}
	}
} 