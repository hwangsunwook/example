package com.mnk.TCPsoketTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TCPsoketTest extends Activity {
  /** Called when the activity is first created. */
private String return_msg;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    final EditText et = (EditText)findViewById(R.id.EditText01);
    Button btn = (Button)findViewById(R.id.Button01);
    final TextView tv = (TextView)findViewById(R.id.TextView01);
    
    
    btn.setOnClickListener(new OnClickListener(){
    public void onClick(View v){
     if(et.getText().toString() != null || !et.getText().toString().equals("")){
     TCPclient tp = new TCPclient(et.getText().toString());
     tp.run();
     
     Toast t = Toast.makeText(getApplicationContext(), return_msg, Toast.LENGTH_LONG);
     t.show();
     tv.setText(return_msg);
     }
    }
    });
        
  }
  
  private class TCPclient implements Runnable {
  private static final String serverIP = "xxx.xxx.xxx.xxx"; // ex: 192.168.0.100
  private static final int serverPort = xxxx; // ex: 5555
  private String msg;
  //private String return_msg;
  
  public TCPclient(String _msg){
   this.msg = _msg;
  }
  @Override
  public void run() {
   // TODO Auto-generated method stub
   try {
      
      InetAddress serverAddr = InetAddress.getByName(serverIP);
      
      Log.d("TCP", "C: Connecting...");
      
      Socket socket = new Socket(serverAddr, serverPort);
      
     try {
      Log.d("TCP", "C: Sending: '" + msg + "'");
      PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
      
      out.println(msg);
      Log.d("TCP", "C: Sent.");
         Log.d("TCP", "C: Done.");
         
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         return_msg = in.readLine();
         
         Log.d("TCP", "C: Server send to me this message -->" + return_msg);
        } catch(Exception e) {
          Log.e("TCP", "C: Error1", e);
      } finally {
       socket.close();
      }
      } catch (Exception e) {
        Log.e("TCP", "C: Error2", e);
      }
  }
  
  }
}