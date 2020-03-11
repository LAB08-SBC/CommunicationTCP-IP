package teste;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    
    public static void main(String args[]) throws Exception
	{
		Socket sk=new Socket("127.0.0.1",5000);
		BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream()));
		PrintStream sout=new PrintStream(sk.getOutputStream());
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		String s,t;
		int id=0;
		
		while (  true )
		{
			System.out.print("Jogador : ");
			s=stdin.readLine();
			sout.println(s);
			
			if (s.indexOf("/") != -1) {
	                //Se for diferente de -1 é pq existe o caracter.
	                String[] posic = s.split("/");
	                id = Integer.parseInt(posic[0]);
			}
			
			s=sin.readLine();
			System.out.print("Capitão: "+s+"\n");
			
			if(s.equals("Posicao Recebida")){
			
				System.out.print("Jogador está bloqueado? \n");
				s=stdin.readLine();	
				//Thread.sleep(8000);
				sout.println(s);	
				s=sin.readLine();
				System.out.print("Capitão: "+s+"\n");
				}
			
			t="Jogador "+id+" escolhido";
		
			if(s.equals(t)){
				s="Ok";
				System.out.print("Jogador : "+s+"\n");
				sout.println(s);
			}

	
			
			if(s.equals("Vou até a bola")){
				s="Ok";
				System.out.print("Jogador : "+s+"\n");
				sout.println(s);
			}
			
                        if ( s.equalsIgnoreCase("BYE") )
                        {
                           System.out.println("Connection ended by client");
 			   break;
                        }
                        
			  			
		}
		 sk.close();
		 sin.close();
		 sout.close();
 		stdin.close();
	}
    
}
