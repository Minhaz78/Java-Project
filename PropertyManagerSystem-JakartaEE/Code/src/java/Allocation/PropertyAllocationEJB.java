package Allocation;

import Manager.PropertyManager;
import Sale.SaleProperty;
import Rent.RentProperty;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PropertyAllocationEJB {

    @PersistenceContext(unitName = "W10P1JSFPU")
    private EntityManager em;

    // Allocate a sale or rent property to a manager
    public void allocateProperty(PropertyManager manager, SaleProperty saleProperty, RentProperty rentProperty) {
        PropertyAllocation allocation = new PropertyAllocation();
        
        allocation.setPropertyManager(manager);
        if (saleProperty != null) {
            allocation.setSaleProperty(saleProperty);  // Set Sale Property
        }
        if (rentProperty != null) {
            allocation.setRentProperty(rentProperty);  // Set Rent Property
        }
        allocation.setTimeCreated(new java.util.Date());

        em.persist(allocation);
    }

    // Find all allocations
    public List<PropertyAllocation> findAllAllocations() {
        return em.createQuery("SELECT a FROM PropertyAllocation a", PropertyAllocation.class).getResultList();
    }

    // Delete an allocation by ID
    public void deleteAllocation(Long allocationId) {
        PropertyAllocation allocation = em.find(PropertyAllocation.class, allocationId);
        if (allocation != null) {
            em.remove(allocation);
        }
    }
}
