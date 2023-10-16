package ir.uc.iust;
import java.io.*;
import java.net.*;


public class Server {

    public static void main(String[] args) throws Exception {


        ServerSocket welcomeSocket = new ServerSocket(17032);

        System.out.println("\t\t\t"+"***************************" + "\n");
        System.out.println("\t\t\t"+"**** SERVER IS RUNNING ****" + "\n");
        System.out.println("\t\t\t"+"***************************" + "\n\n");

        while (true) {
            try {
                Socket connectionSocket = welcomeSocket.accept();

                System.out.println("**** SERVER ACCEPT NEW CLIENT ****" + "\n");

                ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());

                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

                new mthread(connectionSocket,inFromClient,outToClient).start();

            }
            catch (Exception e){
                e.printStackTrace();

            }
        }
    }
}