public class Building
{
    private int buildingHeight;
    private boolean hasExitPortal;
    private boolean hasFuelCell;
    private boolean hasPoliceWeb;
    private boolean isFrozen;

    public Building()
    {
        this.buildingHeight = 2;
        this.hasExitPortal = false;
        this.hasFuelCell = false;
        this.hasPoliceWeb = false;
        this.isFrozen = false;
    }

    public Building(int buildingHeight, boolean hasExitPortal, boolean hasFuelCell,
    boolean hasPoliceWeb, boolean isFrozen)
    {
        this.buildingHeight = buildingHeight;
        this.hasExitPortal = hasExitPortal;
        this.hasFuelCell = hasFuelCell;
        this.hasPoliceWeb = hasPoliceWeb;
        this.isFrozen = isFrozen;
    }

    public String display()
    {
        return buildingHeight + "," + hasExitPortal + "," + hasFuelCell +
        "," + hasPoliceWeb + "," + isFrozen;
    }

    public int getBuildingHeight()
    {
        return buildingHeight;
    }

    public boolean getHasExitPortal()
    {
        return hasExitPortal;
    }

    public boolean getHasFuelCell()
    {
        return hasFuelCell;
    }

    public boolean getHasPoliceWeb()
    {
        return hasPoliceWeb;
    }

    public boolean getIsFrozen()
    {
        return isFrozen;
    }

    public void setBuildingHeight(int buildingHeight)
    {
        this.buildingHeight = buildingHeight;
    }

    public void setHasExitPortal(boolean hasExitPortal)
    {
        this.hasExitPortal = hasExitPortal;
    }

    public void setHasFuelCell(boolean hasFuelCell)
    {
        this.hasFuelCell = hasFuelCell;
    }

    public void setHasPoliceWeb(boolean hasPoliceWeb)
    {
        this.hasPoliceWeb = hasPoliceWeb;
    }

    public void setIsFrozen(boolean isFrozen)
    {
        this.isFrozen = isFrozen;
    }
}
