package ifpr.pgua.eic.tads.comunicacao.geradorblahblahblah;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Servidor {


    private ServerSocket servidor;
    private ArrayList<Gerador> conexoes;


    public Servidor(int porta) throws IOException {
        servidor = new ServerSocket(porta);
        conexoes = new ArrayList<>();
    }

    public void processa(){

        while(true){
            try{

                if(conexoes.size() < 20){
                    System.out.println("Escutando...");
                    Socket socket = servidor.accept();
                    System.out.println("Conexão recebida..."+socket.getInetAddress());
                    Gerador cliente = new Gerador(socket);

                    new Thread(cliente).start();
                    System.out.println("Liberado...");
                    conexoes.add(cliente);

                }else{
                    System.out.println("Cheio... dormir...");
                    Thread.sleep(500);
                }
                Iterator<Gerador> iterator = conexoes.iterator();
                while (iterator.hasNext()){
                    Gerador g = iterator.next();
                    if(g.isFinalizada()){
                        conexoes.remove(g);
                    }
                }

            }catch (IOException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
