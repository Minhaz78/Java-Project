package Converter;

import jakarta.inject.Inject;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import Rent.RentProperty;
import Rent.RentPropertyEJB;

@FacesConverter(value = "rentPropertyConverter", managed = true)
public class RentPropertyConverter implements Converter<RentProperty> {

    @Inject  // Use CDI Injection
    private RentPropertyEJB rentPropertyEJB;

    @Override
    public RentProperty getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return rentPropertyEJB.findRentPropertyById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, RentProperty property) {
        return property != null && property.getId() != null ? property.getId().toString() : "";
    }
}
