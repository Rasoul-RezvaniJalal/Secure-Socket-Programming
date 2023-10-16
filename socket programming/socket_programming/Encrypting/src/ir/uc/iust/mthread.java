package ir.uc.iust;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

public class mthread extends Thread {

    private final Socket connectionSocket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public mthread (Socket socket ,ObjectInputStream inputStream ,ObjectOutputStream outputStream){
        this.connectionSocket = socket ;
        this.inputStream = inputStream;
        this.outputStream = outputStream;

    }

    @Override
    public void run(){
        int[][] Private_key = {
                {1, 2},
                {-1, 2}
        };
        while (true){
            try{
                int nodes = inputStream.read();
                int temp;

                int[][] adjMatrix = new int[2][nodes];

                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < nodes; j++) {
                        temp = inputStream.read();

                        adjMatrix[i][j] = temp;
                    }
                }
                int C[][] = new int[2][nodes];
                char En[][] = new char[2][nodes];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < nodes; j++) {
                        for (int k = 0; k < 2; k++) {
                            C[i][j] += (Private_key[i][k] * adjMatrix[k][j]);
                        }

                        En[i][j] = ((char) C[i][j]);

                    }
                }


                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < nodes; j++) {
                        //System.out.println("befor transfer is : " + (En[i][j]));
                        outputStream.writeObject((En[i][j]));
                        outputStream.flush();
                    }
                }

                System.out.println("\n || THE RESULT SENT TO CLIENT ||");
            }
            catch (IOException e){
                    break;
            }
        }
    }
}
