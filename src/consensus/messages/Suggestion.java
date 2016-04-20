package consensus.messages;

public class Suggestion extends ConsensusMessage {

  public int serial;
  public int value;
  public boolean seen;
  public boolean accept;
  public boolean accord;

  public Suggestion(int sender, int receiver) {
    super(sender, receiver);
  }

  @Override
  public String toString() {
    String string = "[CONSENSUS MESSAGE]";
    string += "\tfrom: " + sender;
    string += "\tto: " + receiver;
    string += "\tserial: " + serial;
    string += "\tvalue: " + value;
    string += "\tseen: " + seen;
    string += "\taccepted: " + accept;
    string += "\taccorded: " + accord;
    string += "\t[END MESSAGE]";

    return string;
  }

}
