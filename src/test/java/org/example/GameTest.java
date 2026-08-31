package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    public static final int OPTION_ROCK = 0;
    public static final int OPTION_PAPER = 1;
    public static final int OPTION_SCISSORS = 2;

    @InjectMocks
    private Game game;

    @Mock
    private Random random;

    private ByteArrayOutputStream out;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        // Re-instanciar para que lea el nuevo System.in
        game = new Game();
        // Inyectar manualmente el mock de Random
        try {
            java.lang.reflect.Field field = Game.class.getDeclaredField("random");
            field.setAccessible(true);
            field.set(game, random);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void when_writeQuit_then_exitGame() {
        setInput("Quit\n");
        game.play();

        Assert.assertTrue(out.toString().contains("Let's play Rock"));
    }

    @Test
    public void when_chooseRock_then_beatsScissors() {
        setInput("Rock\nQuit\n");
        when(random.nextInt(3)).thenReturn(OPTION_SCISSORS);

        game.play();

        Assert.assertTrue(out.toString().contains("Computer chose scissors"));
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void when_chooseScissors_then_beatsPaper() {
        setInput("Scissors\nQuit\n");
        when(random.nextInt(3)).thenReturn(OPTION_PAPER);

        game.play();

        Assert.assertTrue(out.toString().contains("Computer chose paper"));
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void when_choosePaper_then_beatsRock() {
        setInput("Paper\nQuit\n");
        when(random.nextInt(3)).thenReturn(OPTION_ROCK);

        game.play();

        Assert.assertTrue(out.toString().contains("Computer chose rock"));
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void when_bothChooseRock_then_tie() {
        setInput("Rock\nQuit\n");
        when(random.nextInt(3)).thenReturn(OPTION_ROCK);

        game.play();

        Assert.assertTrue(out.toString().contains("Computer chose rock"));
        Assert.assertTrue(out.toString().contains("ties:1"));
    }

    @Test
    public void when_chooseRockAndComputerChoosePaper_then_loose() {
        setInput("Rock\nQuit\n");
        when(random.nextInt(3)).thenReturn(OPTION_PAPER);

        game.play();

        Assert.assertTrue(out.toString().contains("Computer chose paper"));
        Assert.assertTrue(out.toString().contains("loses:1"));
    }
}