/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.Hello;
import messages.RouteTable;
import messages.TableLine;

/**
 *
 * @author Yan Kaic
 */
public class Server extends Thread {

  private ServerSocket serverSocket;
  private RouteTable table;

  Server(RouteTable table, int port) {
    try {
      serverSocket = new ServerSocket(port);
      this.table = table;
    }
    catch (IOException ex) {
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  int getPort() {
    return serverSocket.getLocalPort();
  }

  @Override
  public void run() {
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        new Behavior(socket).start();
      }
      catch (IOException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public class Behavior extends Thread {

    private Socket socket;

    public Behavior(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
     
          Object readObject = input.readObject();

          if (readObject instanceof RouteTable) {
            RouteTable otherTable = (RouteTable) readObject;
            table.merge(otherTable);

          }

          if (readObject instanceof Hello) {
            Hello hello = (Hello) readObject;
            TableLine line = table.get(hello.name);
            line.update(hello.name, hello.distance);
          }
        }
     
      catch (IOException | ClassNotFoundException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
  }

}
