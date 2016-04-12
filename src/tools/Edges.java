/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import view.Edge;

/**
 *
 * @author Yan Kaic
 */
public class Edges extends ArrayList<Edge>{

  public void addEdge(Edge edge){
    if(!has(edge)){
      add(edge);
    }
  }

  private boolean has(Edge edge) {
    for (int i = 0; i < size(); i++) {
      if(get(i).equals(edge))
        return true;      
    }
    return false;
  }
}
