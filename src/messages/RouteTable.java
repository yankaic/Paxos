package messages;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Yan Kaic
 */
public class RouteTable extends ArrayList<TableLine> implements Serializable {

  static char lastId = 'A';
  private char id;

  private String author;

  public RouteTable() {
   
  }

  public RouteTable(String author) {
    this.author = author;
    init();
  }

  private void init() {
    add(author, author, 0);
    id = lastId++;
  }

  public String getAuthor() {
    return author;
  }

  public void autentic(String author) {
    this.author = author;
    remove(0);
    add(0, new TableLine(author, author, 0));
  }

  public boolean has(TableLine search) {
    return has(search.getTarget());
  }

  public boolean has(String target) {
    return get(target) != null;
  }
 
  public void addLine(TableLine line){
    if(!has(line)){
      add(line);
    }
    else{      
      TableLine get = get(line.getLink());
      if(!get.getLink().equals(line.getTarget())){
        add(line);
      }
    }
  }

  public void add(String target, String link, int distance) {
    add(new TableLine(target, link, distance));
  }

  public TableLine get(String target) {
    for (TableLine line : this) {
      if (line.getTarget().equals(target)) {
        return line;
      }
    }
    return null;
  }

  public void merge(RouteTable othertable) {
    //verificando se a tabela eh conectada diretamente a esta tabela
    TableLine found = get(othertable.getAuthor());
    if (found == null || !found.isDirect()) {
      return;
    }
    union(othertable);
    overlap(othertable);
  }

  private void union(RouteTable othertable) {
    int distance = get(othertable.getAuthor()).getDistance();
    for (TableLine line : othertable) {
      try {
        TableLine thisline = get(line.getTarget());
        int thisLineDistance = thisline.getDistance();
        int thatLineDistance = line.getDistance() + distance;
        if (thatLineDistance < thisLineDistance) {
          thisline.update(othertable.getAuthor(), thatLineDistance);
        }
      }
      catch (NullPointerException e) {
        add(line.getTarget(), othertable.author, line.getDistance() + distance);
      }
    }
  }

  private void overlap(RouteTable othertable) {
    int distance = get(othertable.getAuthor()).getDistance();
    for (int i = 0; i < size(); i++) {
      TableLine line = get(i);
      if (line.getLink().equals(othertable.getAuthor())) {
        if (othertable.has(line)) {
          int otherDistance = othertable.get(line.getTarget()).getDistance();
          line.update(othertable.getAuthor(), distance + otherDistance);
        }
        else {
          remove(line);
        }
      }
    }

  }

  @Override
  public String toString() {
    String output = "Author: " + author + "\t id:" + id + '\n';
    for (TableLine line : this) {
      output += line.toString() + '\n';
    }
    return output;
  }

  public void removeLink(String link) {
    TableLine line = null;
    for (int x = 0; x < size(); x++) {
      line = get(x);
      if (line.getLink().equals(link)) {
        break;
      }
    }
    if (line != null) {
      remove(line);
    }
  }

  public String getId() {
    return id + "";
  }

}
