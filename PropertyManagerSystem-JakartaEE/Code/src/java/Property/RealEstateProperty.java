package Property;

import Manager.PropertyManager;
import jakarta.persistence.*;

@Entity
@Table(name = "real_estate_property")
public class RealEstateProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String propertyType;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private PropertyManager propertyManager;

    // Other attributes like bedrooms, bathrooms, price, etc.

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyManager getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }
}
