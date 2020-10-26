package com.game;

public class GameStatus {
    private static final int TOTAL_MONKEYS = 3;
    private static final int TOTAL_WOLFS = 3;
    private static final String SIDE_A = "SIDE_A";
    private static final String SIDE_B = "SIDE_B";

    private int monkeysSideA = TOTAL_MONKEYS;
    private int monkeysSideB = 0;
    private int wolfsSideA = TOTAL_WOLFS;
    private int wolfsSideB = 0;
    private int monkeysInsideTheBoat = 0;
    private int wolfsInsideTheBoat = 0;
    private String currentSide = SIDE_A;
    private GameState state;

    public GameStatus() {
        state = GameState.RUNNING;
    }

    public String getCurrentSide() {
        return currentSide;
    }

    public int getMonkeysFromCurrentSide() {
        return isCurrentSideA() ? monkeysSideA : monkeysSideB;
    }

    private boolean isCurrentSideA() {
        return currentSide.equals(SIDE_A);
    }

    public int getWolfsFromCurrentSide() {
        return isCurrentSideA() ? wolfsSideA : wolfsSideB;
    }

    public String getMonkeyMessageToDisplayFromCurrentSide() {
        return isCurrentSideA() ? "Enter the number of monkey going from Side A to Side B" : "Enter the number of monkey going from Side B to Side A";
    }

    public String getWolfMessageToDisplayFromCurrentSide() {
        return isCurrentSideA() ? "Enter the number of wolf going from Side A to Side B" : "Enter the number of wolf going from Side B to Side A";
    }

    public void printPositionOfTheAnimals() {
        System.out.println("Side A has: " + monkeysSideA + " monkeys and " + wolfsSideA + " wolfs");
        System.out.println("Side B has: " + monkeysSideB + " monkeys and " + wolfsSideB + " wolfs");
    }

    public void updatePositions() {
        if(isCurrentSideA()){
            monkeysSideB += monkeysInsideTheBoat;
            monkeysSideA -= monkeysInsideTheBoat;
            wolfsSideB += wolfsInsideTheBoat;
            wolfsSideA -= wolfsInsideTheBoat;
            currentSide = SIDE_B;
        }else{
            monkeysSideA += monkeysInsideTheBoat;
            monkeysSideB -= monkeysInsideTheBoat;
            wolfsSideA += wolfsInsideTheBoat;
            wolfsSideB -= wolfsInsideTheBoat;
            currentSide = SIDE_A;
        }
    }

    public boolean isBoatEmpty(){
        return monkeysInsideTheBoat == 0 && wolfsInsideTheBoat == 0;
    }

    public boolean checkIfThereAreMoreThanTwoAnimalsInTheBoat() {
        return (monkeysInsideTheBoat + wolfsInsideTheBoat) > 2;
    }

    public boolean isGameOver() {
        boolean gameOver = (wolfsSideA > monkeysSideA || wolfsSideB > monkeysSideB) && (monkeysSideA != 0 && monkeysSideB != 0);
        if(gameOver) state = GameState.GAMEOVER;
        return gameOver;
    }

    public boolean isAllAnimalCrossTheRiver() {
        boolean allAnimalCrossTheRiver = monkeysSideB == 3 && wolfsSideB == 3;
        if(allAnimalCrossTheRiver) state = GameState.WIN;
        return allAnimalCrossTheRiver;
    }

    public int getMonkeysInsideTheBoat() {
        return monkeysInsideTheBoat;
    }

    public void setMonkeysInsideTheBoat(int monkeysInsideTheBoat) {
        this.monkeysInsideTheBoat = monkeysInsideTheBoat;
    }

    public void setWolfsInsideTheBoat(int wolfsInsideTheBoat) {
        this.wolfsInsideTheBoat = wolfsInsideTheBoat;
    }

    public boolean isRunning(){
        return state.equals(GameState.RUNNING);
    }

    public GameState getState() {
        return state;
    }
}