package messages;

import com.google.gson.Gson;
import java.io.Serializable;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

/**
 *
 * @author Yan Kaic
 */
public class TableLine implements Serializable {

  protected String target;
  protected String link;
  protected int distance;

  public TableLine(String target, String link, int distance) {
    this.target = target;
    this.link = link;
    this.distance = distance;
  }

  public String getTarget() {
    return target;
  }

  public String getLink() {
    return link;
  }

  public int getDistance() {
    return distance;
  }

  public boolean isDirect() {
    return target.equals(link);
  }

  public void update(String link, int distance) {
    this.link = link;
    this.distance = distance;
  }
  
  public static void load(String string){
   
  }

  @Override
  public String toString() {
    return '[' + target + ", " + link + ", " + distance + ']';
  }
  
 

}
