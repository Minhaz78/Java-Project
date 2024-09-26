package cqu.drs;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a Disaster entity with its properties like type, description, location, severity, and criticality.
 * This class uses JavaFX properties for data binding in a UI.
 * 
 * @author Mohammad Minhaz Uddin
 */
public class Disaster {

    private final StringProperty type;
    private final StringProperty description;
    private final StringProperty location;
    private final IntegerProperty severity;
    private final BooleanProperty critical;

    /**
     * Constructs a Disaster object.
     * 
     * @param type        The type of the disaster (e.g., "Fire", "Flood").
     * @param description A brief description of the disaster.
     * @param location    The location where the disaster occurred.
     * @param severity    The severity level of the disaster (1-10).
     * @param critical    Whether the disaster is critical (true/false).
     */
    public Disaster(String type, String description, String location, int severity, boolean critical) {
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.severity = new SimpleIntegerProperty(severity);
        this.critical = new SimpleBooleanProperty(critical);
    }

    /**
     * Returns the disaster type property.
     * 
     * @return type property as a StringProperty.
     */
    public StringProperty typeProperty() {
        return type;
    }

    /**
     * Returns the disaster description property.
     * 
     * @return description property as a StringProperty.
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Returns the disaster location property.
     * 
     * @return location property as a StringProperty.
     */
    public StringProperty locationProperty() {
        return location;
    }

    /**
     * Returns the disaster severity property.
     * 
     * @return severity property as an IntegerProperty.
     */
    public IntegerProperty severityProperty() {
        return severity;
    }

    /**
     * Returns the disaster criticality property.
     * 
     * @return critical property as a BooleanProperty.
     */
    public BooleanProperty criticalProperty() {
        return critical;
    }

    /**
     * Gets the type of the disaster.
     * 
     * @return the type of the disaster.
     */
    public String getType() {
        return type.get();
    }

    /**
     * Gets the description of the disaster.
     * 
     * @return the description of the disaster.
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Gets the location of the disaster.
     * 
     * @return the location of the disaster.
     */
    public String getLocation() {
        return location.get();
    }

    /**
     * Gets the severity of the disaster.
     * 
     * @return the severity of the disaster.
     */
    public int getSeverity() {
        return severity.get();
    }

    /**
     * Checks if the disaster is critical.
     * 
     * @return true if the disaster is critical, false otherwise.
     */
    public boolean isCritical() {
        return critical.get();
    }
}
