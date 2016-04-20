package consensus.messages;

public class Agreement extends ConsensusMessage {

  public int value;
  public boolean done;

  public Agreement(int sender, int receiver) {
    super(sender, receiver);
  }
  
  public boolean isDone(){
    return done;
  }

}
