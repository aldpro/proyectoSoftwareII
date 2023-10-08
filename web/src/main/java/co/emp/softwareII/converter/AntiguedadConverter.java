package co.emp.softwareII.converter;

import co.emp.softwareII.entidades.Antiguedad;
import org.springframework.stereotype.Component;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value="antiguedadConverter")
public class AntiguedadConverter extends EnumConverter {

    public AntiguedadConverter() {
        super(Antiguedad.class);
    }

}


