package ifpr.pgua.eic.tads.comunicacao.tcp;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    private ServerSocket servidor;
    private Socket cliente;


    private String HOST;
    private int PORTA;

    private BufferedReader entrada;
    private BufferedWriter saida;


    public ServidorTCP(String HOST,int PORTA)throws IOException {
        servidor = new ServerSocket(PORTA);
    }

    public void escuta() throws IOException {
        System.out.println("Aguardando conexão...");
        cliente = servidor.accept();
        System.out.println("Conectado..."+cliente.getInetAddress()+":"+cliente.getPort());

        //abreFluxos();

    }

    private void abreFluxos() throws IOException{
        entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        saida = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
    }

    public void processa() throws IOException{
            Thread t = new Thread(new Worker(cliente));
            t.start();
        
        /* 
        try{
            while(true){
                String msgEntrada = entrada.readLine();

                System.out.println("RECEBIDO --> "+msgEntrada);
                if(msgEntrada.toLowerCase().equals("sair")){
                    break;
                }
                if(msgEntrada.toLowerCase().startsWith("somar: ")){
                    String[] tokens = msgEntrada.split(" ");
                    try{
                        int n1 = Integer.valueOf(tokens[1]);
                        int n2 = Integer.valueOf(tokens[2]);

                        int soma = n1+n2;

                        saida.write("Resultado: "+soma);
                    }catch(NumberFormatException e){
                        saida.write("ERRO");
                    }
                }   
                else if(msgEntrada.toLowerCase().startsWith("contar:")){
                    String[] tokens = msgEntrada.split(":");

                    String msg = tokens[1];

                    int cont = msg.length();
                    saida.write("Contém "+cont+" caracteres\n");

                }else{
                    msgEntrada = msgEntrada.toUpperCase();
                    saida.write(msgEntrada+"\n");
                }

                saida.flush();

            }
        }catch (IOException e){

        }
        */
    }

    public void fecha() throws IOException{
        entrada.close();
        saida.close();
        cliente.close();
    }






}
