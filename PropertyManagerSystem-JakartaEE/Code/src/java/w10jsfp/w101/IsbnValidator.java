package w10jsfp.w101;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator(value = "isbnValidator")
public class IsbnValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String componentValue = value.toString();

        pattern = Pattern.compile("(?=[-0-9xX]{13}$)");
        matcher = pattern.matcher(componentValue);

        if (!matcher.find()) {
            String message = MessageFormat.format("Validation Error: {0} is not in the isbn format", componentValue);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            throw new ValidatorException(facesMessage);
        }
    }
}