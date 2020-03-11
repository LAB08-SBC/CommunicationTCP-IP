/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    
    public static void main(String args[]) throws Exception
	{
           
                    
		Socket sk=new Socket("192.168.43.157",5000);
		BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream()));
		PrintStream sout=new PrintStream(sk.getOutputStream());
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		String s="";  

                int x =0;
                double id = 1;
                double c=0;
                int n=3; //NUMERO DE JOGADORES+1
                String posij="3"; //POSIÇÃO DO JOGADOR
                boolean bloqueado = true; //JOGADOR BLOQUEADO OU NAO
                boolean bloq=false;
double posi[] = new double[n];
double ids[] = new double[n];
double menor = 6;
double idmenor = 0;
double a = 0;
double au = 0;
        		Bioloid Jason = new Bioloid(18);
		while (  true )
		{
	
			
                   if(x==0){
                    s=posij;
			System.out.print("Client("+id+")"+" "+": "+s+"\n");
                         sout.println(s);
                    s=sin.readLine();
                         System.out.print("Server : "+s+"\n");
                        
                   
                   
                         Thread.sleep(2000);
                         
                    for(int i=1;i<n;i++){
                                             s="posição";
			System.out.print("Client("+id+")"+" "+": "+s+"\n");
                         sout.println(s);
                         s=sin.readLine();
                         System.out.print("Server : "+s+"\n");
                        posi[i]=Double.parseDouble(s);
                        ids[i]= i;
                    }
                     x=x+1;}
                    for (int i = 0; i < n-1; i++) {

                            if (posi[i] > posi[i + 1]) {
                                a = posi[i];
                                au = ids[i];

                                ids[i] = ids[i + 1];
                                posi[i] = posi[i + 1];
                                posi[i + 1] = a;
                                ids[i + 1] = au;
                            }

                           
                        }
                    
           
                         
                    if (ids[1]==id){
                        if (bloqueado == false){
                       Jason.move(5,600);
                        	System.out.print("Client("+id+")"+": eu vou\n");
                        sout.println("eu vou");
                        s=sin.readLine();
                        System.out.print("Server : "+s+"\n");
                    	
                   break;
                        }
                        else{
                     System.out.print("Client("+id+")"+": bloqueado\n");
                        sout.println("bloqueado");
                        s=sin.readLine();
                        System.out.print("Server : "+s+"\n");
                    	Jason.move(6,600);
       break;
                        }}
                    else{
                  if(x==1){  
       Thread.sleep(2000);
                         System.out.print("Client("+id+")"+": alguem vai?\n");
                        sout.println("alguem vai?");
                        s=sin.readLine();
                        System.out.print("Server : "+s+"\n");
                        x=x+1;
                        if(s.equals("não")){
                        while(s.equals("não")){
                             for(int i=2;i<n;i++){
                        if (ids[i]==id){
                        if (bloqueado == false){
                       System.out.print("Client("+id+")"+": eu vou\n");
                        sout.println("eu vou");
                        s=sin.readLine();
                        System.out.print("Server : "+s+"\n");
                    	Jason.move(5,600);
                    
                   
                        }
                        else{
                     System.out.print("Client("+id+")"+": bloqueado\n");
                        sout.println("bloqueado");
                        s=sin.readLine();
                        System.out.print("Server : "+s+"\n");
                    	Jason.move(6,600);
                     
                        }
                 break;
                        }
                                else{
               
                         System.out.print("Client("+id+")"+": alguem vai?\n");
                        sout.println("alguem vai?");
                        s=sin.readLine();
                        System.out.print("Server : "+s+"\n"); 
                                }      
                             if(i==n){
                     System.out.println("Não há nenhum jogador disponível");
              break;   
                    } }  
                        }}
                             
                        else{
                            break;
                        }
                  }
                   
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
