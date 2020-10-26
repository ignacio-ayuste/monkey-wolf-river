package com.game;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MonkeyWolfGameTest {

    private static final String WINNER_TEST = "1 1 1 0 0 2 1 2 1 1 2 0 1 2 0 1 2";
    private static final String GAMEOVER_TEST = "2";

    @Test
    public void GIVEN_validMovementsToWin_WHEN_play_THEN_returnsOutcomeWin(){
        Scanner scanner = getScanner(new ByteArrayInputStream(WINNER_TEST.getBytes()));
        MonkeyWolfGame monkeyWolfGame = new MonkeyWolfGame();
        GameState outcome = monkeyWolfGame.play(scanner);
        assertThat(outcome).isEqualTo(GameState.WIN);
    }

    @Test
    public void GIVEN_moreAnimalsOnOneSide_WHEN_play_THEN_returnsOutcomeGameOver(){
        Scanner scanner = getScanner(new ByteArrayInputStream(GAMEOVER_TEST.getBytes()));
        MonkeyWolfGame monkeyWolfGame = new MonkeyWolfGame();
        GameState outcome = monkeyWolfGame.play(scanner);
        assertThat(outcome).isEqualTo(GameState.GAMEOVER);
    }

    private Scanner getScanner(InputStream inputStream){
        System.setIn(inputStream);
        return new Scanner(System.in);
    }
}