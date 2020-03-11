


import java.math.BigDecimal;

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

    int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService pool = null;
    int clientcount = 0;
    double menor = 6;

    public static void main(String[] args) throws IOException {

        Server serverobj = new Server(5000);
        serverobj.startServer();
    }

    Server(int port) {
        this.port = port;
        pool = Executors.newFixedThreadPool(200);
    }

    public void startServer() throws IOException {

        server = new ServerSocket(5000);
        System.out.println("Server Booted");
        System.out.println("Any client can stop the server by sending -1");
        while (true) {
            client = server.accept();
            clientcount++;
            ServerThread runnable = new ServerThread(client, clientcount, this);
            pool.execute(runnable);
        }

    }

    private static class ServerThread implements Runnable {

        Server server = null;
        Socket client = null;
        BufferedReader cin;
        PrintStream cout;
        Scanner sc = new Scanner(System.in);
        int id;
        String s;
        private static int n = 4;//NUMERO DE JOGADORES+1
        private static double posi[] = new double[n];
        private static boolean bloqueado = false;

        ServerThread(Socket client, int count, Server server) throws IOException {

            this.client = client;
            this.server = server;
            this.id = count;
            System.out.println("Connection " + id + "established with client " + client);

            cin = new BufferedReader(new InputStreamReader(client.getInputStream()));
            cout = new PrintStream(client.getOutputStream());

        }

       
        public void run() {
            int x = 1;
            int j=0;

            try {
                while (true) {
                    s = cin.readLine();
                    System.out.print("Client(" + id + ") :" + s + "\n");

                    if(j==0){
                  
                        posi[id] = Double.parseDouble(s);
                        System.out.print("Server : posição gravada\n");
                        cout.println("posição gravada");
                        j=j+1;
                    }
                       
   if(s.equals("posição")){    
      
while (s.equals("posição")){
 
    for(int i=1;i<n;i++){
    s=Double.toString(posi[i]);
    System.out.print("Server : " + s + "\n");
                        cout.println(s);
                           s = cin.readLine();
                    System.out.print("Client(" + id + ") :" + s + "\n");
    }
}      }
   
    if(s.equals("eu vou")){
                        bloqueado=false;  
                        System.out.print("Server : ok\n");
                        cout.println("ok");
              
                    }
                    if(s.equals("bloqueado")){
    
                     bloqueado=true;
                     System.out.print("Server : ok\n");
                        cout.println("ok");
                    }
                    
                    
           
                    if(s.equals("alguem vai?")){
                     if (bloqueado==true){

                        System.out.print("Server : não\n");
                        cout.println("não");
                        
                                        }else{
                        System.out.print("Server : sim\n");
                        cout.println("sim");
                    }
                     }
                                     
                   
                    if (s.equalsIgnoreCase("lalal")) {
                        cout.println("ok");
                        break;
                    }

 
   }
                
            

            cin.close();
            client.close();
            cout.close();
            if (x == 0) {
                System.out.println("Server cleaning up.");
                System.exit(0);
            }
        }
        catch(IOException ex

        
            ){
             System.out.println("Error : " + ex);
        }

    }
}
}

