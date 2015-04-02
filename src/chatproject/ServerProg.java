/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatproject;


import java.lang.InterruptedException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by G R Tushar on 27-11-14.
 */
public class ServerProg {

    private static final String File_Path = "C:" + "/";
    private static final int PORT_number= 6789;
    public static Users us;
    //static Data dt;
   // Socket connectionSocket;

    //Create I/O streams for communicating to the client
   // static ServerSocket welcomeSocket;
    //static BufferedReader inFromClient;
    //static DataOutputStream outToClient;

    static boolean isValid(String str) {
        String fileName = "abc.txt";

        String file_Path = File_Path + fileName;
        //System.out.println(str);

        File file_match = new File(file_Path);
        try{
            Scanner scanner = new Scanner(file_match);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println(line);
                lineNum++;
                if(line.equals(str)) {
                    System.out.println("true");
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            return false;//System.out.println("not found");
        }
        return false;
    }

    public static void main(String args[])  throws Exception {
        ServerSocket welcomeSocket=new ServerSocket(6002);
        Data dt = new Data();
        while(true) {
            String ClientSentence;
            //String ReqFilePath;
            //String Dir = "G:\\";

            System.out.println("Server Running");

            Socket connectionSocket=welcomeSocket.accept();

            BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());



            //
            ClientSentence = inFromClient.readLine();

            //System.out.println("accepted  :: "+ClientSentence);
            while(!isValid(ClientSentence)) {
                //System.out.println(ClientSentence);
                outToClient.writeBytes("sorry"+'\n');
                
                connectionSocket=welcomeSocket.accept();
                inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                outToClient=new DataOutputStream(connectionSocket.getOutputStream());
                ClientSentence=inFromClient.readLine();
            }

            //outToClient.writeBytes("something" + '\n');
            outToClient.writeBytes(dt.getusers() + '\n');
            int idx = ClientSentence.indexOf(' ');
            String name = ClientSentence.substring(0, idx);
            System.out.println(name + " logged in");

            //int idx = ClientSentence.indexOf(' ');
            //String name = ClientSentence;//.substring(0, idx - 1);
            dt.toMap(name, outToClient);
            //us.set(name);

            Thread t = new Thread(new Communication(name, dt.mp, inFromClient));
            t.start();
        }
    }
}

