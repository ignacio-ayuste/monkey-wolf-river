package com.game;

public enum GameState {
    RUNNING("Running"),
    WIN("Congratulations!!!!!!  You moved all monkeys and wolfs to the Side B"),
    GAMEOVER("Game Over!!!  The no of wolfs cannot be greater than the monkeys on either side !!");

    private final String message;

    GameState(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}