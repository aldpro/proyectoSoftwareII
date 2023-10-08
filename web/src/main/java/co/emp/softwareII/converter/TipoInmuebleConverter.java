package co.emp.softwareII.converter;

import co.emp.softwareII.entidades.TipoInmueble;
import org.springframework.stereotype.Component;

import javax.faces.convert.EnumConverter;

@Component
public class TipoInmuebleConverter extends EnumConverter {

    public TipoInmuebleConverter(){
        super(TipoInmueble.class);
    }
}
