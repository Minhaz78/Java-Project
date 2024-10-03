package cqu.mavenproject1;

import java.time.LocalDate;

/**
 * The Disaster class represents a disaster event, including its type, location,
 * date, and description.
 *
 * @author asif
 */
public class Disaster {

    private String disasterType;  // The type of the disaster (e.g., Fire, Flood)
    private String location;      // The location where the disaster occurred
    private LocalDate date;       // The date when the disaster occurred
    private String description;   // A description of the disaster

    /**
     * Constructor to create a Disaster object.
     *
     * @param disasterType the type of the disaster (e.g., Fire, Flood)
     * @param location the location where the disaster occurred
     * @param date the date when the disaster occurred
     * @param description a description of the disaster
     */
    public Disaster(String disasterType, String location, LocalDate date, String description) {
        this.disasterType = disasterType;
        this.location = location;
        this.date = date;
        this.description = description;
    }

    /**
     * Gets the type of the disaster.
     *
     * @return the disaster type
     */
    public String getDisasterType() {
        return disasterType;
    }

    /**
     * Sets the type of the disaster.
     *
     * @param disasterType the disaster type to set
     */
    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    /**
     * Gets the location of the disaster.
     *
     * @return the disaster location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the disaster.
     *
     * @param location the disaster location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the date of the disaster.
     *
     * @return the disaster date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the disaster.
     *
     * @param date the disaster date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the description of the disaster.
     *
     * @return the disaster description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the disaster.
     *
     * @param description the disaster description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
