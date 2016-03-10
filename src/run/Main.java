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
    
    RouteTable tableA = new RouteTable("A");
    RouteTable tableB = new RouteTable("B");
    RouteTable tableC = new RouteTable("C");

    tableA.add("B", "B", 2);
    tableA.add("C", "C", 7);
    tableB.add("A", "A", 2);
    tableB.add("C", "C", 4);
    tableC.add("B", "B", 4);
    tableC.add("A", "A", 7);
    tableC.add("D", "D", 10);

    tableB.merge(tableC);
    tableA.merge(tableB);

    tableC.remove(tableC.get("D"));

    tableB.merge(tableC);
    tableA.merge(tableB);

    System.out.println(tableA);
    System.out.println(tableB);
    System.out.println(tableC);
    
    new Simulation().setVisible(true);

  }

  private static void setUIMananger() {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        System.out.println(info.getName());
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
