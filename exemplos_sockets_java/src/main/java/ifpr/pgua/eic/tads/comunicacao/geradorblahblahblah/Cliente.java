package ifpr.pgua.eic.tads.comunicacao.geradorblahblahblah;

import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",12000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while(true){
            System.out.println("Aqui...");
            String msg = in.readLine();
            System.out.println(msg);
            if(msg.equals("FIM")){
                System.out.println("Saindo...");
                System.exit(0);
            }
        }
    }

}
