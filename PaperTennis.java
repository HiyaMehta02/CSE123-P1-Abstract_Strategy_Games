// Hiya Mehta
// 02/03/2025
// CSE 123
// TA : Hayden Feeney

import java.util.*;

// This class allows the to play three rounds of the game PaperTennis.
// It allows the user to get the instructions for the game, print the 
// game board, check if the game is over, get the winner of the game, 
// and make a move
public class PaperTennis extends AbstractStrategyGame {
    private int[] gameBoard;
    private int ballIndex;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private int round;
    private static final int TOTAL_ROUNDS = 3;
    private static final int BOARD_LENGTH = 6;
    

    // this is a constructor
    // Behavior :
    //     - Creates a new PaperTennis game
    // Parameters : None 
    // Returns : None
    // Exceptions : None
    public PaperTennis() {
        gameSetUp();
        this.player1 = new Player(1);
        this.player2 = new Player(2); 
        this.currentPlayer = player1;
        this.round = 0;      
    }

    // Behavior : 
    //    - returns the instructions for the game
    // Parameters : 
    //    - none
    // Returns : 
    //    - String : the instructions for the game
    // Exceptions : 
    //    - none
    public String instructions() {
        return "Each player starts with $50 (Player 1 on the left and Player 2 on the right).\n" + 
        "For each move, both players secretly decide how much of their remaining money to spend,\n"+ 
        "then reveal their bids simultaneously. The player who bids the higher number moves\n" +
        "the ball one column toward the opponent's side, and both players subtract their chosen\n" + 
        "bid from their total money. The game continues until the ball is knocked out of the\n" +
        "court or one or both players run out of money, and the player on whose side the ball\n" + 
        "ends up loses the game.";
    }

    // Behavior : 
    //    - returns the game board
    // Parameters : 
    //    - none
    // Returns : 
    //    - String: the current game board
    // Exceptions : 
    //    - none
    public String toString() {
        String arr = "Game Board:\t";
        for (int i = 0; i < BOARD_LENGTH; i++) {
            if (i != 0 && i != 6) {
                arr+=gameBoard[i];
            }
        }
        return "\n" + arr + "\n";
    }

    // Behavior : 
    //    - checks if the game is over or not
    // Parameters : 
    //    - none
    // Returns : 
    //    - returns true if the game is over
    //    - returns false if the game is not over
    // Exceptions : 
    //    - none
    public boolean isGameOver() {
        return round == TOTAL_ROUNDS;
    }

    // Behavior : 
    //    - gets the winner of the game
    // Parameters : 
    //    - none
    // Returns : 
    //    - returns 1 if player 1 won
    //    - returns 2 if player 2 won    
    //    - returns -1 if there is no winner   
    // Exceptions : 
    //    - none
    public int getWinner() {
        if (player1.getPoints() > player2.getPoints()) {
            return 1;
        } else if (player1.getPoints() < player2.getPoints()) {
            return 2;
        } else {
            return -1;
        }
    }

    // Behavior : 
    //    - tells you which players turn is next
    // Parameters : 
    //    - none
    // Returns : 
    //    - returns 1 if it is player 1's turn
    //    - returns 2 if it is player 2's turn
    //    - returns -1 if the game is over   
    // Exceptions : 
    //    - none
    public int getNextPlayer() {
        if (isGameOver() == false) {
            return currentPlayer.getPlayerNum();
        } else {
            return -1;
        }
    }

    // Behavior : 
    //    - tells the player know how much money they have
    //    - asks and gets the amount of money the player wants to bet
    // Parameters : 
    //    - input (Scanner) : a scanner that allows the method to access the console
    // Returns : 
    //    - none   
    // Exceptions : 
    //    - throws IllegalArgumentException if the bet is less than 0 
    //    - throws IllegalArgumentException if the bet is more than 
    //      the amount of money the player already has
    public void makeMove(Scanner input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        System.out.println("\t\tYou have $" + currentPlayer.getMoney() + " left\n");
        System.out.println("How much money do you want to bet?");
        int bet = input.nextInt();
        if (currentPlayer.getMoney() <= bet && bet < 0) {
            throw new IllegalArgumentException();
        }
        currentPlayer.setBet(bet);
        if (currentPlayer.getPlayerNum() == 1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
            gameMaintainer();
        }
    }

    // Behavior : 
    //    - tells you who the winner one round of the game is
    // Parameters : 
    //    - none
    // Returns : 
    //    - Player : returns the player that won that round 
    //               null if no one has won  
    // Exceptions : 
    //    - none 
    private Player miniGameWinner() {
        if (player1.getMoney() == 0 && player2.getMoney() == 0) {
            return new Player(0);
        }
         if (ballIndex == 0) {
            return player2;
        } else if (ballIndex == 6) {
            return player1;
        } else {
            return null;
        }
    }

    // Behavior : 
    //    - tells you who bet more money
    // Parameters : 
    //    - none
    // Returns : 
    //    - Player : returns the player who bet more money   
    // Exceptions : 
    //    - none 
    private Player roundWinner() {
        if (player1.getBet() > player2.getBet()) {
            return player1;
        } else if (player1.getBet() < player2.getBet()) {
            return player2;
        } else {
            return null;
        }
    }

    // Behavior : 
    //    - this method is in charge of updating the gameboard and updating 
    //      the current round of the game the players are currently on
    // Parameters : 
    //    - none
    // Returns : 
    //    - none   
    // Exceptions : 
    //    - none 
    private void gameMaintainer() {
        if (roundWinner() != null) {
            System.out.println("\tPlayer " + roundWinner().getPlayerNum() + " bet more money!");
            gameBoard[ballIndex] = 0;
            if (roundWinner() == player1) {
                ballIndex = ballIndex + 1;
                if (ballIndex == BOARD_LENGTH/2) {
                    ballIndex++;
                }
            } else if (roundWinner() == player2) {
                ballIndex = ballIndex - 1;
                if (ballIndex == BOARD_LENGTH/2) {
                    ballIndex--;
                }
            }
        } else {
            System.out.println("\tBoth players tied this round.");
        }
        System.out.println("ballindex: " + ballIndex);
        if (miniGameWinner() != null) {
            if (miniGameWinner().getPlayerNum() != 0) {
                miniGameWinner().addPoint();
                if (ballIndex == 0 || ballIndex == 6) {
                    miniGameWinner().addPoint();
                }
                round++;
            }
            gameReset();
        } else {
            gameBoard[ballIndex] = 5;
        }
    }

    // Behavior : 
    //    - resets the game for the next round after one round is over
    // Parameters : 
    //    - none
    // Returns : 
    //    - none   
    // Exceptions : 
    //    - none 
    private void gameReset() {
        System.out.println("\nTHE WINNER OF THIS ROUND IS PLAYER" +miniGameWinner().getPlayerNum());
        if (round != 3) { 
            System.out.println("\n\n\nThis is the start of the new game.");
            gameSetUp();
            player1.reset();
            player2.reset();
        }
    }

    // Behavior : 
    //    - sets/resets the game board 
    // Parameters : 
    //    - none
    // Returns : 
    //    - none   
    // Exceptions : 
    //    - none 
    private void gameSetUp() {
        this.gameBoard = new int[7];
        this.gameBoard[BOARD_LENGTH/2] = 5;
        this.ballIndex = BOARD_LENGTH/2;
    }
}
