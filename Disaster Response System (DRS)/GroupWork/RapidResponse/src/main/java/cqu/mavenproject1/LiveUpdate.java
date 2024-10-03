package cqu.mavenproject1;

import java.sql.Timestamp;

/**
 * The LiveUpdate class represents an update related to a disaster or resource,
 * including the type of update, a description, and a timestamp indicating when
 * the update occurred.
 *
 * @author asif
 */
public class LiveUpdate {

    private String updateType;  // The type of update (e.g., Resource, Disaster, Alert)
    private String description;  // A description of the update
    private Timestamp timestamp;  // The timestamp when the update was created

    /**
     * Constructor to create a LiveUpdate object.
     *
     * @param updateType the type of the update (e.g., Resource, Disaster,
     * Alert)
     * @param description a description of the update
     * @param timestamp the timestamp when the update occurred
     */
    public LiveUpdate(String updateType, String description, Timestamp timestamp) {
        this.updateType = updateType;
        this.description = description;
        this.timestamp = timestamp;
    }

    /**
     * Gets the type of the update.
     *
     * @return the update type
     */
    public String getUpdateType() {
        return updateType;
    }

    /**
     * Gets the description of the update.
     *
     * @return the update description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the timestamp of when the update was created.
     *
     * @return the timestamp of the update
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the update.
     *
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
