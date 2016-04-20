/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import consensus.messages.ConsensusMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class Client {

  private final RouteTable table;

  public Client(RouteTable table) {
    this.table = table;
  }

  public void send(Hello msg, int port) {
    sendObject(msg, port);
  }

  public void send(RouteTable table, int port) {
    sendObject(table, port);
  }

  public void sendObject(Object object, int port) {
    try {
      TableLine line = table.get(port + "");
      port = Integer.parseInt(line.getLink());
      Socket localSocket = new Socket("localhost", port);
      ObjectOutputStream output = new ObjectOutputStream(localSocket.getOutputStream());
      output.writeObject(object);
      output.close();
      localSocket.close();
    }
    catch (IOException ex) {
      Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void multicast(Object obj) {
    TableLine line;
    for (int i = 1; i < table.size(); i++) {
      line = table.get(i);
      if (line.isDirect()) {
        int port = Integer.parseInt(line.getLink());
        sendObject(obj, port);
      }
    }
  }

  public void broadcast(ConsensusMessage message) {
    for (int i = 0; i < table.size(); i++) {
      TableLine line = table.get(i);
      message.receiver = Integer.parseInt(line.getTarget());
      int port = Integer.parseInt(line.getLink());
      sendObject(message, port);

    }

  }

}
