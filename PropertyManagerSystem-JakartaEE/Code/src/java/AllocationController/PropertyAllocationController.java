package AllocationController;

import Manager.PropertyManager;
import Sale.SaleProperty;
import Rent.RentProperty;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import Allocation.PropertyAllocationEJB;
import Allocation.PropertyAllocation;

@Named
@RequestScoped
public class PropertyAllocationController {

    @EJB
    private PropertyAllocationEJB allocationEJB;

    private PropertyManager selectedManager;  // Selected Property Manager
    private SaleProperty selectedSaleProperty;  // Selected Sale Property
    private RentProperty selectedRentProperty;  // Selected Rent Property
    private PropertyAllocation newAllocation = new PropertyAllocation();  // New Allocation

    // List of allocations for viewing
    private List<PropertyAllocation> allocations;

    // Getters and Setters
    public PropertyManager getSelectedManager() {
        return selectedManager;
    }

    public void setSelectedManager(PropertyManager selectedManager) {
        this.selectedManager = selectedManager;
    }

    public SaleProperty getSelectedSaleProperty() {
        return selectedSaleProperty;
    }

    public void setSelectedSaleProperty(SaleProperty selectedSaleProperty) {
        this.selectedSaleProperty = selectedSaleProperty;
    }

    public RentProperty getSelectedRentProperty() {
        return selectedRentProperty;
    }

    public void setSelectedRentProperty(RentProperty selectedRentProperty) {
        this.selectedRentProperty = selectedRentProperty;
    }

    public List<PropertyAllocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<PropertyAllocation> allocations) {
        this.allocations = allocations;
    }

    // Allocate a property to a manager
    public String createAllocation() {
        try {
            // Create the allocation using the selected manager and property objects
            allocationEJB.allocateProperty(selectedManager, selectedSaleProperty, selectedRentProperty);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Allocation created successfully"));
            return "listAllocation.xhtml?faces-redirect=true";  // Redirect to the list of allocations
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to create allocation"));
            return null;
        }
    }

    // Load all allocations
    public void loadAllocations() {
        allocations = allocationEJB.findAllAllocations();
    }

    // Delete an allocation
    public void deleteAllocation(Long allocationId) {
        try {
            allocationEJB.deleteAllocation(allocationId);
            loadAllocations(); // Reload the list
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete allocation"));
        }
    }
}
