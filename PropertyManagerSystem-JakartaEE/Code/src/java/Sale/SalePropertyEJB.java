/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sale;

/**
 *
 * @author User
 */


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class SalePropertyEJB {

    @PersistenceContext(unitName = "W10P1JSFPU")
    private EntityManager em;

    // Retrieve all properties
    public List<SaleProperty> findProperties() {
        Query query = em.createQuery("SELECT p FROM SaleProperty p");
        return query.getResultList();
    }

    // Add a new property
    public SaleProperty createProperty(SaleProperty property) {
        em.persist(property);
        return property;
    }

    // Update an existing property
    public SaleProperty updateProperty(SaleProperty property) {
        return em.merge(property);
    }

    // Delete a property
    public void deleteProperty(SaleProperty property) {
        em.remove(em.merge(property));
    }
    public SaleProperty findPropertyById(Long propertyId) {
    return em.find(SaleProperty.class, propertyId);
}
}
