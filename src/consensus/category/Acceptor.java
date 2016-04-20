package consensus.category;

import consensus.Actor;
import consensus.Agent;
import consensus.messages.Suggestion;
import consensus.messages.ConsensusMessage;
import consensus.messages.Agreement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acceptor extends Agent {

  private int serial;
  private Suggestion suggestion;

  public Acceptor(Actor actor) {
    super(actor);
  }

  public void recuseSuggestion() {
    suggestion.accept = false;
    replyMessage();
  }

  private void replyMessage() {
    suggestion.receiver = suggestion.sender;
    suggestion.sender = getActor().getId();
    getActor().getClient().sendObject(suggestion, suggestion.receiver);

    System.out.println("acceptor enviando:\t" + suggestion);
  }

  public void acceptSuggestion() {
    suggestion.accept = true;
    serial = suggestion.serial;
    value = suggestion.value;
    replyMessage();
  }

  public void confirmSuggestion() {
    Agreement agreement = new Agreement(getActor().getId(), getActor().getId());
    agreement.value = suggestion.value;
    agreement.done = false;
    getActor().getClient().broadcast(agreement);
  }

  @Override
  public void receive(ConsensusMessage message) {
    if (message instanceof Suggestion) {
      Suggestion msg = (Suggestion) message;
      if (!msg.seen && !msg.accept || msg.accord) {
        suggestion = (Suggestion) message;
        mutex.release();
      }
    }
  }

  @Override
  public void run() {
    try {
      while (true) {
        mutex.acquire();
        suggestion.seen = true;
        if (suggestion.accord) {
          confirmSuggestion();
        }
        else if (suggestion.serial >= serial) {
          acceptSuggestion();
        }
        else {
          recuseSuggestion();
        }

      }
    }
    catch (InterruptedException ex) {
      Logger.getLogger(Learner.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
