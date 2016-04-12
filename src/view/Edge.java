/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Yan Kaic
 */
public class Edge {

  private Node A, B;
  private Color color;

  public Edge() {
  }

  public Edge(Node A, Node B, Color color) {
    this.A = A;
    this.B = B;
    this.color = color;
  }
  
  public boolean equals(Edge edge){
    boolean vertex, invert, colors;
    vertex = A == edge.getA() && B == edge.getB();
    invert = A == edge.getB() && B == edge.getA();
    colors = color == edge.getColor();
    return colors && (vertex || invert);
  }

  public void add(Node node) {
    if (isComplete()) {
      return;
    }
    if (isEmpty()) {
      A = node;
    }
    else if (node != A) {
      B = node;
    }
  }

  public Node getA() {
    return A;
  }

  public Node getB() {
    return B;
  }

  public Point getPointA() {
    int x = A.getX() + A.getWidth() / 2;
    int y = A.getY() + A.getHeight() / 2;
    return new Point(x, y);
  }

  public boolean isEmpty() {
    return A == null;
  }

  public boolean isIncomplete() {
    return (A != null && B == null);
  }

  public boolean isComplete() {
    return B != null;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
  
  
}
