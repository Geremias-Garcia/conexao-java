package ifpr.pgua.eic.tads.comunicacao.geradorblahblahblah;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.SecureRandom;

public class Gerador implements Runnable{

    private String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam eget orci massa. Aenean maximus volutpat lorem vel efficitur. Cras id venenatis tortor. Aenean ullamcorper, massa et ullamcorper efficitur, ante lectus gravida odio, sed mollis neque ex a libero. Etiam mollis mauris ante, at luctus augue fermentum vitae. Ut rutrum faucibus massa, ut rutrum felis. Duis sed purus eget odio tincidunt egestas viverra in enim. In laoreet nunc vestibulum tristique viverra. Mauris in fermentum lectus, id auctor tortor. Nam enim ante, mattis in interdum sed, pellentesque vitae enim. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis non purus ante. Proin ullamcorper tortor convallis facilisis volutpat. Curabitur metus eros, egestas in diam nec, varius pretium dolor. Sed sodales eros eu arcu sagittis molestie. Donec in posuere odio. " +
            "Curabitur rhoncus erat feugiat ante mattis pellentesque. Aliquam ullamcorper quam tellus, vitae tempus metus cursus quis. Fusce iaculis gravida justo eu vulputate. Ut ac blandit augue, eget aliquet ex. Quisque nec bibendum nunc. Aliquam velit lacus, elementum at luctus id, dignissim at quam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc iaculis non ante rutrum iaculis. Mauris vel lacinia tellus. Aliquam bibendum est lorem, a mattis libero luctus nec. In nec varius enim. Etiam felis neque, ullamcorper eu odio quis, tristique eleifend leo. Phasellus facilisis nibh in auctor vehicula. Integer faucibus vel orci nec commodo. " +
            "Duis sollicitudin justo non elit efficitur, imperdiet mattis enim placerat. Duis interdum blandit laoreet. Etiam porta, eros finibus euismod porttitor, sem orci ullamcorper leo, id interdum massa diam vel ligula. Sed hendrerit magna sit amet ex tempus euismod. Etiam porta vel orci vel ultrices. Quisque euismod laoreet neque, sit amet tempor nisl aliquam non. Suspendisse risus lectus, imperdiet et erat in, hendrerit sodales odio. In ultrices metus ut nulla finibus, ac pretium lacus iaculis. Quisque mattis est metus, in aliquam neque rutrum et. Nullam in ullamcorper ligula, lobortis tristique diam. Nullam non augue elementum, imperdiet eros vitae, elementum ante. Vestibulum posuere eleifend augue, nec ornare libero volutpat vel. Nam porttitor pharetra lacus at bibendum. Aliquam elit lacus, tempor eget ante quis, vestibulum bibendum lectus. Duis ex augue, hendrerit nec malesuada vitae, convallis sed sem. " +
            "Mauris sodales orci magna, et volutpat purus tincidunt ac. Integer justo dui, eleifend id auctor in, sodales vel ligula. Suspendisse consequat sem quis enim maximus, sed volutpat nunc scelerisque. Etiam ut nulla luctus, aliquam ligula vitae, laoreet enim. Nulla hendrerit imperdiet consectetur. Integer quis velit luctus, malesuada nulla sed, facilisis nisl. Nulla sagittis tempus massa, vel elementum eros tristique at. Mauris consectetur diam eu vehicula suscipit. " +
            "Morbi id orci augue. Curabitur non dolor ac mi vulputate consectetur. Vestibulum leo ipsum, lobortis nec velit at, congue ultrices dolor. Vivamus tortor urna, maximus ut augue vitae, pulvinar faucibus felis. Vestibulum porttitor condimentum auctor. Morbi fringilla vulputate libero et venenatis. Donec felis lorem, ultricies in euismod at, malesuada eget nisi. Vestibulum mollis massa sit amet imperdiet euismod. Sed ac est nec libero viverra pharetra. Nam tincidunt, felis at vestibulum ullamcorper, ante nunc viverra ex, a faucibus felis nisl sit amet justo. Suspendisse fringilla faucibus semper. Aenean hendrerit nisl eu risus mollis, vel euismod metus euismod. Aliquam in quam fringilla, lobortis dolor sed, faucibus diam. Ut a lobortis libero, vel venenatis ipsum.";


    private Socket socket;
    private BufferedWriter out;
    private SecureRandom rnd;
    private boolean finalizada;

    public Gerador(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.rnd = new SecureRandom();
        this.finalizada = false;
    }


    private String fraseAleatoria(){

        int index = this.rnd.nextInt(lorem.length()-10);
        String saida = lorem.substring(index,index+10);
        System.out.println(saida);
        return saida;

    }

    public void run(){

        int cont = 10;

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        while(cont > 0){
            System.out.println("Escrevendo..."+cont);
            try{
                this.out.write(fraseAleatoria()+"\n");
                this.out.flush();
                Thread.sleep(2000);
                cont -=1;
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }

        try{
            this.out.write("FIM\n");
            this.out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        finalizada = true;
    }

    public boolean isFinalizada() {
        return finalizada;
    }
}
