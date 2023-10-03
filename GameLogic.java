public class GameLogic {

    private int turnCount;
    private boolean gameOver;
    private boolean winner;
    private int fuelCellCount;
    private Building[] buildings;
    private int jumperFuel;
    private int playerPosition;

    public GameLogic() {
        this.turnCount = 0;
        this.gameOver = false;
        this.winner = false;
        fuelCellCount = 0;
        buildings = new Building[15];
        this.jumperFuel = 10;
        this.playerPosition = 0;
    }

    private void buildStoreys() {
        for (int j = 4; j >= 0; j--) {
            String storeyString = "";
            for (int i = 0; i < buildings.length; i++) {
                if (buildings[i].getBuildingHeight() > j) {
                    if (buildings[i].getHasPoliceWeb()) {
                        storeyString += "W ";
                    } else if (buildings[i].getIsFrozen()) {
                        storeyString += "! ";
                    } else {
                        storeyString += "~ ";
                    }
                } else {
                    storeyString += "  ";
                }
            }
            System.out.println(storeyString);
        }

    }

    private void consumeFuel() {
        if (jumperFuel + 5 > 20) {
            jumperFuel = 20;
        } else {
            jumperFuel += 5;
        }
        fuelCellCount++;
        buildings[playerPosition].setHasFuelCell(false);
        System.out.println("\nYou consumed a fuel cell!");
    }

    public void displayGUI() {
        // Building[] buildings = items.getBuildings();
        System.out.println(
                "~ = Building, P = Player, F = Fuel Cell, X = Exit Portal, W = Police Web, ! = Frozen Building\n");
        int jumperFuel = getJumperFuel();
        if (jumperFuel < 0) {
            jumperFuel = 0;
        }
        System.out.println("Jumper fuel: " + jumperFuel + "\n");

        // Places player based off playerPosition
        String playerBaseString = "                              ";
        StringBuilder sb = new StringBuilder(playerBaseString);
        String playerString = sb.insert(playerPosition * 2, 'P').toString();
        System.out.println(playerString);

        // Places exit portal
        String exitPortal = "";
        for (int i = 0; i < buildings.length; i++) {
            if (buildings[i].getHasExitPortal()) {
                exitPortal += "X ";
            } else {
                exitPortal += "  ";
            }
        }
        System.out.println(exitPortal);

        // Level 6 (fuel cells)
        String fuelString = "";
        for (int i = 0; i < buildings.length; i++) {
            if (buildings[i].getHasFuelCell()) {
                fuelString += "F ";
            } else {
                fuelString += "  ";
            }
        }
        System.out.println(fuelString);

        // Builds the buildings levels 1-5
        buildStoreys();
    }

    public void escape() {
        System.out.println("\nYOU HAVE ESCAPED!\nYOU ARE FREE!\n");
        setGameOver(true);
        setWinner(true);
    }

    public int getJumperFuel() {
        return jumperFuel;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void jumpLeft() {
        int initialBuildingHeight = buildings[playerPosition].getBuildingHeight();
        setPlayerPosition(playerPosition -= buildings[playerPosition].getBuildingHeight());
        int destinationBuildingHeight = buildings[playerPosition].getBuildingHeight();
        if (buildings[playerPosition].getHasFuelCell()) {
            consumeFuel();
        }
        if (buildings[playerPosition].getHasPoliceWeb()) {
            inWeb();
        }
        jumperFuel -= Math.abs((initialBuildingHeight - destinationBuildingHeight) + 1);
        System.out.println("\nYou jumped backward " + initialBuildingHeight + " buildings!\n");
    }

    public void jumpRight() {
        int initialBuildingHeight = buildings[playerPosition].getBuildingHeight();
        setPlayerPosition(playerPosition += buildings[playerPosition].getBuildingHeight());
        int destinationBuildingHeight = buildings[playerPosition].getBuildingHeight();
        if (buildings[playerPosition].getHasFuelCell()) {
            consumeFuel();
        }
        if (buildings[playerPosition].getHasPoliceWeb()) {
            inWeb();
        }
        jumperFuel -= Math.abs((initialBuildingHeight - destinationBuildingHeight) + 1);
        System.out.println("\nYou jumped forward " + initialBuildingHeight + " buildings!\n");
    }

    public void stay() {
        jumperFuel -= 1;
        if (buildings[playerPosition].getHasFuelCell()) {
            consumeFuel();
        }
        if (buildings[playerPosition].getHasPoliceWeb()) {
            inWeb();
        }
        System.out.println("\nYou stayed on the same building!\n");
    }

    public void setJumperFuel(int jumperFuel) {
        this.jumperFuel = jumperFuel;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public Building getSpecificBuilding(int i) {
        return buildings[i];
    }

    public int getFuelCellCount() {
        return fuelCellCount;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean getWinner() {
        return winner;
    }

    public void inWeb() {
        System.out.println("\nYou are stuck in a police web!\n");
        jumperFuel -= 5;
    }

    public void outOfFuel() {
        System.out.println("\nYou ran out of fuel!\nGAME OVER\n");
        setGameOver(true);
        setWinner(false);
    }

    public void setBuildings(Building[] buildings) {
        this.buildings = buildings;
    }

    public void setFuelCellCount(int fuelCellCount) {
        this.fuelCellCount = fuelCellCount;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

}