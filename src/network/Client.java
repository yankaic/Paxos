package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.PresentationMessage;
import protocol.RouteTable;

/**
 *
 * @author Yan Kaic
 */
public class Client {

  private final RouteTable table;

  public Client(RouteTable table) {
    this.table = table;
  }

  public void connect(int port, int distance) {
    try {
      Socket socket = new Socket("localhost", port);
      table.add(port + "", port + "", distance);
      new Behavior(socket).start();
    }
    catch (IOException ex) {
      new Reconnector(port, distance).start();
    }
  }

  class Reconnector extends Thread {

    private final int port;
    private final int distance;

    public Reconnector(int port, int distance) {
      this.port = port;
      this.distance = distance;
    }

    @Override
    public void run() {
      final int fixTime = 5000;
      int waitTime = fixTime;
      while (true) {
        try {
          sleep(waitTime);
          waitTime += fixTime;
          reconnect();
          break;
        }
        catch (InterruptedException | IOException ex1) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
        }
      }
    }

    private void reconnect() throws IOException {
      Socket socket = new Socket("localhost", port);
      table.add(port + "", port + "", distance);
      new Behavior(socket).start();
    }
  }

  class Behavior extends Thread {

    final int refreshTime = 5000; //miliseconds
    Socket socket;

    public Behavior(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        while (true) {
          sleep(refreshTime);
          output.writeObject(table);
        }

      }
      catch (InterruptedException | IOException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

}
