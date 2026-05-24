/**
 * The class {@code SumdokuPuzzle} represents a SumdokuPuzzle
 *
 * @author rodrigostamartacoelho fc61771
 */
public class SumdokuPuzzle {

   private final int size;
   private final int[][] groupMembership;
   private final int[] groupsValues;
   private final SumdokuGrid puzzleSolution;

   /* invariants
    *
    * 3 <= size <= 9
    * groupMembership eh uma matriz quadrada de tamanho size
    * 1 <= groupsMembership[i][j] <= groupsValues.length
    * groupValues[i] >= 1
    */

   /**
    * The {@code definesPuzzle} function checks if the given 
    * groupMembership and groupsValues
    * defines a SumdokuPuzzle with a single solution.
    * 
    * @param groupMembership The matrix representing the groups of the Puzzle
    * @param groupValues The vector of the sums of each group in groupMembership
    * @requires {@code groupMembership != null && groupValues != null}
    * @ensures \result is true if the puzzle is valid and has a unique solution; false otherwise
    * @return  if groupMembership and groupValues defines a valid Puzzle
    */
   public static boolean definesPuzzle(int[][] groupMembership, int[] groupValues){
      if (groupMembership == null || groupValues == null) {
         return false;
      }

      // Return true only if all checks pass
      return (validGroupMembershipSize(groupMembership) 
              && validGroupsValues(groupMembership, groupValues) 
              && validGroupMembershipEntries(groupMembership, groupValues) 
              && validNumberOfGroups(groupMembership, groupValues) 
              && puzzleUniqueSolution(groupMembership, groupValues));
   }

   /**
    * The {@code validGroupMembershipSize} function checks if the groupMembership matrix 
    * has a valid size for a Sumdoku puzzle.
    *
    * @param groupMembership The matrix representing the groups of the puzzle.
    * @requires {@code groupMembership != null}.
    * @ensures \result is true if groupMembership is square and its size is between 3 and 9; false otherwise.
    * @return true if the groupMembership matrix has a valid size; false otherwise.
    */
   private static boolean validGroupMembershipSize(int[][] groupMembership){
      // Check if the matrix size is within the allowed range
      if (groupMembership.length < 3 || groupMembership.length > 9){
         return false;
      }

      // Ensure the matrix is square
      for(int row = 0; row < groupMembership.length; row++){
         if(groupMembership[row].length != groupMembership.length){
            return false;
         }
      }
      return true;
   }

   /**
    * The {@code validGroupsValues} function checks if the groupValues array contains valid sums 
    * for a Sumdoku puzzle.
    *
    * @param groupMembership The matrix representing the groups of the puzzle.
    * @param groupValues The array containing the sums for each group in the puzzle.
    * @requires {@code groupMembership != null && groupValues != null}.
    * @ensures \result is true if groupValues contains positive sums and has a valid length 
    *          relative to groupMembership; false otherwise.
    * @return true if the groupValues array is valid; false otherwise.
    */
   private static boolean validGroupsValues(int[][] groupMembership, int[]groupValues){
      // Calculate the total number of cells in the grid
      int totalSquares = groupMembership.length*groupMembership.length;

      // Check if the length of groupValues is within the valid range
      if (groupValues.length < 1 || groupValues.length > (totalSquares)){
         return false;
      }

      // Check if each group value is valid
      for (int i = 0; i < groupValues.length; i++) {
         if(groupValues[i] == 0){
            return false;
         }
         else{
            if(groupValues[i] < 1 || groupValues[i] > ((totalSquares) + (totalSquares*groupMembership.length))/2){
               return false;
            }
         }
      }
      return true;
   }

   /**
    * The {@code validGroupMembershipEntries} function checks if all entries in the groupMembership matrix 
    * are valid group indices.
    *
    * @param groupMembership The matrix representing the groups of the puzzle.
    * @param groupValues The array containing the sums for each group in the puzzle.
    * @requires {@code groupMembership != null && groupValues != null}.
    * @ensures \result is true if all entries in groupMembership are between 0 and groupValues.length - 1; 
    *          false otherwise.
    * @return true if all entries in groupMembership are valid; false otherwise.
    */
   private static boolean validGroupMembershipEntries (int[][] groupMembership, int[] groupValues){
      // Check if each entry in groupMembership is within the valid range
      for(int row = 0; row < groupMembership.length; row++){
         for (int col = 0; col < groupMembership.length; col++){
            if (groupMembership[row][col] < 0 || groupMembership[row][col] >= groupValues.length){
               return false;
            }
         }
      }
      return true;
   }

   /**
    * The {@code validNumberOfGroups} function checks if every group defined in groupValues 
    * has at least one corresponding cell in groupMembership.
    *
    * @param groupMembership The matrix representing the groups of the puzzle.
    * @param groupValues The array containing the sums for each group in the puzzle.
    * @requires {@code groupMembership != null && groupValues != null}.
    * @ensures \result is true if each group in groupValues has at least one cell in groupMembership; 
    *          false otherwise.
    * @return true if all groups in groupValues are represented in groupMembership; false otherwise.
    */
   private static boolean validNumberOfGroups (int[][] groupMembership, int[] groupValues){
      // Check if each group in groupValues appears at least once in groupMembership
      for (int g = 0; g < groupValues.length; g++){
         boolean isFound = false;
         for(int row = 0; row < groupMembership.length; row++){
            for(int col = 0; col < groupMembership.length; col++){
               if(groupMembership[row][col] == g){
                  isFound = true;
               }
            }
         }
         if (isFound == false){
            return false;
         }
      }
      return true;
   }

   /** 
    * The {@code puzzleUniqueSolution} function checks if the given groupMembership 
    * and groupValues define a puzzle with a unique solution.
    *
    * @param groupMembership The matrix representing the groups of the puzzle.
    * @param groupValues The array containing the sum for each group in the puzzle.
    * @requires {@code groupMembership != null && groupValues != null}.
    * @ensures \result is true if the puzzle admits exactly one solution; false otherwise.
    * @return true if the puzzle admits exactly one solution; false otherwise.
    */
   private static boolean puzzleUniqueSolution (int[][] groupMembership, int[] groupValues){
      // Validate that all entries in groupMembership are valid group indices
      for (int[] row : groupMembership) {
         for (int value : row) {
             if (value < 0 || value >= groupValues.length) {
                 return false;
             }
         }
      }
      // Use the SumdokuSolver to check the number of solutions
      SumdokuSolver solver = new SumdokuSolver(groupMembership, groupValues);

      // Return true if there is exactly one solution
      return solver.howManySolutions(2) == 1;
   }

   /** 
    * The {@code SumdokuPuzzle} constructor creates a new SumdokuPuzzle with 
    * given groupMembership and groupValues.
    *
    * @param groupMembership The matrix representing the groups of the Puzzle
    * @param groupValues The vector of the sums of each group in groupMembership
    * @require {@code definesPuzzle(groupMembership, groupValues) == true}
    */
   public SumdokuPuzzle(int[][] groupMembership, int[] groupValues){
      SumdokuSolver solver = new SumdokuSolver(groupMembership, groupValues);

      // Set the size of the puzzle grid
      this.size = groupMembership.length;

      // Initialize and copy the groupMembership matrix
      this.groupMembership = new int[size][size];
      for(int row = 0; row < size; row++){
         for(int col = 0; col < size; col++){
            this.groupMembership[row][col] = groupMembership[row][col];
         }
      }

      // Initialize and copy the groupValues array
      this.groupsValues = new int[groupValues.length];
      for(int group = 0; group < groupValues.length; group++){
         this.groupsValues[group] = groupValues[group];
      }

      //finds solution to this groupMembership and groupvalues and save it
      puzzleSolution = new SumdokuGrid(solver.findSolutions(2)[0]);
   }

   /**
    * The {@code size} function returns the size of the SumdokuPuzzle
    * ,i.e., the number of rows or columns it has.
    *
    * @return the size of the SumdokuPuzzle
    */
   public int size() {
      return this.size;
   }

   /**
    * The {@code numberOfGroups} function returns the number 
    * of groups of the SumdokuPuzzle.
    *
    * @return The number of groups in SumdokuPuzzle
    */
   public int numberOfGroups() {
      return this.groupsValues.length;
   }

   /**
    * The {@code groupNumber} function returns the group of a 
    * specified square of the SumdokuPuzzle.
    *
    * @param col The column of the square 
    * @param row The row of the square
    * @requires 1 <= col <= size && 1 <= size
    * @ensures \result is the group number corresponding to the given cell
    * @return The group number of the given square
    */
   public int groupNumber(int row, int col) {
      return groupMembership[row-1][col-1]+1;
   }

   /**
    * The {@code valueGroup} function returns the sum associated with a specific group in the puzzle.
    *
    * @param group The group number for which the sum is requested.
    * @requires {@code 0 <= group < numberOfGroups()}.
    * @ensures \result is the sum associated with the given group.
    * @return the sum assigned to the specified group.
    */
   public int valueGroup(int group) {
      return groupsValues[group-1];
   }

   /**
    * The {@code isSolvedBy} function checks if a given SumdokuGrid is a valid solution for this puzzle.
    *
    * @param playedGrid The SumdokuGrid to check if it solves the puzzle.
    * @requires {@code playedGrid != null && playedGrid.size() == this.size}.
    * @ensures \result is true if the playedGrid solves the puzzle; false otherwise.
    * @return true if playedGrid is a valid solution for this puzzle; false otherwise.
    */
   public boolean isSolvedBy(SumdokuGrid playedGrid) {

      boolean isEqual = true;
      for (int row = 1; row <= playedGrid.size(); row++) {
         for (int col = 1; col <= playedGrid.size(); col++) {
            if(puzzleSolution.value( row, col ) != playedGrid.value( row, col ))
               isEqual = false;
         }
      }
      return isEqual;
      
   }
   
   /**
    * The {@code isPartiallySolvedBy} function checks if a given SumdokuGrid is a valid partial solution 
    * for this puzzle.
    *
    * @param playedGrid The SumdokuGrid to check if it is a partial solution.
    * @requires {@code playedGrid != null && playedGrid.size() == this.size}.
    * @ensures \result is true if the filled cells in playedGrid match the solution for this puzzle 
    *          and do not violate any constraints; false otherwise.
    * @return true if playedGrid is a valid partial solution for this puzzle; false otherwise.
    */
   public boolean isPartiallySolvedBy(SumdokuGrid playedGrid) {

      boolean isEqual = true;
      for (int row = 1; row <= playedGrid.size(); row++) {
         for (int col = 1; col <= playedGrid.size(); col++) {
            if(playedGrid.isFilled(row, col)) {
            if(puzzleSolution.value( row, col ) != playedGrid.value( row, col ))
               isEqual = false;
            }
         }
      }
      return isEqual;
      
   }

   /**
    * The {@code cluesToString} function returns a textual representation of the clues for this puzzle.
    * The clues include the group membership matrix and the sums of each group.
    *
    * @ensures \result is a string containing the group matrix and the group sums.
    * @return a string representation of the puzzle clues.
    */
   public String cluesToString() {
      StringBuilder clues = new StringBuilder();

      // Append the group membership matrix
      for (int row = 1; row <= size(); row++) {
         for (int col = 1; col <= size(); col++) {
            clues.append(" ").append(groupNumber(row, col));
         }
         clues.append("\n");
      }

      // Append the group values
      for(int group = 1; group <= numberOfGroups(); group++){
         clues.append("G").append(group).append(" = ").append(valueGroup(group)).append(" ");
      }
      clues.append("\n");

      return clues.toString();
   }

   /**
    * The {@code toString} function returns a textual representation of the Sumdoku puzzle.
    * The representation includes the group membership matrix.
    *
    * @ensures \result is a formatted string representing the puzzle.
    * @return a string representation of the puzzle.
    */
   public String toString() {
      StringBuilder str = new StringBuilder();
      for (int[] rows : groupMembership) {
         str.append(" ");
         for (int i : rows) {
            str.append(i + " ");
         }
         str.append("\n");
      }

      return str.toString();
   }

   public String FancyToString() {
      StringBuilder str = new StringBuilder();
      int sideLength = groupMembership.length * 2 + 1;
      // drawing the top of the frame
      str.append("╔");
      for(int col = 0; col < sideLength; col++) {
         str.append("═");
      }
      str.append("╗\n");

      // drawing the sides and the content of the groupMembership
      for (int[] rows : groupMembership) {
         str.append("║ ");
         for (int i : rows) {
            str.append(i + " ");
         }
         str.append("║\n");
      }

      // drawing the bottom line of the frame
      str.append("╚");
      for(int col = 0; col < sideLength; col++) {
         str.append("═");
      }
      str.append("╝\n");

      return str.toString();
   }
}
