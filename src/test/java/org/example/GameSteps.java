package org.example;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;

import static org.mockito.Mockito.when;

public class GameSteps {

    public static final int OPTION_ROCK = 0;
    public static final int OPTION_PAPER = 1;
    public static final int OPTION_SCISSORS = 2;

    private Game game;

    @Mock
    private Scanner scanner;

    @Mock
    private Random random;

    private ByteArrayOutputStream out;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        game = new Game();
        try {
            Field scannerField = Game.class.getDeclaredField("scanner");
            scannerField.setAccessible(true);
            scannerField.set(game, scanner);

            Field randomField = Game.class.getDeclaredField("random");
            randomField.setAccessible(true);
            randomField.set(game, random);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the user will choose {string}")
    public void theUserWillChoose(String userSelection) {
        when(scanner.nextLine()).thenReturn(userSelection).thenReturn("Quit");
    }

    @And("the computer will choose {string}")
    public void theComputerWillChoose(String computerSelection) {
        int selection = 0;
        String choice = computerSelection.toLowerCase();

        if ("scissors".equals(choice)) {
            selection = OPTION_SCISSORS;
        } else if ("rock".equals(choice)) {
            selection = OPTION_ROCK;
        } else if ("paper".equals(choice)) {
            selection = OPTION_PAPER;
        }

        when(random.nextInt(3)).thenReturn(selection);
    }

    @When("they play")
    public void theyPlay() {
        game.play();
    }

    @Then("the user wins")
    public void theUserWins() {
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @And("the user lose")
    public void theUserLose() {
        Assert.assertTrue(out.toString().contains("loses:1"));
    }

    @And("the user tie")
    public void theUserTie() {
        Assert.assertTrue(out.toString().contains("ties:1"));
    }

    @Then("verify that the computer chose {string}")
    public void verifyThatTheComputerChose(String computerSelection) {
        Assert.assertTrue(out.toString().contains("Computer chose " + computerSelection));
    }
}