package tools;

import java.util.ArrayList;

public class Matrix<Generic> {

  private final ArrayList<ArrayList<Generic>> matrix;

  public Matrix() {
    matrix = new ArrayList<>();
  }

  public Generic get(int line, int column) {
    return matrix.get(line).get(column);
  }

  public void grow() {
    ArrayList<Generic> list = new ArrayList<>();
    matrix.add(list);

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.size(); j++) {
        try {
          matrix.get(i).get(j);
        }
        catch (IndexOutOfBoundsException e) {
          matrix.get(i).add(null);
        }
      }
    }
  }

  public void insertLastTitle(Generic item) {
    final int lastIndex = matrix.size() - 1;
    matrix.get(0).set(lastIndex, item);
    matrix.get(lastIndex).set(0, item);
    
  }

  @Override
  public String toString() {
    String output = "";

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.size(); j++) {
        output += matrix.get(i).get(j) + "\t";
      }
      output += '\n';
    }

    return output;
  }

  public static void main(String[] args) {
    Matrix matrix = new Matrix();
    matrix.grow();
    matrix.grow();
    matrix.grow();
    matrix.grow();
    matrix.set("OI", 2, 1);
    matrix.grow();
    matrix.grow();
    matrix.insertLastTitle("Título");
    System.out.println(matrix);
  }

  private void set(Generic object, int line, int column) {
    matrix.get(line).set(column, object);
  }

}
