/**
 * File: RPS.java
 * Name: Rizq Khateeb
 * ID: A15848068
 * Email: rkhateeb@ucsd.edu
 * Sources used: None
 * 
 * File consisting of an concrete class that implements RPSAbstract, 
 * and plays the game and determines a winner for each round.
 */

import java.util.Scanner;

/**
 * class that extends abstract class RPSAbstract, and uses defined variables and methods from 
 * that class to play an RPS game and determine the winner
 * 
 */
public class RPS extends RPSAbstract {
    
    /**
     * Constructor for RPS class
     * @param moves - array of allowed strings for moves
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove){
        // if move is not valid, return output for invalid output 
        if (this.isValidMove(playerMove)==false || this.isValidMove(cpuMove)==false){
            return INVALID_INPUT_OUTCOME;
        }
        int pMove = -1;
        int cMove = -1;
        //find cpu move index
        for(int i = 0; i < possibleMoves.length; i++){
           if (cpuMove.equals(possibleMoves[i])){
               cMove = i;
               break;
           }
        }
        //find player move index
        for(int i = 0; i < possibleMoves.length; i++){
            if (playerMove.equals(possibleMoves[i])){
                pMove = i;
                break;
            }
         }
        
        //Based on index position of cpuMove and playerMove, return winner
        if ((pMove == cMove + 1) || 
            (cMove == possibleMoves.length-1 && pMove == 0)){
            return CPU_WIN_OUTCOME;
        }
        else if ((cMove == pMove + 1) ||
                (pMove == possibleMoves.length-1 && cMove == 0)){
            return PLAYER_WIN_OUTCOME;
        }
        //return tie for all other conditions
        else {
            return TIE_OUTCOME;
        }
    }


    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);
        String a = "";
                
        // While user does not input "q", play game
        while(!a.equals(QUIT)) {
            // prompt user to enter their move
            System.out.println(PROMPT_MOVE);
            a= in.nextLine();

            // end game when user quits            
            if(a.equals(QUIT)){
                game.end();
                break;
            }
            // play game if input valid
            else{
                game.play(a, game.genCPUMove());
            }
        }
        // close scanner
        in.close();
    }
}
