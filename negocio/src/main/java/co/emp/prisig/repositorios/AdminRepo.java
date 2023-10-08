package co.emp.prisig.repositorios;

import co.emp.prisig.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Administrador, Integer>  {

    Optional<Administrador> findByCorreo(String correo);

    @Query("select i from InmVenta i where i.ciudad.codigo = :codigo")
    List<InmVenta> listarInmuebleVentaCiudad(Integer codigo);

    @Query("select i from InmArriendo i where i.ciudad.codigo = :codigo")
    List<InmArriendo> listarInmuebleArriendoCiudad(Integer codigo);

    @Query("select i from InmVenta i where i.antiguedad = :antiguedad")
    List<InmVenta> listarInmueblesVentaAntiguedad(Antiguedad antiguedad);

    @Query("select i from InmArriendo i where i.antiguedad = :antiguedad")
    List<InmArriendo> listarInmuebleArriendoAntiguedad(Antiguedad antiguedad);

    @Query("select i from InmVenta i where i.tipoInmueble = :tipoInmueble")
    List<InmVenta> listarInmueblesVentaTipoInmueble(TipoInmueble tipoInmueble);

    @Query("select i from InmArriendo i where i.tipoInmueble = :tipoInmueble")
    List<InmArriendo> listarInmuebleArriendoTipoInmueble(TipoInmueble tipoInmueble);
}