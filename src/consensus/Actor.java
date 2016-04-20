package consensus;

import consensus.category.Learner;
import consensus.category.Acceptor;
import consensus.category.Proposer;
import consensus.messages.ConsensusMessage;
import consensus.messages.Presentation;
import java.util.ArrayList;
import messages.RouteTable;
import network.Client;

public class Actor{

  private final int id;
  private int value;

  private final RouteTable nodes;

  private Client client;

  private Proposer proposer;
  private Acceptor acceptor;
  private Learner learner;

  public Actor(int id, RouteTable table) {
    this.id = id;
    this.nodes = table;
    
    this.proposer = new Proposer(this);
    this.acceptor = new Acceptor(this);
    this.learner = new Learner(this);
    
    learner.start();
    acceptor.start();
    proposer.start();
    
  }
  

  

  public void receive(ConsensusMessage message) {
    if (!(message instanceof Presentation)) {
      proposer.receive(message);
      learner.receive(message);
      acceptor.receive(message);
    }
  }

  
  /**
   *
   */
  public void echo() {
    Presentation presentation = new Presentation(id, id);   
    client.broadcast(presentation);
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public RouteTable getNodes() {
    return nodes;
  }

  public int getId() {
    return id;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  
  

}
