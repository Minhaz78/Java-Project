/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RentController;

/**
 *
 * @author User
 */


import Rent.RentProperty;
import Rent.RentPropertyEJB;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class RentPropertyController {

    @EJB
    private RentPropertyEJB rentPropertyEJB;
    private RentProperty rentProperty = new RentProperty();
    private Long rentPropertyId;  // For searching by ID
    private RentProperty foundRentProperty;  // For displaying the found property
    private List<RentProperty> properties;
    // Getters and Setters

    public RentProperty getRentProperty() {
        return rentProperty;
    }

    public void setRentProperty(RentProperty rentProperty) {
        this.rentProperty = rentProperty;
    }

    public Long getRentPropertyId() {
        return rentPropertyId;
    }

    public void setRentPropertyId(Long rentPropertyId) {
        this.rentPropertyId = rentPropertyId;
    }

    public RentProperty getFoundRentProperty() {
        return foundRentProperty;
    }

    public void setFoundRentProperty(RentProperty foundRentProperty) {
        this.foundRentProperty = foundRentProperty;
    }
    //Getter for the properties list
    public List<RentProperty> getProperties() {
        if (properties == null) {
            properties = rentPropertyEJB.findRentProperties();
        }
        return properties;
    }
    // Setter for the properties list
    public void setProperties(List<RentProperty> properties) {
        this.properties = properties;
    }

    // Create rent property
    public String createRentProperty() {
        rentPropertyEJB.createRentProperty(rentProperty);
        return "listRentProperties.xhtml?faces-redirect=true";
    }

    // Find a rent property by ID
    public String searchRentPropertyById() {
        foundRentProperty = rentPropertyEJB.findRentPropertyById(rentPropertyId);
        if (foundRentProperty != null) {
            return "searchRentResult.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Property not found", "No rent property found with that ID."));
            return null;
        }
    }

    // Retrieve all rent properties
    public List<RentProperty> getRentProperties() {
        return rentPropertyEJB.findRentProperties();
    }
}
