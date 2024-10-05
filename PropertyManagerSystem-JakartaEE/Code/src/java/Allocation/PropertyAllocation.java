package Allocation;

import Manager.PropertyManager;
import Sale.SaleProperty;
import Rent.RentProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "property_allocation")
public class PropertyAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private PropertyManager propertyManager;

    @ManyToOne
    @JoinColumn(name = "sale_property_id", nullable = true) // Nullable because it can be either sale or rent property
    private SaleProperty saleProperty;

    @ManyToOne
    @JoinColumn(name = "rent_property_id", nullable = true) // Nullable because it can be either sale or rent property
    private RentProperty rentProperty;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_created")
    private Date timeCreated;

    // Getters and Setters for each attribute

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropertyManager getPropertyManager() {
        return propertyManager;
    }

    public void setPropertyManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }

    public SaleProperty getSaleProperty() {
        return saleProperty;
    }

    public void setSaleProperty(SaleProperty saleProperty) {
        this.saleProperty = saleProperty;
    }

    public RentProperty getRentProperty() {
        return rentProperty;
    }

    public void setRentProperty(RentProperty rentProperty) {
        this.rentProperty = rentProperty;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
