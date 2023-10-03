import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffle {

    public Shuffle() {

    }

    public void parseFile(Building[] buildings) {
        FileIO fileIO = new FileIO();
        String file = fileIO.readFile();
        String[] lines = file.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] buildingParams = lines[i].split(",");
            int heightOfBuilding = 0;
            boolean exitPortal = false;
            boolean fuelCell = false;
            boolean policeWeb = false;
            boolean frozen = false;
            try {
                heightOfBuilding = Integer.parseInt(buildingParams[0]);
                exitPortal = Boolean.parseBoolean(buildingParams[1]);
                fuelCell = Boolean.parseBoolean(buildingParams[2]);
                policeWeb = Boolean.parseBoolean(buildingParams[3]);
                frozen = Boolean.parseBoolean(buildingParams[4]);
            } catch (Exception e) {
                System.out.println("Error assigning building. Skipping");
                continue;
            }
            buildings[i] = new Building(heightOfBuilding, exitPortal, fuelCell, policeWeb, frozen);
        }
    }

    public void shuffleFuelCells(Building[] buildings) {
        // Randomise locations of fuel cells with 4 unique numbers every three turns
        List<Boolean> flags = new ArrayList<Boolean>();
        for (int i = 0; i < 11; i++)
            flags.add(false);
        for (int i = 0; i < 4; i++)
            flags.add(true);
        Collections.shuffle(flags);
        for (int i = 0; i < flags.size(); i++) {
            buildings[i].setHasFuelCell(flags.get(i));
        }
    }

    public void shuffleGUI(Building[] buildings) {
        // Randomise building heights
        for (Building building : buildings) {
            building.setBuildingHeight((int) ((Math.random() * 5) + 1));
        }

        // Randomise police web with setHasPoliceWeb()
        for (Building building : buildings) {
            building.setHasPoliceWeb(false);
        }
        buildings[(int) ((Math.random() * 14))].setHasPoliceWeb(true);

        // Randomise frozen buildings with setFrozen()
        for (Building building : buildings) {
            building.setIsFrozen(false);
        }
        buildings[(int) ((Math.random() * 14))].setIsFrozen(true);
    }

}