package co.emp.prisig.converter;

import co.emp.prisig.entidades.TipoInmueble;
import org.springframework.stereotype.Component;

import javax.faces.convert.EnumConverter;

@Component
public class TipoInmuebleConverter extends EnumConverter {

    public TipoInmuebleConverter(){
        super(TipoInmueble.class);
    }
}
