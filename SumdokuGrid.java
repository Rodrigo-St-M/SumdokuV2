/**
 * The class {@code SumdokuGrid} represents a square grid of ints
 * to be used in a SumdokuPuzzle.
 *
 * @author rodrigostamartacoelho fc61771
 */
public class SumdokuGrid{
   // atributes / fields

   private final int[][] squares;

   /* invariants
    *
    * squares eh uma matriz quadrada
    * 3 <= squares.length <= 9
    * 1 <= squares[i][j] <= squares.length
    */

   /**
    * The {@code SumdokuGrid} function is a constructor of SumdokuGrid
    * that creates a SumdokuGrid of a given size, with all squares with 
    * the value 0.
    * @param size The size of the SumdokuGrid ,i.e., the number of rows or columns it has.
    * @require {@code 3 >= size && size <= 9}
    */
   public SumdokuGrid(int size) {
      squares = new int[size][size];
   }

   /**
    * The {@code SumdokuGrid} function is a constructor of SumdokuGrid
    * that creates a SumdokuGrid with a given matrix.
    * @param matrix The matrix to create the new Sumdokugrid
    * @requires {@code isValidMatrix(matrix)}
    * 
    */
   public SumdokuGrid(int[][] matrix) {
      squares = SumdokuGrid.copyMatrix(matrix);
   }

   /**
    * The {@code isValidMatrix} function checks if the given matrix is
    * valid for constructing a SumdokuGrid.
    * A matrix is valid for contructing a SumdokuGrid if it is a square 
    * matrix and if it only has values between 0 and its size
    * @param matrix The matrix to evaluate
    * @return If the matrix is valid
    */
   public static boolean isValidMatrix(int[][] matrix) {
      boolean isValid = true;
      int i = 0;
      int arraysize = matrix[0].length;
      // while we don't know if it is invalid
      while(isValid && i < matrix.length) {
         // check if it's all lines have the same length
         if (arraysize != matrix[i].length)
            isValid = false;
         // check if it only has valid values
         for (int j = 0; j < matrix[i].length; j++) {
            if (-1 >= matrix[i][j] || matrix[i][j] > matrix.length)
            isValid = false;
         }
      }
      return isValid;
   }

   /**
    * The {@code Value} funtion returns the value of a square, given it's
    * row and column.
    * @param r The row of the square
    * @param c The column of the square
    * @return The value of the square
    */
   public int value(int r, int c) {
      return squares[r-1][c-1];
   }

   /**
    * The {@code size} function returns the size of this SumdokuGrid
    * ,i.e., the number of rows or columns it has.
    * @return The size of this SumdokuGrid.
    */
   public int size() {
      return squares.length;
   }

   /**
    * The {@code fill} function fills a specified square with a given value.
    * @param r The row of the square
    * @param c The column of the square
    * @param value The value to fill the square with
    * @requires {@code r <= this.size() && c <= this.size()}
    */
   public void fill(int r, int c, int value) {
      squares[r-1][c-1] = value;
   }

   /**
    * The {@code isFilled} function checks if a square is filled,
    * given it's row and column.
    * @param r The row of the square
    * @param c The column of the square
    * @return If the square is filled
    * @requires {@code sumdokugrid != null}
    */
   public boolean isFilled(int r, int c) {

      return !(value(r, c) == 0);
   }

   /**
    * The {@code FancyToString} returns a fancy string representation of the SumdokuGrid object
    * with a frame
    * @return a String representation of SumdokuGrid.
    */
   public String FancyToString() {
      StringBuilder str = new StringBuilder();
      int sideLength = squares.length * 2 + 1;

      // drawing the top of the frame
      str.append("╔");
      //str.repeat("═", squares.length*2+1); only works for java version 21+
      for(int col = 0; col < sideLength; col++) {
         str.append("═");
      }

      str.append("╗\n");

      // drawing the sides and the content of the Grid
      for (int[] rows : squares) {
         str.append("║ ");
         for (int i : rows) {
            str.append(i + " ");
         }
         str.append("║\n");
      }

      // drawing the bottom line of the frame
      str.append("╚");
      //str.repeat("═", squares.length*2+1); only works for java version 21+
      for(int col = 0; col < sideLength; col++) {
         str.append("═");
      }
      str.append("╝\n");

      return str.toString();
   }

   /**
    * The {@code toString} returns a string representation of the SumdokuGrid object
    * @return a String representation of SumdokuGrid.
    */
   public String toString() {
      StringBuilder str = new StringBuilder();
      for (int[] rows : squares) {
         for (int i : rows) {
            if(i == 0) {
               str.append(". ");
            }
            else 
            str.append(i + " ");
         }
         str.append("\n");
      }
      return str.toString();
   }

   /**
    * The {@code copyMatrix} function returns a copy of the given matrix.
    * @param matrix the matrix to copy
    * @return an independent copy of matrix
    */
   static private int[][] copyMatrix(int[][] matrix) {
      // the new independent matrix of same size
      int[][] copy = new int[matrix.length][matrix.length];

      // copy values to the copy matrix
      for (int r = 0; r < matrix.length; r++) {
         for (int c = 0; c < matrix.length; c++) {
            copy[r][c] = matrix[r][c];
         }
      }
      return copy;
   }
} 
