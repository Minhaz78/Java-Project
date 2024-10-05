package Manager;

import Property.RealEstateProperty;
import jakarta.persistence.*;
import java.util.List;



@Entity
@Table(name = "property_manager")
public class PropertyManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String email;
    // New field for allocated properties
    @OneToMany(mappedBy = "propertyManager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RealEstateProperty> allocatedProperties;

    // Constructors, Getters, and Setters

    public PropertyManager() {
    }

    public PropertyManager(String firstName, String lastName, String phone, String mobile, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<RealEstateProperty> getAllocatedProperties() {
        return allocatedProperties;
    }

    public void setAllocatedProperties(List<RealEstateProperty> allocatedProperties) {
        this.allocatedProperties = allocatedProperties;
    }
    @Override
    public String toString() {
        return "PropertyManager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
