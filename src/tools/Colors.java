/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Color;

/**
 *
 * @author Yan Kaic
 */
public class Colors {

  public static final Color PURPURE = new Color(204, 51, 255);
  public static final Color DARK_GREEN = new Color(51, 204, 51);

  public static Color staticColor(String name) {

    switch (name) {
      case "A":
        return Color.BLUE;
      case "B":
        return Color.RED;
      case "C":
        return Color.GREEN;
      case "D":
        return Color.MAGENTA;
      case "E":
        return Color.ORANGE;
      case "F":
        return Color.CYAN;
      case "G":
        return DARK_GREEN;
      case "H":
        return Color.PINK;
      case "I":
        return PURPURE;
      default:
        return Color.gray;
    }
    
  }

  public static Color colorPort(String portString) {
    int port = Integer.parseInt(portString);
    port -= 3030;
    char letter = 'A';
    letter+=port;
    return staticColor(letter+"");
  }
}
