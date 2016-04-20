/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import consensus.Actor;
import java.util.ArrayList;
import messages.Hello;
import messages.RouteTable;
import messages.TableLine;

public class Router {

  private RouteTable table;
  private ArrayList<Client> clients;
  private Server server;
  private static int lastPort = 3030;
  private Client client;

  public Router() {
    table = new RouteTable(lastPort + "");
    clients = new ArrayList<>();
    server = new Server(table, lastPort);
    server.start();
    client = new Client(table);

    refresh();
    lastPort++;
  }

  public int getPort() {
    return server.getPort();
  }

  public RouteTable getTable() {
    return table;
  }
  
  public Actor getActor(){
    return server.getActor();
  }

  public void connect(int port, int distance) {
//    Client client = new Client();
//    client.connect(port);
//    clients.add(client);
    table.add(port + "", port + "", distance);
  }

  private void refresh() {
    new Thread() {
      @Override
      public void run() {
        try {
          while (true) {
            for (int i = 1; i < table.size(); i++) {
              int port = Integer.parseInt(table.get(i).getLink());
              client.send(table, port);
              sleep(500);
            }
            sleep(5000);
          }
        }
        catch (InterruptedException interruptedException) {
          interruptedException.printStackTrace();
        }
      }
    }.start();
  }

  public void update(int port, int distance) {
    for (int i = 0; i < table.size(); i++) {
      TableLine line = table.get(i);
      if (line.getTarget().equals(port + "") && line.getTarget().equals(line.getLink())) {
        line.update(port + "", distance);
        client.send(table, port);
      }
    }

//    for (int i = 0; i < clients.size(); i++) {
//      Client client = clients.get(i);
//      if (client.getPort() == port) {
//        Hello msg = new Hello(server.getPort() + "", distance);
//        client.send(msg);
//        TableLine line = table.get(port + "");
//        line.update(port + "", distance);
//        System.out.println("");
//      }
//    }
  }

}
