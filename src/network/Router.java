package network;

import java.util.ArrayList;
import protocol.RouteTable;

public class Router {

  private static int portServer = 3030;

  private final ArrayList<Client> clients;
  private final Server server;
  RouteTable fixTable, dynamicTable;

  public Router() {
    fixTable = new RouteTable(portServer + "");
    dynamicTable = new RouteTable(portServer + "");
    server = new Server(fixTable, dynamicTable, portServer);
    clients = new ArrayList<>();
    portServer++;
  }
  
  public void connect(String link, int distance){
    Client client = new Client(dynamicTable);
    client.connect(Integer.parseInt(link), distance);
    clients.add(client);
  }
  

}
