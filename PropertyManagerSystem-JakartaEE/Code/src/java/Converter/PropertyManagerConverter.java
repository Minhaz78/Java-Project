package Converter;

import jakarta.inject.Inject;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import Manager.PropertyManager;
import Manager.PropertyManagerEJB;

@FacesConverter(value = "propertyManagerConverter", managed = true)
public class PropertyManagerConverter implements Converter {

    @Inject  // Use CDI Injection instead of @EJB
    private PropertyManagerEJB managerEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return managerEJB.findManagerById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if (object == null || !(object instanceof PropertyManager)) {
            return "";
        }
        return ((PropertyManager) object).getId().toString();
    }
}
