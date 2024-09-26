package cqu.drs;

public class Resource {
    private String name;
    private int availableUnits;

    public Resource(String name, int availableUnits) {
        this.name = name;
        this.availableUnits = availableUnits;
    }

    public String getName() {
        return name;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }
}
