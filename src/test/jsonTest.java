/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Yan Kaic
 */
public class jsonTest {
  public static void main(String[] args) {
    Filho filho = new Filho('a');
    
    if(filho instanceof Pai){
      System.out.println("eu escolhi pai");
    }
    else{
      System.out.println("eu escolhi filho");
    }
  }
  
}
