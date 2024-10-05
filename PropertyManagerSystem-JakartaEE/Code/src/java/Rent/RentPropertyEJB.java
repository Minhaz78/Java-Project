package Rent;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RentPropertyEJB {

    @PersistenceContext(unitName = "W10P1JSFPU")
    private EntityManager em;

    // Create a new rent property
    public RentProperty createRentProperty(RentProperty rentProperty) {
        em.persist(rentProperty);
        return rentProperty;
    }

    // Retrieve all rent properties
    public List<RentProperty> findRentProperties() {
        return em.createQuery("SELECT r FROM RentProperty r", RentProperty.class).getResultList();
    }

    // Find rent property by ID
    public RentProperty findRentPropertyById(Long rentPropertyId) {
        return em.find(RentProperty.class, rentPropertyId);
    }

    // Update an existing rent property
    public RentProperty updateRentProperty(RentProperty rentProperty) {
        return em.merge(rentProperty);
    }

    // Delete a rent property
    public void deleteRentProperty(RentProperty rentProperty) {
        em.remove(em.merge(rentProperty));
    }
}
