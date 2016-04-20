package consensus.messages;

import java.io.Serializable;

/**
 *
 * @author Yan Kaic
 *
 */
public class ConsensusMessage implements Serializable {

  public int sender;

  public int receiver;

  public ConsensusMessage(int sender, int receiver) {
    this.sender = sender;
    this.receiver = receiver;
  }

}
