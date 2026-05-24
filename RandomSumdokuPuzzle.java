import java.util.Random;
/**
 * The {@code RandomSumdokuPuzzle} class generates sumdokuPuzzles picking
 * randomly from a pool of built in SumdokuPuzzles.
 *
 * @author rodrigostamartacoelho fc61771
 */
public class RandomSumdokuPuzzle {

   // atributes / fields

   // The size of the puzzles to give
   private final int size;

   // How many puzzles have been used
   private int puzzlesUsed;

   // Random Class for 
   private final Random random;

   // The available puzzles
   private SumdokuPuzzle[] puzzles;

   private final int NUM_SIZE_3_PUZZLES = 3;
   private final int NUM_SIZE_5_PUZZLES = 4;

   /* invariants
    *
    * 3 <= size <= 9
    * puzzlesUsed >= 0
    *
    */

   /**
    * The {@code RandomSumdokuPuzzle} creates a new RandomSumdokuPuzzle
    * for puzzles of the given size, if supported.
    * @param size the size of the puzzle to generate
    * @require {@code size == 3 || size == 5}
    */
   public RandomSumdokuPuzzle(int size) {
      // initializes atributes
      this.size = size;
      puzzlesUsed = 0;
      random = new Random();

      //depending on size
      switch (size) {
         case 3:
            // initialize puzzles with puzzles of size 3 in a random order
            puzzles = shuffleArray(size3Puzzles());
            break;
      
         case 5:
            // initialize puzzles with puzzles of size 5 in a random order
            puzzles = shuffleArray(size5Puzzles());
            break;
         default:
            //if size is not supported
            puzzles = null;
      }
   }

   public RandomSumdokuPuzzle(int size, long seed) {
      // initializes atributes
      this.size = size;
      puzzlesUsed = 0;
      random = new Random(seed);

      //depending on size
      switch (size) {
         case 3:
            // initialize puzzles with puzzles of size 3 in a random order
            puzzles = shuffleArray(size3Puzzles());
            break;
      
         case 5:
            // initialize puzzles with puzzles of size 5 in a random order
            puzzles = shuffleArray(size5Puzzles());
            break;
         default:
            //if size is not supported
            puzzles = null;
      }
   }

   /**
    * The {@code size} function returns the size of the RandomSumdokuPuzzle
    * that generates.
    * @return The size of the SumdokuPuzzle to generate
    */
   public int size() {
      return size;
   }

   /**
    * The {@code hasNextPuzzle} function checks if it has at least one more
    * puzzle for it's size.
    *
    * @return if it has a SumdokuPuzzle
    */
   public boolean hasNextPuzzle() {
      return puzzles != null && (puzzlesUsed < puzzles.length);
   }

   /**
    * The (@code nextPuzzle) function returns the next SumdokuPuzzle for it's size.
    * if it doesnt have one, returns {@code null}.
    *
    * @return The next SumdokuPuzzle
    */
   public SumdokuPuzzle nextPuzzle() {
      puzzlesUsed++;
      return (puzzlesUsed > puzzles.length)? null : puzzles[puzzlesUsed-1];
   }

   /**
    * The {@code size3Puzzles} function returns a array of built-in SumdokuPuzzles
    * with size 3
    * @return The array of SumdokuPuzzles
    */
   private SumdokuPuzzle[] size3Puzzles() {
      SumdokuPuzzle[] puzzles = new SumdokuPuzzle[NUM_SIZE_3_PUZZLES];
      puzzles[0] = SumPuzzle3A();
      //puzzles[1] = SumPuzzle3B();
      //puzzles[2] = SumPuzzle3C();
      puzzles[1] = SumPuzzle3D();
      puzzles[2] = SumPuzzle3E();

      // puzzles B and C have the same solution to D and E respectively
      // causing a FAIL on nextTest because found will not be equal to 2
      return puzzles;
   }

   /**
    * The {@code size3Puzzles} function returns a array of built-in SumdokuPuzzles
    * with size 5
    * @return The array of SumdokuPuzzles
    */
   private SumdokuPuzzle[] size5Puzzles() {
      SumdokuPuzzle[] puzzles = new SumdokuPuzzle[NUM_SIZE_5_PUZZLES];
      puzzles[0] = SumPuzzle5A();
      puzzles[1] = SumPuzzle5B();
      puzzles[2] = SumPuzzle5C();
      puzzles[3] = SumPuzzle5D();
      return puzzles;
   }
   

   /**
    * The {@code SumPuzzle3A} function returns the built-in 'A' SumdokuPuzzle
    * of size 3 and solution:
    * {2, 1, 3},
    * {3, 2, 1},
    * {1, 3, 2}
    *
    * @return A valid built-in SumdokuPuzzle of size 3
    */
   private SumdokuPuzzle SumPuzzle3A() {
      int[][] groupMembership = {{0, 1, 1},
                                 {2, 3, 1},
                                 {3, 3, 4}};
      int[] groupValues = {2, 5, 3, 6, 2};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle3B} function returns the built-in 'B' SumdokuPuzzle
    * of size 3 and solution:
    * {3, 1, 2},
    * {1, 2, 3},
    * {2, 3, 1}
    *
    * @return A valid built-in SumdokuPuzzle of size 3
    */
   private SumdokuPuzzle SumPuzzle3B() {
      int[][] groupMembership = {{0, 1, 2},
                                 {1, 2, 0},
                                 {2, 0, 1}};
      int[] groupValues = {9, 6, 3};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle3C} function returns the built-in 'C' SumdokuPuzzle
    * of size 3 and solution:
    * {1, 2, 3},
    * {2, 3, 1},
    * {3, 2, 1}
    *
    * @return A valid built-in SumdokuPuzzle of size 3
    */
   private SumdokuPuzzle SumPuzzle3C() {
      int[][] groupMembership = {{0, 1, 1},
                                 {0, 2, 3},
                                 {4, 4, 3}};
      int[] groupValues = {3, 5, 3, 3, 4};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle3D} function returns the built-in 'D' SumdokuPuzzle
    * of size 3 and solution:
    * {3, 1, 2}
    * {1, 2, 3}
    * {2, 3, 1}
    *
    * @return  valid built-in SumdokuPuzzle of size 3
    */
   private SumdokuPuzzle SumPuzzle3D() {
      int[][] groupMembership = {{0, 0, 2},
                                 {0, 1, 2},
                                 {3, 3, 4}};
		int[] groupValues = {5, 2, 5, 5, 1};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle3E} function returns the built-in 'E' SumdokuPuzzle
    * of size 3 and solution:
    * {1, 2, 3}
    * {2, 3, 1}
    * {3, 1, 2}
    *
    * @return valid built-in SumdokuPuzzle of size 3
    */
   private SumdokuPuzzle SumPuzzle3E() {
      int[][] groupMembership = {{0, 0, 0},
                                 {0, 0, 1},
                                 {0, 1, 1}};
		int[] groupValues = {14, 4};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle5A} function returns the built-in 'A' SumdokuPuzzle
    * of size 5 and solution:
    * {4, 2, 3, 5, 1},
    * {2, 3, 5, 1, 4},
    * {3, 5, 1, 4, 2},
    * {5, 1, 4, 2, 3},
    * {1, 4, 2, 3, 5}
    *
    * @return A valid built-in SumdokuPuzzle of size 5
    */
   private SumdokuPuzzle SumPuzzle5A() {
      int[][] groupMembership = {{0, 1, 1, 1, 1},
                                 {0, 2, 2, 3, 3},
                                 {0, 2, 4, 4, 5},
                                 {0, 6, 6, 7, 5},
                                 {8, 8, 9, 7, 5}};
      int[] groupValues = {14, 11, 13, 5, 5, 10, 5, 5, 5, 2};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle5B} function returns the built-in 'B' SumdokuPuzzle
    * of size 5 and solution:
    * {1, 4, 2, 5, 3},
    * {5, 3, 4, 2, 1},
    * {2, 1, 5, 3, 4},
    * {3, 2, 1, 4, 5},
    * {4, 5, 3, 1, 2} errada
    *
    * @return A valid built-in SumdokuPuzzle of size 5
    */
   private SumdokuPuzzle SumPuzzle5B() {
      int[][] groupMembership = {{0, 1, 2, 3, 3},
                                 {4, 0, 2, 5, 5},
                                 {6, 6, 0, 7, 7},
                                 {6, 6, 8, 0, 7},
                                 {6, 6, 8, 9, 0}};
      int[] groupValues = {12, 4, 6, 8, 5, 3, 17, 12, 4, 4};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code SumPuzzle5C} function returns the built-in 'C' SumdokuPuzzle
    * of size 5 and solution:
    * {1, 4, 2, 3, 5},
    * {2, 3, 4, 5, 1},
    * {4, 5, 1, 2, 3},
    * {5, 1, 3, 4, 2},
    * {3, 2, 5, 1, 4}
    *
    * @return A valid built-in SumdokuPuzzle of size 5
    */
   private SumdokuPuzzle SumPuzzle5C() {
      int[][] groupMembership = {{ 0,  1,  2,  3,  4},
                                 { 0,  5,  6,  3,  4},
                                 { 7,  6,  6,  6,  4},
                                 { 7,  8,  8,  9,  4},
                                 {10, 10, 11,  4,  4}};
      int[] groupValues = {3, 4, 2, 8, 16, 3, 12, 9, 4, 4, 5, 5};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }
   
   /**
    * The {@code SumPuzzle5D} function returns the built-in 'D' SumdokuPuzzle
    * of size 5 and solution:
    * {2, 5, 3, 1, 4},
    * {5, 3, 4, 2, 1},
    * {1, 2, 5, 4, 3},
    * {4, 1, 2, 3, 5},
    * {3, 4, 1, 5, 2}
    *
    * @return A valid built-in SumdokuPuzzle of size 5
    */
   public SumdokuPuzzle SumPuzzle5D() {
      int[][] groupMembership = {{0, 0, 0, 1, 2},
							            {3, 3, 0, 1, 2},
							            {4, 5, 6, 6, 7},
							            {4, 5, 8, 8, 7},
							            {9, 9, 9, 10, 10}};
      int[] groupValues = {14, 3, 5, 8, 5, 3, 9, 8, 5, 8, 7};
      return new SumdokuPuzzle(groupMembership, groupValues);
   }

   /**
    * The {@code shuffleArray} method shuffles the elements of the given
    * SumdokuPuzzle array.
    * 
    * @param puzzles The array of puzzles to shuffle
    * @return a copy of the given vector with shuffled sumdokuPuzzles
    * @require {@code puzzles != null} 
    */
   private SumdokuPuzzle[] shuffleArray(SumdokuPuzzle[] puzzles) {
      SumdokuPuzzle[] shufflePuzzles = new SumdokuPuzzle[puzzles.length];
      // for every element of SumdokuPuzzle
      for (int i = 0; i < shufflePuzzles.length; i++) {
         // randomly choose wich empty space to put it in new array
         int randomPosition = random.nextInt(shufflePuzzles.length - i);
         // the number of empty spaces we passed
         int count = 0;
         // the actual index to place this element 
         int positionIndex = -1;
         // while we are not in the right empty space
         while (count <= randomPosition) {
            //go for next space
            positionIndex++;
            //if it's empty
            if(shufflePuzzles[positionIndex] == null)
               count++;
         }
         shufflePuzzles[positionIndex] = puzzles[i];

         // we don't increment count when positionIndex points to a used space
         // because we are only counting empty positions with it
      }  

      return shufflePuzzles;
   }
}

