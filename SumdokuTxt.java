import java.util.Scanner;

/**
 * The {@code SumdokuTxt} class is a textual implementation of the videogame Sumdoku.
 *
 * @author rodrigostamartacoelho
 */
public class SumdokuTxt {

   public static void main(String[] args) {
      if (args.length == 0) {
         // No grid size provided in the arguments
         System.out.println("Por favor, insira o tamanho da grelha ao executar o programa.");
      }
      else{
         Scanner scanner = new Scanner(System.in);
         int gridNumber = Integer.parseInt(args[0]); // Parse grid size from command-line argument
         int totalSquares = gridNumber * gridNumber; // Calculate total squares in the grid

         if (gridNumber >= 3 && gridNumber <= 9) { // Validate grid size is supported
               RandomSumdokuPuzzle puzzleGenerator = new RandomSumdokuPuzzle(gridNumber); // Initialize puzzle generator

               if (!puzzleGenerator.hasNextPuzzle()) {
                  // No more puzzles available for the given grid size
                  System.out.println("Não existem mais puzzles de tamanho " + gridNumber + " para jogar.");
               } else {
                  boolean playAgain = true;
                  System.out.println("Bem vindo ao jogo Sumdoku!");
                  while (playAgain && puzzleGenerator.hasNextPuzzle()) {
                     SumdokuPuzzle puzzle = puzzleGenerator.nextPuzzle(); // Fetch the next puzzle
                     System.out.println("As pistas do puzzle:");
                     System.out.println(puzzle.cluesToString()); // Display puzzle clues
                     System.out.println("Tens " + totalSquares + " tentativas para o resolver. Boa sorte!");

                     play(puzzle, totalSquares, scanner); // Start the game with the current puzzle

                     System.out.print("Queres tentar resolver um novo puzzle (true/false)? ");
                     String playerChoice = scanner.next();
                     playAgain = playerChoice.equals("true"); // Continue if the user says "true"
                  }
                  System.out.println("Espero que tenhas gostado. Volta sempre!"); // End of the game
               }
         } else {
            // Invalid grid size input
            System.out.println("Tamanho inválido ou não suportado. Introduza um valor entre 3 e 9 que seja suportado.");
         }
         scanner.close(); // Close the scanner
      }
   }

   /**
   * The play method manages the gameplay for a single Sumdoku puzzle.
   * It allows the user to fill the grid and checks for the solution.
   *
   * @param puzzle      The Sumdoku puzzle to be solved.
   * @param maxAttempts The maximum number of attempts allowed for solving the puzzle.
   * @param scanner     The Scanner object for reading user input.
   * @require {@code puzzle != null && scanner != null && maxAttempts}
   */
   public static void play(SumdokuPuzzle puzzle, int maxAttempts, Scanner scanner) {
      SumdokuGrid grid = new SumdokuGrid(puzzle.size()); // Create a new empty grid of the appropriate size
      int attempts = 0; // Initialize the number of attempts
      boolean solved = false; // Track whether the puzzle is solved

      while (attempts < maxAttempts && !solved) {
         int position; // Grid position to fill
         int value; // Value to place in the grid

         // Get and validate the position from the user
         do {
            System.out.print("Casa a preencher? ");
            while (!scanner.hasNextInt()) {
               // Handle invalid input for position
               System.out.println("Entrada inválida. Insira um número. ");
               scanner.next(); // Clear invalid input
            }
            position = scanner.nextInt();
            if (position < 1 || position > puzzle.size() * puzzle.size()) {
               // Ensure position is within the valid range
               System.out.println("Posição inválida. Selecione uma posição entre 1 e " + puzzle.size() * puzzle.size());
            }
         } while (position < 1 || position > puzzle.size() * puzzle.size());

         // Convert position to row and column indices
         int row = (position - 1) / puzzle.size() + 1;
         int col = (position - 1) % puzzle.size() + 1;

         // Get and validate the value to fill in the grid
         do {
            System.out.print("Valor a preencher? ");
            while (!scanner.hasNextInt()) {
               // Handle invalid input for value
               System.out.print("Entrada inválida. Insira um número. ");
               scanner.next(); // Clear invalid input
            }
            value = scanner.nextInt(); // Read the value
            if (value < 1 || value > puzzle.size()) {
               // Ensure value is within the valid range
              System.out.println("Valor inválido. Selecione um valor entre 1 e " + puzzle.size());
            }
         } while (value < 1 || value > puzzle.size());

         // Fill the grid at the specified position with the given value
         grid.fill(row, col, value);
         System.out.println(grid); // Display the updated grid

         attempts++; // Increment the number of attempts

         if (puzzle.isSolvedBy(grid)) {
            // Check if the puzzle is solved
            System.out.println("Parabéns! Você resolveu o puzzle!");
            solved = true; // Mark as solved
         } //else if (!puzzle.isPartiallySolvedBy(grid)) {
            //System.out.println("Valor incorreto! Tente novamente.");
            //grid.fill(row,col,0);
            //System.out.println(grid);
         //}
      }

      if (!solved) {
         // Notify the user if they run out of attempts
         System.out.println("Ops, tentativas esgotadas!");
      }
   }
}
