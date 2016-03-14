/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.io.Serializable;

/**
 *
 * @author Yan Kaic
 */
public class Hello implements Serializable{

  public String name;
  public int distance;

  public Hello(String name, int distance) {
    this.name = name;
    this.distance = distance;
  }

    
  
  
}
