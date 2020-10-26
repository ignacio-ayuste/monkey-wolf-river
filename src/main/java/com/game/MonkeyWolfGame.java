package com.game;

import java.util.Scanner;

class MonkeyWolfGame {

    public static final String THE_BOAT_CAN_CARRY_ONLY_2_ANIMALS = "The boat can carry only 2 animals";
    private static final String BOAT_EMPTY_MESSAGE = "The boat is not magic someone has to take it from side to side";
    private static final String PLEASE_SELECT_A_NUMBER_BETWEEN_0_AND_2 = "Please select a number between 0 and 2";
    private static final String THERE_IS_ONLY_ONE_MONKEY_ON_THIS_SIDE = "There is only one monkey on this side";
    private static final String THERE_IS_ONLY_ONE_WOLF_ON_THIS_SIDE = "There is only one wolf on this side";
    private static final int MAX_ANIMAL_ALLOWED_IN_THE_BOAT = 2;
    private GameStatus gameStatus;
    private Scanner scanner;

    public void play() {
        this.scanner = new Scanner(System.in);
        play(scanner);
    }

    private void moveAnimalsToOtherSide() {
        final int monkeysInsideTheBoat = moveAnimalIfPossible(
                gameStatus.getMonkeysFromCurrentSide(),
                0,
                gameStatus.getMonkeyMessageToDisplayFromCurrentSide(),
                THERE_IS_ONLY_ONE_MONKEY_ON_THIS_SIDE);
        gameStatus.setMonkeysInsideTheBoat(monkeysInsideTheBoat);
        final int wolfsInsideTheBoat = moveAnimalIfPossible(
                gameStatus.getWolfsFromCurrentSide(),
                gameStatus.getMonkeysInsideTheBoat(),
                gameStatus.getWolfMessageToDisplayFromCurrentSide(),
                THERE_IS_ONLY_ONE_WOLF_ON_THIS_SIDE);
        gameStatus.setWolfsInsideTheBoat(wolfsInsideTheBoat);

        if (gameStatus.checkIfThereAreMoreThanTwoAnimalsInTheBoat()) {
            System.err.println(THE_BOAT_CAN_CARRY_ONLY_2_ANIMALS);
            moveAnimalsToOtherSide();
            return;
        }
        if (gameStatus.isBoatEmpty()) {
            System.err.println(BOAT_EMPTY_MESSAGE);
            moveAnimalsToOtherSide();
            return;
        }
        gameStatus.updatePositions();

        if (gameStatus.isGameOver()) {
            System.err.println(GameState.GAMEOVER);
            return;
        }
        if (gameStatus.isAllAnimalCrossTheRiver()) {
            System.out.println(GameState.WIN);
            return;
        }
    }

    public GameState play(Scanner scanner) {
        gameStatus = new GameStatus();
        this.scanner = scanner;

        while (gameStatus.isRunning()) {
            System.out.println(gameStatus.getCurrentSide());
            moveAnimalsToOtherSide();
            gameStatus.printPositionOfTheAnimals();
        }
        return gameStatus.getState();
    }

    private int moveAnimalIfPossible(final int totalAnimalOnSide,
                                     final int totalAnimalsInsideTheBoat,
                                     final String inputMessageToDisplay,
                                     final String invalidNumberOfAnimalMessage) {
        int animalInTheBoat = 0;
        if (isBoatNotFull(totalAnimalsInsideTheBoat) && isAnimalPresentOnThatSide(totalAnimalOnSide)) {
            System.out.println(inputMessageToDisplay);
            animalInTheBoat = obtainInputFromKeyboard();
            if (totalAnimalOnSide < animalInTheBoat) {
                System.err.println(invalidNumberOfAnimalMessage);
                animalInTheBoat = moveAnimalIfPossible(totalAnimalOnSide, totalAnimalsInsideTheBoat, inputMessageToDisplay, invalidNumberOfAnimalMessage);
            }
        }
        return animalInTheBoat;
    }

    private int obtainInputFromKeyboard() {
        while (!scanner.hasNextInt()) scanner.next();
        int numberInputFromKeyboard = scanner.nextInt();
        boolean isValidValue = numberInputFromKeyboard >= 0 && numberInputFromKeyboard <= 2;
        while (!isValidValue) {
            System.err.println(PLEASE_SELECT_A_NUMBER_BETWEEN_0_AND_2);
            numberInputFromKeyboard = scanner.nextInt();
            isValidValue = numberInputFromKeyboard >= 0 && numberInputFromKeyboard <= 2;
        }
        return numberInputFromKeyboard;
    }

    private static boolean isAnimalPresentOnThatSide(int animalPresentOnThatSide) {
        return animalPresentOnThatSide != 0;
    }

    public boolean isBoatNotFull(final int totalAnimalsInsideTheBoat) {
        return totalAnimalsInsideTheBoat != MAX_ANIMAL_ALLOWED_IN_THE_BOAT;
    }
}