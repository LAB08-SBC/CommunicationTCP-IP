package teste;

import java.util.Scanner;

public class nenhumbloqueado {

    public static void main(String[] args) {
        Scanner buf = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        int qtd = 0;
        String t = "Sim";
        String aux = "";
        int id = 0;

        System.out.println("Quantos Jogadores?");
        qtd = buf.nextInt();
        double[] Jogador = new double[qtd];

        System.out.println("\nDigite a distância do Jogador " + (qtd - 1) + " até a bola: ");
        Jogador[qtd - 1] = buf.nextDouble();

        //distancia dos outros jogadores
        for (int j = 0; j < qtd - 1; j++) {
            //imaginando que o jogador esteja recebendo os dados e transmitindo pro servidor
            System.out.println("\nDefina a distância do Jogador " + j + " até a bola.");
            aux = str.nextLine();
            aux = j + "/" + aux;
            if (aux.indexOf("/") != -1) {
                //Se for diferente de -1 é pq existe o caracter.
                String[] posic = aux.split("/");
                id = Integer.parseInt(posic[0]);
                Jogador[id] = Double.parseDouble(posic[1]);
            }
        }

        //primeiro toma como referencia a distancia e id do proprio jogador
        int idmenor = qtd - 1;
        double distmenor = Jogador[qtd - 1];

        while (t.equals("Sim")) {
            //retorna o id e a menor distancia armazenada no vetor

            for (int i = 0; i < qtd; i++) {
                if (Jogador[i] > 0) {
                    if (Jogador[i] < distmenor) {
                        distmenor = Jogador[i];
                        idmenor = i;
                        i = 0;
                    }
                }
            }

            //nenhum está bloqueado
            t = "Não";
            Jogador[idmenor] = 0;
            distmenor = 100;
        }

        System.out.println("\nO Jogador " + idmenor + " foi escolhido.");

    }

}
