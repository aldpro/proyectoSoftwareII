package co.emp.softwareII.servicios;

import co.emp.softwareII.entidades.*;

import java.util.List;

public interface AdminServicio {

    Administrador iniciarSesion(String correo, String password)throws Exception;

    //Gestion de inmuebles para arriendo
    InmArriendo crearInmArriendo(InmArriendo inmArriendo);

    InmArriendo actualizarInmArriendo(InmArriendo inmArriendo) throws Exception;

    void eliminarInmArriendo(Integer codigoInmArriendo) throws Exception;

    List<InmArriendo> listarInmArriendo();

    InmArriendo obtenerInmArriendo(Integer codigoInmArriendo) throws Exception;

    //Gestion de inmuebles para venta

    InmVenta crearInmVenta(InmVenta inmVenta);

    InmVenta actualizarInmVenta(InmVenta inmVenta) throws Exception;

    void eliminarInmVenta(Integer codigoInmVenta) throws Exception;

    List<InmVenta> listarInmVenta();

    InmVenta obtenerInmVenta(Integer codigoInmVenta) throws Exception;

    //Gestion de ciudades

    Ciudad crearCiudad(Ciudad ciudad);

    void eliminarCiudad(Integer codigoCiudad) throws Exception;

    Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception;

    List<Ciudad> listarCiudades();

    Ciudad actualizarCiudad(Ciudad ciudad) throws Exception;
    // Este método lista los inmuebles
    List<InmVenta> listarInmuebleVentaCiudad(Integer codigo);

    List<InmArriendo> listarInmuebleArriendoCiudad(Integer codigo);

    List<InmVenta> listarInmueblesVentaAntiguedad(Antiguedad antiguedad);

    List<InmArriendo> listarInmuebleArriendoAntiguedad(Antiguedad antiguedad);

    List<InmVenta> listarInmueblesVentaTipoInmueble(TipoInmueble tipoInmueble);

    List<InmArriendo> listarInmuebleArriendoTipoInmueble(TipoInmueble tipoInmueble);
}
