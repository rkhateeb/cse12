/**
 * File: RPSAbstract.java
 * Name: Rizq Khateeb
 * ID: A15848068
 * Email: rkhateeb@ucsd.edu
 * Sources used: None
 * 
 * File consisting of an abstract class that implements RPSInterface, 
 * and methods that set up the game.
 */

import java.util.Random;

/**
 * Abstract class that implements RPSInterface, contains all 
 * variables needed for the game, along with methods to determine
 * if a move is valid, to play the game, to generate a cpu 
 * move, and to end the game
 */
public abstract class RPSAbstract implements RPSInterface {
    public static final int SEED = 12;
    Random rand = new Random(SEED);
    
    // The moves allowed in this version of RPS
    public String [] possibleMoves;
    
    // The number of games, player wins, CPU wins and ties
    public int numGames;
    public int numPlayerWins;
    public int numCPUWins;
    public int numTies;
    
    // The complete history of the moves
    String[] playerMoves;
    String[] cpuMoves;

    // The default moves.  Use for the basic implementation of the game.
    public static final String[] DEFAULT_MOVES = {"scissors", "paper", "rock"};
    //public static final String[] DEFAULT_MOVES = {"water", "fire", "ice", "ground", "electric"};

    // Messages for the game.  USE THESE!
    public static final String INVALID_INPUT =
    "That is not a valid move. Please try again.";
    public static final String PLAYER_WIN = "You win.";
    public static final String CPU_WIN = "I win.";
    public static final String TIE = "It's a tie.";
    public static final String CPU_MOVE = "I chose %s. ";
    public static final String THANKS = 
        "Thanks for playing!\nOur most recent games were: ";
    public static final String PROMPT_MOVE = 
        "Let's play! What's your move? (Type the move or q to quit)";
    
    public static final String OVERALL_STATS =
        "Our overall stats are:\n" + 
        "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    public static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";


    // Game outcome constants.  USE THESE!
    public static final int CPU_WIN_OUTCOME = 2;
    public static final int PLAYER_WIN_OUTCOME = 1;
    public static final int TIE_OUTCOME = 0;
    public static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.  Use as appropriate.
    public static final int MAX_GAMES = 100;
    public static final int MIN_POSSIBLE_MOVES = 3;
    public static final int NUM_ROUNDS_TO_DISPLAY = 10;
    public static final int PERCENTAGE_RESIZE = 100;
    public static final String QUIT = "q";
    

     /**
     * Determine if the move is valid
     * @param move The move
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(String move) {
        // if input is null, return false
        if(move==null){
            return false;
        }
        // for each value in the array of possibleMoves
        for(int i=0; i<possibleMoves.length; i++){
            // if player move matches an entry in possibleMoves, return true.
            if (move.equals(possibleMoves[i])){
                return true;
            }
        }
        return false;  // dummy return value so code compiles.
    }
    
     /**
     * Play one game of RPS.
     * Also adds appropriately to the number of games, wins and ties played.
     * and records the most recent moves.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     */
    public void play(String playerMove, String cpuMove) {
       // Use determineWinner to determine who won
       int winner = this.determineWinner(playerMove, cpuMove);
       // Record the moves made in correct arrays, indexed by numGames
       playerMoves[numGames] = playerMove;
       cpuMoves[numGames] = cpuMove;
       // Add one to the appropriate statistics
       // If invalid input, do not add one to any statistics 
        if (winner == -1){
            System.out.println(INVALID_INPUT);
        }
        // If tie, add one to tie counter 
        else if (winner == 0){
            numGames++;
            numTies++;
            System.out.printf(CPU_MOVE, cpuMove);
            System.out.println(TIE);
        }
        // If player wins, add one to player win counter
        else if (winner == 1){
            numGames++;
            numPlayerWins++;
            System.out.printf(CPU_MOVE, cpuMove);
            System.out.println(PLAYER_WIN);
        }
        // If cpu wins, add one to cpu win counter
        else if (winner == 2){
            numGames++;
            numCPUWins++;
            System.out.printf(CPU_MOVE, cpuMove);
            System.out.println(CPU_WIN);
        }
    }  
    

    // NOTE: You do not need to modify the methods below

    /**
     * Generates next cpu move
     * @return String representing the move, depending on random int
     */
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    public void end() {
        float cpuWinPercent = (float)numCPUWins/
            (float)numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float)numPlayerWins/
            (float)numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float)numTies/
            (float)numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (numGames < NUM_ROUNDS_TO_DISPLAY) 
        ? 0 : numGames - NUM_ROUNDS_TO_DISPLAY;
            
        // Print system and user moves
        for (int i = numGames - 1 ; i >= printTo; i--) {
            System.out.printf(CPU_PLAYER_MOVES, this.cpuMoves[i],
                this.playerMoves[i]);   
        }

        System.out.printf(OVERALL_STATS, cpuWinPercent, playerWinPercent,
            tiedPercent);
    }
}
