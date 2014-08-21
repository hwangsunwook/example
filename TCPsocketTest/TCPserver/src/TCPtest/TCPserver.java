package TCPtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver implements Runnable {
 public static final int ServerPort = xxxx; // ex: 5555
 public static final String ServerIP = "xxx.xxx.xxx.xxx"; // ex: 192.168.0.100
 
  @Override
 public void run() {
  // TODO Auto-generated method stub
  try{
   System.out.println("S: Connecting...");
   ServerSocket serverSocket = new ServerSocket(ServerPort);

   while (true) {
    Socket client = serverSocket.accept();
    System.out.println("S: Receiving...");

    try {
     BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
     String str = in.readLine();
     System.out.println("S: Received: '" + str + "'");
     PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(client.getOutputStream())),true);
     out.println("Server Received "+ str);
     
    } catch(Exception e) {
     System.out.println("S: Error");
     e.printStackTrace();
    } finally {
     client.close();
     System.out.println("S: Done.");
     
    }
   }
  } catch (Exception e) {
   System.out.println("S: Error");
   e.printStackTrace();
  }
 }

 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  Thread desktopServerThread = new Thread(new TCPserver());
  desktopServerThread.start();
 }
}
