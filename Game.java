public class Game {

    private GameLogic gameLogic;
    private Player player;

    public Game() {
        this.gameLogic = new GameLogic();
        this.player = new Player();
    }

    public Game(GameLogic gameLogic, Player player) {
        this.gameLogic = gameLogic;
        this.player = player;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public Player getPlayer() {
        return player;
    }

    private void printIntroduction() {
        System.out.println(
                "===============================================================================================");
        System.out.println(
                "||                        WELCOME TO NOWHERE LAND WHERE NO ONE ESCAPES!                      ||");
        System.out.println(
                "||  You are quested to try and escape using the only jumper device available in Nowhere.     ||");
        System.out.println(
                "||  The cost of this device is not free! But let's discuss payment IF you manage to escape!  ||");
        System.out.println(
                "||  Remember the following to ensure you surivve:                                            ||");
        System.out.println(
                "||  * the device will only allow you to jump short distances                                 ||");
        System.out.println(
                "||  * the jump distance is based on the height difference of the buildings being jumped      ||");
        System.out.println(
                "||  * the building heights change very frequently over time                                  ||");
        System.out.println(
                "||  * fuel cells found on rooftops can refuel the device for a short while                   ||");
        System.out.println(
                "||  * stay far away from the frozen buildings                                                ||");
        System.out.println(
                "||  Look out for the Nowhere police webs                                                     ||");
        System.out.println(
                "||  Lastly the Underground Guild takes no responsibility or provides an guarantees           ||");
        System.out.println(
                "||  Should you survive, we WILL come to collect! Good luck!                                  ||");
        System.out.println(
                "===============================================================================================\n");
    }

    private void programLoop() {
        Input input = new Input();
        int playerPosition = gameLogic.getPlayerPosition();
        Building[] buildings = gameLogic.getBuildings();
        boolean flag = true;
        do {
            try {
                switch (input.acceptIntegerInput(
                        "Enter 1 to jump forward\nEnter 2 to jump backward\nEnter 3 to skip a turn\nPlease select your action:")) {
                    case 1:

                        if (buildings[playerPosition].getIsFrozen()) {
                            System.out.println(
                                    "\nYou are stuck on a frozen building!\nSelect 3 to stay and miss your turn!\n");
                            gameLogic.stay();
                        } else {
                            if ((playerPosition + (buildings[playerPosition].getBuildingHeight())) > 14) {
                                System.out.println("\nYou can't jump forward past the last building!\n");
                            } else {
                                try {
                                    gameLogic.jumpRight();
                                    flag = false;
                                } catch (Exception e) {
                                    System.out.println("Someting Wong" + e);
                                }
                            }
                        }

                        break;

                    case 2:
                        if (buildings[playerPosition].getIsFrozen()) {
                            System.out.println(
                                    "\nYou are stuck on a frozen building!\nSelect 3 to stay and miss your turn!\n");
                            gameLogic.stay();
                        } else {
                            if ((playerPosition - (buildings[playerPosition].getBuildingHeight())) < 0) {
                                System.out.println("\nYou can't jump backward past the first building!\n");
                            } else {
                                gameLogic.jumpLeft();
                                flag = false;
                            }
                        }
                        break;

                    case 3:
                        gameLogic.stay();
                        flag = false;
                        break;

                    default:
                        System.out.println("Please enter a valid input:");
                }
            } catch (Exception e) {
                System.out.println("\nPlease enter a valid input!:");
            }

        } while (flag);
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void startProgram() {
        Shuffle shuffle = new Shuffle();
        Building[] buildings = gameLogic.getBuildings();
        shuffle.parseFile(buildings);

        int playerPosition = gameLogic.getPlayerPosition();
        int turnCount = gameLogic.getTurnCount();

        printIntroduction();

        player.acceptUserName();

        while (!(gameLogic.getGameOver())) {
            gameLogic.displayGUI();
            if (gameLogic.getJumperFuel() <= 0) {
                gameLogic.displayGUI();
                gameLogic.outOfFuel();
            } else if (buildings[gameLogic.getPlayerPosition()].getHasExitPortal() &&
                    !(buildings[playerPosition].getIsFrozen())) {
                gameLogic.displayGUI();
                gameLogic.escape();
            } else {
                programLoop();
            }
            gameLogic.setTurnCount(turnCount++);
            shuffle.shuffleGUI(buildings);
            if (turnCount % 3 == 0) {
                shuffle.shuffleFuelCells(buildings);
            }
        }

        writeStats();

    }

    private void writeStats() {
        FileIO fileIO = new FileIO();
        String statString = "Player: " + player.getUserName() + ", Turns played: " + gameLogic.getTurnCount()
                + ", Fuel Cell Count: " +
                gameLogic.getFuelCellCount() + ", " + (gameLogic.getWinner() ? "You are victorious!" : "You lose!");
        System.out.println(statString);
        fileIO.writeFile(statString);
    }

}