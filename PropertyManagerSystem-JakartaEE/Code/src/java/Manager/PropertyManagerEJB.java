package Manager;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PropertyManagerEJB {

    @PersistenceContext(unitName = "W10P1JSFPU")
    private EntityManager em;

    // Create a new property manager
    public PropertyManager createPropertyManager(PropertyManager manager) {
        em.persist(manager);
        return manager;
    }

    // Retrieve all property managers
    public List<PropertyManager> findAllManagers() {
        return em.createQuery("SELECT p FROM PropertyManager p", PropertyManager.class).getResultList();
    }

    // Find a property manager by ID
    public PropertyManager findManagerById(Long managerId) {
        return em.find(PropertyManager.class, managerId);
    }

    // Update a property manager
    public PropertyManager updateManager(PropertyManager manager) {
        return em.merge(manager);
    }

    // Delete a property manager
    public void deleteManager(PropertyManager manager) {
        em.remove(em.merge(manager));
    }
     // Method to find property managers by first and last name
    public List<PropertyManager> findManagersByName(String firstName, String lastName) {
        return em.createQuery("SELECT p FROM PropertyManager p WHERE p.firstName = :firstName AND p.lastName = :lastName", PropertyManager.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }
}
