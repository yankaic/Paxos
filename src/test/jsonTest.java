/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import messages.RouteTable;

/**
 *
 * @author Yan Kaic
 */
public class jsonTest {
  public static void main(String[] args) {
    RouteTable table = new RouteTable("A");
    table.add("B", "B", 90);
    table.add("C", "B", 90);
    table.add("D", "B", 90);
    table.add("E", "B", 90);
    System.out.println(table);
    
    Gson parse = new Gson();
    String str = parse.toJson(table);
    System.out.println(str);
    RouteTable other = parse.fromJson(str, RouteTable.class);
    System.out.println(other);
  }
  
}
