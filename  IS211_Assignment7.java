package test;
import java.util.Random;
import java.util.Scanner;

public class GameOfPig {
    public static void main(String[] args) {
        int your_score = 0, computer_score = 0;
        while(your_score < 100 && computer_score < 100) {
            your_score = usersTurn(your_score);
            System.out.println("\nYour total score is: " + your_score);
            if(your_score >= 100) {
                System.out.println("YOU WON!");
                break;
            }

            System.out.println();
            computer_score = computersTurn(computer_score);
            System.out.println("\nComputer's total score is: " + computer_score);
            if(computer_score >= 100) {
                System.out.println("THE COMPUTER WON!");
                break;
            }
        }
    }

    static int usersTurn(int oldTotal) {
        Scanner in = new Scanner(System.in);
        String move = "r";
        int turn_score = 0;

        while(move.equals("r")) {
            int rolled = rollDie();
            System.out.println("\nYou rolled: " + rolled);
            if(rolled == 1) {
                System.out.println("\nYou lose your turn. Total score is " + oldTotal);
                move = "";
                continue;
            }

            turn_score += rolled;

            if(turn_score + oldTotal >= 100) {
                return turn_score + oldTotal;
            }

            System.out.println("Your turn score is: " + turn_score + " and your total score is: " + oldTotal);

            System.out.println("If you hold, you will have " + (turn_score + oldTotal) + " points.");
            System.out.print("Enter 'r' to roll again, 'h' to hold: ");
            move = in.nextLine();

            if(move.equals("h")) {
                oldTotal += turn_score;
            }
        }

        return oldTotal;
    }

    static int computersTurn(int oldTotal) {
        String move = "r";
        int turn_score = 0;

        while(move.equals("r")) {
            int rolled = rollDie();
            System.out.println("The computer rolled: " + rolled);
            if(rolled == 1) {
                System.out.println("The computer lost its turn. Total score is " + oldTotal);
                move = "";
                continue;
            }

            turn_score += rolled;

            if(turn_score + oldTotal >= 100) {
                return turn_score + oldTotal;
            }

            if(turn_score >= 20) {
                System.out.println("The computer holds.");
                move = "h";
                oldTotal += turn_score;
            }
        }

        return oldTotal;
    }

    static int rollDie() {
        Random rand = new Random();
        return rand.nextInt(6 - 1) + 1;
    }
}
