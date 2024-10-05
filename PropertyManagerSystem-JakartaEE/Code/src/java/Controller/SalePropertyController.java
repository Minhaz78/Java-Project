package Controller;

import Sale.SaleProperty;
import Sale.SalePropertyEJB;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named("salePropertyController")
@RequestScoped
public class SalePropertyController {

    @EJB
    private SalePropertyEJB propertyEJB;

    private Long propertyId;  // Property ID input by the user
    private SaleProperty foundProperty;  // To store the found property
    private SaleProperty property = new SaleProperty();  // New property to create

    // Getters and Setters
    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public SaleProperty getFoundProperty() {
        return foundProperty;
    }

    public void setFoundProperty(SaleProperty foundProperty) {
        this.foundProperty = foundProperty;
    }

    public SaleProperty getProperty() {
        return property;
    }

    public void setProperty(SaleProperty property) {
        this.property = property;
    }

    // Method to get all properties
    public List<SaleProperty> getProperties() {
        return propertyEJB.findProperties();
    }

    // Method to create a property and return navigation outcome
    public String createProperty() {
        try {
            propertyEJB.createProperty(property);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Property created successfully"));
            return "success";  // Trigger navigation to listProperties.xhtml
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to create property"));
            return null;  // Stay on the current page in case of failure
        }
    }

    // Method to search a sale property by ID
    public String searchPropertyById() {
        try {
            foundProperty = propertyEJB.findPropertyById(propertyId);  // Call EJB method to search
            if (foundProperty != null) {
                System.out.println("Property found: " + foundProperty.toString());
                return "searchSaleResult.xhtml?faces-redirect=true";  // Redirect to the result page
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Property not found"));
                return null;  // Stay on the search page if no property found
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error searching property"));
            return null;  // Stay on the search page if an error occurs
        }
    }
}
