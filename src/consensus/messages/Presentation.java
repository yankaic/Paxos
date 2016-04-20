package consensus.messages;

public class Presentation extends ConsensusMessage {

  public static final char ACCEPTOR = 'a';

  public static final char PROPOSER = 'p';

  public static final char LEARNER = 'l';

  public char tag;


  public Presentation(int sender, int receiver) {
    super(sender, receiver);
  }

}
