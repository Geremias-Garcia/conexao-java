package ifpr.pgua.eic.tads.comunicacao.geradorblahblahblah;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {


        Servidor servidor = new Servidor(12000);

        servidor.processa();


    }



}
