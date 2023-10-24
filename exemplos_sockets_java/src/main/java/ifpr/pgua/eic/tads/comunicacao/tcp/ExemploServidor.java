package ifpr.pgua.eic.tads.comunicacao.tcp;

import java.io.IOException;

public class ExemploServidor {

    public static void main(String[] args) {
        try{
            ServidorTCP servidorTCP = new ServidorTCP("localhost",12000);

            while (true){

                servidorTCP.escuta();
                servidorTCP.processa();
                //servidorTCP.fecha();
            }


        }catch (IOException e){

        }

    }

}
