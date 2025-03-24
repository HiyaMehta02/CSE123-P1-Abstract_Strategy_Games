// Hiya Mehta
// 02/03/2025
// CSE 123
// TA : Hayden Feeney

import java.util.*;

// This class represents a player who is player PaperTennis.
// It helps keep track of the amount of money the player bet,
// the money the player has, and the points that the player has
// obtained. It allows the user to set the bet, check if they 
// have money left, and reset their back
public class Player {
    private int PlayerBet;
    private int PlayerMoney;
    private int PlayerNum;
    private int points;

    // this is a constructor
    // Behavior :
    //     - Creates a new Player and initializes all the variables
    // Parameters : 
    //     - PlayerNum (int) : the number accociated with the player 
    // Returns : None
    // Exceptions : None
    public Player(int PlayerNum) {
        this.PlayerMoney = 50;
        this.PlayerNum = PlayerNum;
        this.PlayerBet = 0;
        this.points = 0;
    }

    // Behavior : 
    //    - sets the players bet
    // Parameters : 
    //    - bet (int) : the amount of money that player wants to bet
    // Returns : 
    //    - none
    // Exceptions : 
    //    - throws IllegalArgumentException if the bet is less than 0 
    //    - throws IllegalArgumentException if the bet is more than 
    //      the amount of money the player already has 
    public void setBet(int bet) {
        if (PlayerMoney >= bet && bet >= 0) {
            PlayerBet = bet;
            PlayerMoney = PlayerMoney - bet;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // Behavior : 
    //    - tells you if the player has any money left
    // Parameters : 
    //    - none
    // Returns : 
    //    - int : returns -1 if the player has no money and returns 0 if they do
    // Exceptions : 
    //    - none 
    public int noMoneyLeft() {
        if (PlayerMoney == 0) {
            return -1;
        } else {
            return 0;
        }
    }

    // Behavior : 
    //    - returns the amount of money the user bet
    // Parameters : 
    //    - none
    // Returns : 
    //    - int : the amount of money the user bet
    // Exceptions : 
    //    - none 
    public int getBet() {
        return PlayerBet;
    }

    // Behavior : 
    //    - returns the amount of money the user has
    // Parameters : 
    //    - none
    // Returns : 
    //    - int : the amount of money the user has
    // Exceptions : 
    //    - none 
    public int getMoney() {
        return PlayerMoney;
    }

    // Behavior : 
    //    - returns the number associated with the player
    // Parameters : 
    //    - none
    // Returns : 
    //    - int : the number associated with the player
    // Exceptions : 
    //    - none 
    public int getPlayerNum() {
        return PlayerNum;
    }

    // Behavior : 
    //    - returns the number of points the user has
    // Parameters : 
    //    - none
    // Returns : 
    //    - int : the number of points the user has
    // Exceptions : 
    //    - none
    public int getPoints() {
        return points;
    }

    // Behavior : 
    //    - increases the amount of points the player has
    // Parameters : 
    //    - none
    // Returns : 
    //    - none
    // Exceptions : 
    //    - none
    public void addPoint() {
        points++;
    }

    // Behavior : 
    //    - resets the players money for the start of a new game
    // Parameters : 
    //    - none
    // Returns : 
    //    - none
    // Exceptions : 
    //    - none
    public void reset() {
        this.PlayerMoney = 50;
    }
}
