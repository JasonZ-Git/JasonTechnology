package crack_code_interview;

public class Q1_8_Zero_Matrix {

  public static int[][] toZeroMatrix(int[][] matrix) {

    int rowCount = matrix.length;
    int columnCount = matrix[0].length;

    boolean[] rowZero = new boolean[rowCount];
    boolean[] columnZero = new boolean[columnCount];

    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < columnCount; j++) {
        if (matrix[i][j] == 0) {
          rowZero[i] = true;
          columnZero[j] = true;
        }
      }
    }

    for (int i = 0; i < rowCount; i++) {
      if (rowZero[i]) {
        nullifyRow(matrix, i);
      }
    }

    for (int j = 0; j < rowCount; j++) {
      if (columnZero[j]) {
        nullifyColumn(matrix, j);
      }
    }


    return matrix;
  }

  private static void nullifyRow(int[][] matrix, int rowIndex) {
    for (int j = 0; j < matrix[0].length; j++) {
      matrix[rowIndex][j] = 0;
    }
  }

  private static void nullifyColumn(int[][] matrix, int columnIndex) {
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][columnIndex] = 0;
    }
  }
}
