package org.example;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);
    private Random random = new Random();

    // -------------------------------------------------------------
    // Clases internas agregadas para resolver el "cannot find symbol"
    // -------------------------------------------------------------
    public enum GameOption {
        ROCK,
        PAPER,
        SCISSORS
    }

    public static class ScoreBoard {
        private int wins = 0;
        private int losses = 0;
        private int ties = 0;

        public void incrementWins() {
            this.wins++;
        }

        public void incrementLosses() {
            this.losses++;
        }

        public void incrementTies() {
            this.ties++;
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public int getTies() {
            return ties;
        }
    }

    // -------------------------------------------------------------
    // Lógica principal del juego
    // -------------------------------------------------------------
    public void play() {
        printGameRules();

        ScoreBoard scoreBoard = new ScoreBoard();
        String choice = input.nextLine().toUpperCase();

        while (!choice.equals("QUIT")) {
            GameOption choicenum = getChoiceNum(choice);
            while (choicenum == null) {
                System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
                choice = input.nextLine().toUpperCase();
                choicenum = getChoiceNum(choice);
            }

            GameOption compnum = getComputerChoice();
            completeGamePlay(scoreBoard, choicenum, compnum);
            printResults(scoreBoard);

            choice = input.nextLine().toUpperCase();
        }
    }

    private void printResults(ScoreBoard scoreBoard) {
        // Se corrigió getTie() a getTies()
        System.out.println("wins:" + scoreBoard.getWins() + "\nloses:" + scoreBoard.getLosses() + "\nties:" + scoreBoard.getTies());
        System.out.println("Let's play again! \n \n");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
    }

    private void completeGamePlay(ScoreBoard scoreBoard, GameOption choicenum, GameOption compnum) {
        if (choicenum == compnum) {
            tie(scoreBoard);
        } else if (choicenum == GameOption.ROCK && compnum == GameOption.SCISSORS
                || choicenum == GameOption.SCISSORS && compnum == GameOption.PAPER
                || (choicenum == GameOption.PAPER && compnum == GameOption.ROCK)) {
            wins(scoreBoard);
        } else {
            lose(scoreBoard);
        }
    }

    private void lose(ScoreBoard scoreBoard) {
        System.out.println("you lose.");
        scoreBoard.incrementLosses();
    }

    private void tie(ScoreBoard scoreBoard) {
        System.out.println("It's a tie");
        // Se corrigió incrementTie() a incrementTies()
        scoreBoard.incrementTies();
    }

    private void wins(ScoreBoard scoreBoard) {
        System.out.println("you win!");
        scoreBoard.incrementWins();
    }

    private GameOption getChoiceNum(String choice) {
        GameOption selectedOption = null;

        if (choice.equals("QUIT"))
            System.exit(0);

        try {
            selectedOption = GameOption.valueOf(choice);
        } catch (Exception e) {
            return null;
        }
        return selectedOption;
    }

    private GameOption getComputerChoice() {
        GameOption option = GameOption.values()[random.nextInt(3)];
        System.out.println("Computer chose " + option.toString().toLowerCase());
        return option;
    }

    private void printGameRules() {
        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
    }
}