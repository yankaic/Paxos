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

  public static Color staticColor(int value) {
    value %= 9;

    switch (value) {
      case 0:
        return Color.BLUE;
      case 1:
        return Color.RED;
      case 2:
        return Color.GREEN;
      case 3:
        return Color.MAGENTA;
      case 4:
        return Color.ORANGE;
      case 5:
        return Color.CYAN;
      case 6:
        return DARK_GREEN;
      case 7:
        return Color.PINK;
      case 8:
        return PURPURE;
      default:
        return Color.gray;
    }    
  }

  
}
