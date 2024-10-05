package ManagerController;

import Manager.PropertyManager;
import Manager.PropertyManagerEJB;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named 
@RequestScoped
public class PropertyManagerController {

    @EJB
    private PropertyManagerEJB managerEJB;
    private PropertyManager propertyManager = new PropertyManager();
    private Long managerId;  // To store the selected manager ID
    private String firstName;  // For searching by first name
    private String lastName;   // For searching by last name
    private List<PropertyManager> searchResults;
    private List<PropertyManager> propertyManagers;
// Getters and Setters
    public PropertyManager getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }
    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
       public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PropertyManager> getSearchResults() {
        return searchResults;
    }
    public List<PropertyManager> getPropertyManagers() {
        if (propertyManagers == null) {
            propertyManagers = managerEJB.findAllManagers();
        }
        return propertyManagers;
    }

    // Create property manager
    public String createManager() {
        try {
            managerEJB.createPropertyManager(propertyManager);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Property Manager created successfully"));
            return "listPropertyManagers.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to create property manager"));
            return null;
        }
    }
    // Method to view the details of a property manager including allocations
    public String viewManagerDetails(Long id) {
        this.propertyManager = managerEJB.findManagerById(id);  // Fetch manager and allocated properties
        return "viewManagerDetails.xhtml?faces-redirect=true";  // Navigate to the details page
    }
    // Method to search property managers by first name and last name
    public String searchManager() {
        searchResults = managerEJB.findManagersByName(firstName, lastName);
        if (searchResults.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "No manager found"));
        }
        return "searchManagerResult.xhtml";  // Navigate to search result page
    }

    // Retrieve all property managers
    public List<PropertyManager> getAllManagers() {
        return managerEJB.findAllManagers();
    }
}
