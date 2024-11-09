import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuizGame {

    // Class to represent a quiz question
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    // Method to start the quiz game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Store quiz questions, options, and correct answers
        Question[] questions = {
            new Question("What is the capital of France?", new String[]{"1. Berlin", "2. London", "3. Paris", "4. Madrid"}, 3),
            new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Venus", "3. Mars", "4. Jupiter"}, 3),
            new Question("Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. J.K. Rowling"}, 2)
        };

        int score = 0;
        int questionIndex = 0;
        int totalQuestions = questions.length;
        int[] userAnswers = new int[totalQuestions];
        boolean[] isCorrect = new boolean[totalQuestions];
        AtomicBoolean answerSubmitted = new AtomicBoolean(false);

        // Loop through all the questions
        for (Question question : questions) {
            System.out.println("\nQuestion " + (questionIndex + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            // Timer for each question (10 seconds)
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answerSubmitted.get()) {
                        System.out.println("\nTime's up! Moving to the next question.");
                        answerSubmitted.set(true);
                    }
                }
            }, 10000); // 10 seconds timer

            System.out.print("Please select an option (1-4): ");
            while (!answerSubmitted.get()) {
                try {
                    int answer = scanner.nextInt();
                    if (answer >= 1 && answer <= 4) {
                        userAnswers[questionIndex] = answer;
                        isCorrect[questionIndex] = (answer == question.correctAnswerIndex);
                        if (isCorrect[questionIndex]) {
                            score++;
                        }
                        answerSubmitted.set(true);
                    } else {
                        System.out.println("Invalid option. Please select a valid option (1-4): ");
                    }
                } catch (Exception e) {
                    scanner.next(); // clear invalid input
                    System.out.println("Please enter a number between 1 and 4.");
                }
            }

            timer.cancel(); // stop the timer once answer is submitted
            answerSubmitted.set(false); // reset for the next question
            questionIndex++;
        }

        // Show the result screen
        System.out.println("\nQuiz Complete! Here's your result:");
        System.out.println("Total Score: " + score + "/" + totalQuestions);
        for (int i = 0; i < totalQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i].questionText);
            System.out.println("Your answer: " + questions[i].options[userAnswers[i] - 1]);
            System.out.println("Correct answer: " + questions[i].options[questions[i].correctAnswerIndex - 1]);
            if (isCorrect[i]) {
                System.out.println("Result: Correct");
            } else {
                System.out.println("Result: Incorrect");
            }
        }

        scanner.close();
    }
}