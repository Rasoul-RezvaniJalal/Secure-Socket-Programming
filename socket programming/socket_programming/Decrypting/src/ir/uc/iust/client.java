package ir.uc.iust;
import java.net.*;

import java.io.*;

import java.lang.String;
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

public class client {


    public static void main(String[] args) throws Exception{

        Scanner rd = new Scanner(System.in);
        System.out.println("\nEnter Sentence to Decrypt : ");

        String statment = rd.nextLine();

        int col=0;
        int t = statment.length()/2;
        if(statment.length() % 2 == 0){
            col = t;
        }
        else{
            System.out.println("Encrypted massage must have even size!");

            System.exit(0);
        }

        int m =0;
        int[][] mat = new int[2][col];


        for(int i=0;i<2;i++){
            for(int j=0;j<col;j++){

                    mat[i][j] = (int) statment.toCharArray()[m];
                    m++;
                    if(m==statment.length())
                        break;
            }
        }

        System.out.println(" \n ENCRYPTED TEXT IS : " + statment);


        Socket ss=new Socket("localhost",11267);

        ObjectOutputStream outToServer = new ObjectOutputStream(ss.getOutputStream());

        ObjectInputStream inFromServer = new ObjectInputStream((ss.getInputStream()));

        outToServer.write(col);
        outToServer.flush();

        for (int i = 0; i < 2; i++) {
            for(int j =0 ; j < col ; j++) {
                outToServer.write(((char) mat[i][j]));
                outToServer.flush();
            }
        }


        char temp;
        char [][] tmp_mat = new char [2][col];
        for (int i = 0; i < 2; i++){
            for(int j =0;j<col;j++) {
                temp = (char) inFromServer.readObject();

                tmp_mat[i][j] = temp;
            }
            }

        String EncryptSentence = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < col; j++) {
                EncryptSentence += tmp_mat[i][j];
            }
        }


        System.out.println("DECRYPT RESULT FROM SERVER : " + EncryptSentence);

        ss.close();

    }
}
