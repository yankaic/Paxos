package run;

import protocol.RouteTable;
import view.Simulation;

/**
 *
 * @author Yan Kaic
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    setUIMananger();
    
  
    
    new Simulation().setVisible(true);

  }

  private static void setUIMananger() {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Windows".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Simulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

}
