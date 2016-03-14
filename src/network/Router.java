/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import messages.Hello;
import messages.RouteTable;
import messages.TableLine;

public class Router {

  private RouteTable table;
  private ArrayList<Client> clients;
  private Server server;
  private static int lastPort = 3030;

  public Router() {
    table = new RouteTable(lastPort + "");
    clients = new ArrayList<>();
    server = new Server(table, lastPort);
    server.start();
    refresh();
    lastPort++;
  }

  public int getPort() {
    return server.getPort();
  }
  
  public RouteTable getTable(){
    return table;
  }

  public void connect(int port, int distance) {
    Client client = new Client();
    client.connect(port);
    clients.add(client);
    table.add(port + "", port + "", distance);
  }

  private void refresh() {
    new Thread() {
      @Override
      public void run() {
        try {
          while (true) {
            for (int i = 0; i < clients.size(); i++) {
              clients.get(i).send(table);
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
    for (int i = 0; i < clients.size(); i++) {
      Client client = clients.get(i);
      if (client.getPort() == port) {
        Hello msg = new Hello(server.getPort() + "", distance);
        client.send(msg);
        TableLine line = table.get(port + "");
        line.update(port + "", distance);
        System.out.println("");
      }
    }
  }

}
