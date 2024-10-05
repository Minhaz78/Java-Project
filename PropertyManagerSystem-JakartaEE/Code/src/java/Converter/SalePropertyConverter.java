package Converter;

import jakarta.inject.Inject;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import Sale.SaleProperty;
import Sale.SalePropertyEJB;

@FacesConverter(value = "salePropertyConverter", managed = true)
public class SalePropertyConverter implements Converter<SaleProperty> {

    @Inject
    private SalePropertyEJB salePropertyEJB;  // CDI Injection

    @Override
    public SaleProperty getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return salePropertyEJB.findPropertyById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, SaleProperty property) {
        return property != null && property.getId() != null ? property.getId().toString() : "";
    }
}
