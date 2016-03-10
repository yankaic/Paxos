package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.PresentationMessage;
import protocol.RouteTable;

/**
 *
 * @author Yan Kaic
 */
public class Server {

  public static final String END_MESSAGE = "END_MESSAGE";
  public static final String HELLO = "HELLO";
  public static final String TABLE = "TABLE";
  private final RouteTable fixTable, dynamicTable;

  public Server(RouteTable fixTable, RouteTable dynamicTable, int port) {
    this.fixTable = fixTable;
    this.dynamicTable = dynamicTable;
    new Thread() {
      @Override
      public void run() {
        try {
          ServerSocket welcome = new ServerSocket(port);
          while (true) {
            Socket socket = welcome.accept();
            new Behavior(socket).start();
          }
        }
        catch (IOException ex) {
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }.start();
  }

  private class Behavior extends Thread {

    private final Socket socket;

    public Behavior(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        while (socket.isConnected()) {
          Object readObject = input.readObject();

          if (readObject instanceof RouteTable) {
            RouteTable table = (RouteTable) readObject;
            dynamicTable.merge(table);
          }
          else if (readObject instanceof PresentationMessage) {
            PresentationMessage hello = (PresentationMessage) readObject;
            if (fixTable.has(hello.name)) {
              fixTable.get(hello.name).update(socket.getPort() + "", hello.distance);
            }
            else {
              fixTable.add(hello.name, socket.getPort() + "", hello.distance);
            }
            dynamicTable.merge(fixTable);
          }
        }
      }
      catch (IOException ex) {
        fixTable.removeLink(socket.getPort() + "");
        
      }
      catch (ClassNotFoundException ex) {
        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

}
