package ifpr.pgua.eic.tads.comunicacao.tcp;


import java.io.*;
import java.net.Socket;

public class ClienteTCP {

    private Socket socket;
    private String HOST;
    private int PORTA;
    private BufferedReader entrada;
    private BufferedWriter saida;


    public ClienteTCP(String HOST, int PORTA) throws IOException {

        this.socket = new Socket(HOST,PORTA);
        abreFluxos();

    }

    private void abreFluxos() throws IOException{
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        saida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public String processa(String msgEnvio) throws IOException{


        saida.write(msgEnvio+"\n");
        saida.flush();

        String msgRecebida = entrada.readLine();

        return msgRecebida;
    }

    public void fecha() throws IOException {
        entrada.close();
        saida.close();
        socket.close();
    }




}
