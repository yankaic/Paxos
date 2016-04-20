package consensus.category;

import consensus.Actor;
import consensus.Agent;
import consensus.messages.Suggestion;
import consensus.messages.ConsensusMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proposer extends Agent {

  private int serial;
  private int round = 0;

  private Suggestion suggestion;

  public Proposer(Actor actor) {
    super(actor);
    this.serial = 0;
  }

  /**
   *
   */
  public void createSuggestion() {
    try {
      serial = (int) (Math.random() * 10) + getValue();
      int value = (int) (Math.random() * serial + serial / 3.0*2.0);
      setValue(value);
      Suggestion propose = new Suggestion(getActor().getId(), getActor().getId());
      propose.value = value;
      propose.serial = serial;
      propose.seen = false;
      propose.accept = false;
      propose.accord = false;
      sleep(1000);
      getActor().getClient().broadcast(propose);
    }
    catch (InterruptedException ex) {
      Logger.getLogger(Proposer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   *
   */
  public void confirmSuggestion() {
    Suggestion propose = new Suggestion(getActor().getId(), getActor().getId());
    propose.value = getValue();
    propose.serial = serial;
    propose.accord = true;
    propose.accept = true;
    propose.seen = true;
    getActor().getClient().broadcast(propose);

  }

  @Override
  public void receive(ConsensusMessage message) {
    if (message instanceof Suggestion) {
      Suggestion msg = (Suggestion) message;
      if (msg.seen && !msg.accord) {
        suggestion = (Suggestion) message;
        mutex.release();
      }
    }
  }

  @Override
  public void run() {
    int accepteds = 0;
    int recuseds = 0;
    int delay;
    while (true) {
      try {
        delay = (int) (Math.random() * 5000 + 5000);
        sleep(delay);
        createSuggestion();
        accepteds = 0;
        recuseds = 0;

        int halfSize = getActor().getNodes().size();
        while (accepteds <= halfSize / 2 && recuseds <= halfSize / 2) {
          mutex.acquire();

          if (suggestion.accept && suggestion.serial == serial) {
            accepteds++;
          }
          else {
            recuseds++;
          }
        }//fim do laco
        if (accepteds > recuseds) {
          confirmSuggestion();
        }
      }
      catch (InterruptedException ex) {
        Logger.getLogger(Proposer.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

}
