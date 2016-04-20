package consensus.category;

import consensus.Actor;
import consensus.Agent;
import consensus.messages.ConsensusMessage;
import consensus.messages.Agreement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Learner extends Agent {

  private Agreement received;

  public Learner(Actor actor) {
    super(actor);
  }

  @Override
  public void receive(ConsensusMessage message) {
    if (message instanceof Agreement) {
      Agreement msg = (Agreement) message;
      if (!msg.done) {
        received = msg;
        mutex.release();
      }
    }
  }

  @Override
  public void run() {
    try {
      while (true) {
        for (int count = 0; count <= getActor().getNodes().size() / 2; count++) {
          mutex.acquire();
          if (received.value != getValue()) {
            setValue(received.value);
            count = 0;
          }
        }
        received.done = true;
        received.sender = getActor().getId();
        getActor().getClient().broadcast(received);
      }
    }
    catch (InterruptedException ex) {
      Logger.getLogger(Learner.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
