/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Hello;
import messages.RouteTable;

/**
 *
 * @author Yan Kaic
 */
public class Client {

//  Socket socket;
//  ObjectOutputStream output;

  public Client() {
  }

//  public void connect(int port) {
//    try {
//      socket = new Socket("localhost", port);
//      output = new ObjectOutputStream(socket.getOutputStream());
//    }
//    catch (IOException ex) {
//      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//    }
//  }
//
//  public int getPort() {
//    return socket.getPort();
//  }

//  public void send(RouteTable routeTable) {
//    sendObject(routeTable);
//  }

  public static void send(Hello msg, int port) {
    try {
      Socket localSocket = new Socket("localhost", port);
      ObjectOutputStream output = new ObjectOutputStream(localSocket.getOutputStream());
      output.writeObject(msg);
      output.close();
      localSocket.close();
    }
    catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
//
//  private void sendObject(Object object) {
//    try {
//      output.writeObject(object);
////      System.out.println("Destination: " + socket.getPort() + object);
//    }
//    catch (IOException ex) {
//      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//    }
//  }
  
  public static void send(RouteTable table, int port){
    try {
      Socket socket = new Socket("localhost", port);
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      output.writeObject(table);
      output.close();
      socket.close();
    }
    catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
