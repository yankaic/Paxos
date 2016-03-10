/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Yan Kaic
 */
public class Node extends javax.swing.JPanel {

  private static char name = 'A';

  /**
   * Creates new form Node
   */
  public Node() {
    initComponents();
    init();
  }

  private void init() {
    setBackground(Color.BLUE);
    label.setText(name++ + "");
    mouseEvents();
    setSize(45, 45);
    label.setSize(45, 45);
  }
  
  @Override
  public String getName(){
    return label.getText();
  }

  public void moveMouse() throws HeadlessException {
    Point mouse = MouseInfo.getPointerInfo().getLocation();
    Point tela = getLocationOnScreen();

    tela.x -= getLocation().x;
    tela.y -= getLocation().y;

    mouse.x -= tela.x + getWidth() / 2;
    mouse.y -= tela.y + getHeight() / 2;

    setLocation(mouse);
  }

  private void mouseEvents() {
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        moveMouse();
      }

      @Override
      public void mouseMoved(MouseEvent e) {
      }
    });
  }

  @Override
  public void paintComponent(Graphics gph) {
    Graphics2D graphics = (Graphics2D) gph.create();
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics.setPaint(backColor);
    graphics.fillOval(1, 1, getWidth() - 3, getHeight() - 3);
    graphics.setPaint(getBackground());
    graphics.setStroke(new BasicStroke(2));
    graphics.drawOval(1, 1, getWidth() - 3, getHeight() - 3);
    graphics.setPaint(Color.black);
    super.paintComponent(gph);

  }
  private final Color backColor = new Color(245, 245, 245);

  ;

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    label = new javax.swing.JLabel();

    setMaximumSize(new java.awt.Dimension(45, 45));
    setMinimumSize(new java.awt.Dimension(45, 45));
    setOpaque(false);
    setPreferredSize(new java.awt.Dimension(45, 45));
    setLayout(new java.awt.BorderLayout());

    label.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
    label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label.setText("A");
    label.setMaximumSize(new java.awt.Dimension(45, 45));
    label.setMinimumSize(new java.awt.Dimension(45, 45));
    label.setPreferredSize(new java.awt.Dimension(45, 45));
    add(label, java.awt.BorderLayout.CENTER);
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel label;
  // End of variables declaration//GEN-END:variables
}
