package consensus;

import consensus.messages.ConsensusMessage;
import java.util.concurrent.Semaphore;

public abstract class Agent extends Thread {

  protected int value;

  protected final Semaphore mutex;

  private final Actor actor;
  
  public Agent(Actor actor){
    this.actor = actor;
    this.mutex = new Semaphore(0);
  }

  public Actor getActor() {
    return actor;
  }
  
  

  /**
   *
   * @param message
   */
  public abstract void receive(ConsensusMessage message);

  /**
   *
   */
  public void echo() {

  }

  /**
   *
   */
  @Override
  public abstract void run();

}
