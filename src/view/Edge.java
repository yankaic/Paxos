/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Point;

/**
 *
 * @author Yan Kaic
 */
public class Edge {

  private Node A, B;

  public Edge() {
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
}
