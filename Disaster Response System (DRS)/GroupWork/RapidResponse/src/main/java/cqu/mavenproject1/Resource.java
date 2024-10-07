package cqu.mavenproject1;

/**
 * The Resource class represents an emergency resource, such as medical supplies
 * or food, including its name, available and total units, and the date it was
 * last updated.
 *
 * @author asif
 */
public class Resource {

    private String resourceName;  // Name of the resource
    private int availableUnits;   // Number of available units of the resource
    private int totalUnits;       // Total number of units of the resource
    private String date;            // Date when the resource was last updated

    /**
     * Constructor to create a Resource object.
     *
     * @param resourceName the name of the resource
     * @param availableUnits the number of available units
     * @param totalUnits the total number of units
     * @param date the date when the resource was last updated
     */
    public Resource(String resourceName, int availableUnits, int totalUnits, String date) {
        this.resourceName = resourceName;
        this.availableUnits = availableUnits;
        this.totalUnits = totalUnits;
        this.date = date;
    }

    /**
     * Gets the name of the resource.
     *
     * @return the name of the resource
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Sets the name of the resource.
     *
     * @param resourceName the name of the resource
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Gets the number of available units of the resource.
     *
     * @return the number of available units
     */
    public int getAvailableUnits() {
        return availableUnits;
    }

    /**
     * Sets the number of available units of the resource.
     *
     * @param availableUnits the number of available units
     */
    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    /**
     * Gets the total number of units of the resource.
     *
     * @return the total number of units
     */
    public int getTotalUnits() {
        return totalUnits;
    }

    /**
     * Sets the total number of units of the resource.
     *
     * @param totalUnits the total number of units
     */
    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    /**
     * Gets the date when the resource was last updated.
     *
     * @return the date when the resource was last updated
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date when the resource was last updated.
     *
     * @param date the date when the resource was last updated
     */
    public void setDate(String date) {
        this.date = date;
    }
}
