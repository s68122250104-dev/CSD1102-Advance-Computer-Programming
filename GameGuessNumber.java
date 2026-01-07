import java.util.Scanner;
import java.util.Random;

public class GameGuessNumber {
    public static void main(String[] args) {

        Scanner nsc = new Scanner(System.in);
        Random random = new Random();

        int number = random.nextInt(20) + 1;
        int max = 5;

        System.out.println("I'm thinking of a number between 1 and 20");
        System.out.println("Can you guess it is?");

        for (int i = 1; i <= max; i++) {
            System.out.print("Enter Number : ");
            int guess = nsc.nextInt();

            if (guess == number) {
                System.out.println("Correct!");
            } else if (guess < number) {
                System.out.println("Your Guess is Low. Try Again?");
            } else {
                System.out.println("Your Guess is High. Try Again?");
            }   if (i == max) {
                System.out.println("End Game!!! Answer is : " + random);
            }

            
        }

        nsc.close();
    }
}