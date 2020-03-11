import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	
	public static int qtd;
	public static double distmenor;
	public static int idmenor;
    public static double Jogadores[] = new double [qtd];
    int port;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=-1;
    
    public static void main(String[] args) throws IOException {
        Server serverobj=new Server(5000);
        serverobj.startServer();
        Scanner m = new Scanner (System.in);
        System.out.println("Qual é a Quantidade de Jogadores?\n");
        qtd= m.nextInt();
        System.out.println("Qual é a Distância do Jogador "+(qtd-1)+" até a Bola?\n");
        Jogadores[qtd-1]=m.nextDouble();
        distmenor=Jogadores[qtd-1];
        idmenor=qtd-1;
        }
    
    Server(int port){
        this.port=port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {
        
        server=new ServerSocket(5000);
        System.out.println("Capitão Pronto");
       
        while(true)
        {
            client=server.accept();
            clientcount++;
            ServerThread runnable= new ServerThread(client,clientcount,this);
            pool.execute(runnable);
        }
        
    }

    private static class ServerThread implements Runnable {
        
        Server server=null;
        Socket client=null;
        BufferedReader cin;
        PrintStream cout;
        Scanner sc=new Scanner(System.in);
        Scanner p=new Scanner (System.in);
        int id;
        String s;
        int cont,contt;
        String t="Bloqueado";

     

        ServerThread(Socket client, int count ,Server server ) throws IOException {
            
            this.client=client;
            this.server=server;
            this.id=count;
            System.out.println("Conexão "+id+" estabelecida com Jogador "+client);
            
            cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
            cout=new PrintStream(client.getOutputStream());
        
        }

        
        public void run() {
            int x=1;              
  //tenho que tirar daqui so nao sei como
          /* if(x==1){
         	  System.out.println("Qual a quantidade de jogadores? ");
               qtd=sc.nextInt();            
               System.out.println("\nQual a distância do jogador "+(qtd-1)+" até a bola?");
               Jogadores[qtd-1]=sc.nextInt();
               System.out.println("\nJogador "+(qtd-1)+" está bloqueado?");
               Jogadores[qtd-1]=0;
               x=2;
        	  } */
           
         try{
         while(true){    
        	 
        	  
               s=cin.readLine();
  			 
			System. out.print("Jogador("+id+") :"+s+"\n");
			//System.out.print("Capitão : ");
			//s=stdin.readLine();
                    //        s=sc.nextLine();
                            
                            while(cont<qtd-1){
                            	
                            	//if(cont==qtd-1){
                               // Jogadores[qtd-1]=dist;
                               // idmenor = qtd - 1;
                               // distmenor = Jogadores[qtd - 1];
                            //	}
                            	
                            	if (s.indexOf("/") != -1) {
                                //Se for diferente de -1 é pq existe o caracter.
                                String[] posic = s.split("/");
                                id = Integer.parseInt(posic[0]);
                                Jogadores[id] = Double.parseDouble(posic[1]);
                    
                            	}                     
                            	cont++;
                            }
                            
                             if(cont==qtd-1){
                            	s="Posicao Recebida";
                                cout.println(s);
                              	System.out.print("Capitão: "+s+"\n");
                              	cont++;
                             }
                             
                                s=cin.readLine();
                    			System.out.print("Jogador("+id+") :"+s+"\n");
                              	
                            
                          //  s="Situacao Recebida";
                           // cout.println(s);
                          	//System.out.print("Capitão: "+s+"\n");
                     
                                                    
                    			while(t.equals("Bloqueado")){
                                for (int i = 0; i < qtd; i++) {
                                    if (Jogadores[i] > 0) {
                                        if (Jogadores[i] < distmenor) {
                                            distmenor = Jogadores[i];
                                            idmenor = i;
                                            i = 0;
                                        }
                                    }
                                }

                                                               
                                if(idmenor==qtd-1){
                                	 s="Vou até a bola";
                                     cout.println(s);
                                   	System.out.print("Capitão: "+s+"\n");
                                }else{
                                s="Jogador "+idmenor+" escolhido";
                                cout.println(s);
                              	System.out.print("Capitão: "+s+"\n");
                              	
                                }
                             
                                
                                Jogadores[idmenor] = 0;
                                   	idmenor=0;
                    			}
                                   	
                        if (s.equalsIgnoreCase("bye"))
                        {
                            cout.println("BYE");
                            x=0;
                            System.out.println("Conexão encerrada");
                            break;
                        }
		
		}
		
            
                cin.close();
                client.close();
		cout.close();
                if(x==0) {
			System.out.println( "Server cleaning up." );
			System.exit(0);
                }
         } 
         catch(IOException ex){
             System.out.println("Error : "+ex);
         }
            
 		
        }
    }
    
}
