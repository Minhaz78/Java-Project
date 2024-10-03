package cqu.mavenproject1;

import java.sql.Date;

/**
 * The DisasterAlert class represents an alert related to a disaster, including
 * the date, type, severity, critical status, and description of the alert.
 *
 * @author asif
 */
public class DisasterAlert {

    private Date date;           // The date when the alert was issued
    private String alertType;    // The type of the alert (e.g., Fire, Flood)
    private int severity;        // The severity level of the alert (e.g., 1-5)
    private String critical;     // Indicates whether the alert is critical (e.g., High, Medium, Low)
    private String description;  // A description of the alert

    /**
     * Constructor to create a DisasterAlert object.
     *
     * @param date the date when the alert was issued
     * @param alertType the type of the alert (e.g., Fire, Flood)
     * @param severity the severity level of the alert
     * @param critical the critical status of the alert (e.g., High, Medium,
     * Low)
     * @param description a description of the alert
     */
    public DisasterAlert(Date date, String alertType, int severity, String critical, String description) {
        this.date = date;
        this.alertType = alertType;
        this.severity = severity;
        this.critical = critical;
        this.description = description;
    }

    /**
     * Gets the date of the alert.
     *
     * @return the alert date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the alert.
     *
     * @param date the alert date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the type of the alert.
     *
     * @return the alert type
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * Sets the type of the alert.
     *
     * @param alertType the alert type to set
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    /**
     * Gets the severity level of the alert.
     *
     * @return the alert severity
     */
    public int getSeverity() {
        return severity;
    }

    /**
     * Sets the severity level of the alert.
     *
     * @param severity the alert severity to set
     */
    public void setSeverity(int severity) {
        this.severity = severity;
    }

    /**
     * Gets the critical status of the alert.
     *
     * @return the critical status of the alert
     */
    public String getCritical() {
        return critical;
    }

    /**
     * Sets the critical status of the alert.
     *
     * @param critical the critical status to set (e.g., High, Medium, Low)
     */
    public void setCritical(String critical) {
        this.critical = critical;
    }

    /**
     * Gets the description of the alert.
     *
     * @return the alert description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the alert.
     *
     * @param description the alert description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
