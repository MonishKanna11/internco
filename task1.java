import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int rounds = 1;
        int totalScore = 0;
        String playAgain = "y";

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain.equalsIgnoreCase("y")) {
            System.out.println("\nRound " + rounds + ":");
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10;
            boolean correctGuess = false;

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + "/" + maxAttempts + " - Guess the number (between 1 and 100): ");
                try {
                    int guess = Integer.parseInt(scanner.nextLine());
                    attempts++;
                    if (guess < targetNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > targetNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the correct number.");
                        correctGuess = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + targetNumber + ".");
            }

            // Scoring based on the number of attempts taken
            int score = Math.max(0, maxAttempts - attempts);
            totalScore += score;
            System.out.println("Your score for this round: " + score);
            System.out.println("Total score: " + totalScore);

            System.out.print("Do you want to play another round? (y/n): ");
            playAgain = scanner.nextLine();
            rounds++;
        }

        System.out.println("\nGame over! You played " + (rounds - 1) + " rounds with a total score of " + totalScore + ".");
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}