package co.emp.softwareII.servicios;

import co.emp.softwareII.entidades.*;
import co.emp.softwareII.repositorios.AdminRepo;
import co.emp.softwareII.repositorios.CiudadRepo;
import co.emp.softwareII.repositorios.InmArriendoRepo;
import co.emp.softwareII.repositorios.InmVentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Métodos con consultas a la base de datos
 */

@Service
public class AdminServicioImpl implements AdminServicio{

    @Autowired
    private final InmArriendoRepo inmArriendoRepo;
    private final InmVentaRepo inmVentaRepo;
    private final CiudadRepo ciudadRepo;
    private final AdminRepo adminRepo;

    public AdminServicioImpl(InmArriendoRepo inmArriendoRepo, InmVentaRepo inmVentaRepo, CiudadRepo ciudadRepo, AdminRepo adminRepo) {
        this.inmArriendoRepo = inmArriendoRepo;
        this.inmVentaRepo = inmVentaRepo;
        this.ciudadRepo = ciudadRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public Administrador iniciarSesion(String correo, String password) throws Exception {
        if(correo.isEmpty() || password.isEmpty()){
            throw new Exception("Por favor rellenar todos los campos de texto");
        }

        Administrador admin = adminRepo.findByCorreo(correo).orElse(null);

        if (admin == null) {
            throw new Exception("El correo no existe");
        }

        if (!admin.getPassword().equals(password)){
            throw new Exception("La constraseña es incorrecta");
        }

        return admin;
    }

    @Override
    public InmArriendo crearInmArriendo(InmArriendo inmArriendo) {
        return inmArriendoRepo.save(inmArriendo);
    }

    @Override
    public InmArriendo actualizarInmArriendo(InmArriendo inmArriendo) throws Exception {
        Optional<InmArriendo> guardado = inmArriendoRepo.findById(inmArriendo.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El inmueble no existe");
        }
        return inmArriendoRepo.save(inmArriendo);
    }

    @Override
    public void eliminarInmArriendo(Integer codigoInmArriendo) throws Exception {
        Optional<InmArriendo> guardado = inmArriendoRepo.findById(codigoInmArriendo);

        if (guardado.isEmpty()){
            throw new Exception("El inmueble no existe");
        }
        inmArriendoRepo.delete(guardado.get());
    }

    @Override
    public List<InmArriendo> listarInmArriendo() {
        return inmArriendoRepo.findAll();
    }

    @Override
    public InmArriendo obtenerInmArriendo(Integer codigoInmArriendo) throws Exception {
        Optional<InmArriendo> guardado = inmArriendoRepo.findById(codigoInmArriendo);

        if (guardado.isEmpty()){
            throw new Exception("El inmueble no existe");
        }
        return guardado.get();
    }

    @Override
    public InmVenta crearInmVenta(InmVenta inmVenta) {
        return inmVentaRepo.save(inmVenta);
    }

    @Override
    public InmVenta actualizarInmVenta(InmVenta inmVenta) throws Exception {
        Optional<InmVenta> guardado = inmVentaRepo.findById(inmVenta.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El inmueble no existe");
        }
        return inmVentaRepo.save(inmVenta);
    }

    @Override
    public void eliminarInmVenta(Integer codigoInmVenta) throws Exception {
        Optional<InmVenta> guardado = inmVentaRepo.findById(codigoInmVenta);

        if (guardado.isEmpty()){
            throw new Exception("El inmueble no existe");
        }
        inmVentaRepo.delete(guardado.get());
    }

    @Override
    public List<InmVenta> listarInmVenta() {
        return inmVentaRepo.findAll();
    }

    @Override
    public InmVenta obtenerInmVenta(Integer codigoInmVenta) throws Exception {
        Optional<InmVenta> guardado = inmVentaRepo.findById(codigoInmVenta);

        if (guardado.isEmpty()){
            throw new Exception("El inmueble no existe");
        }
        return guardado.get();
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad){
        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Integer codigoCiudad) throws Exception {

        if(codigoCiudad ==  null){
            throw new Exception("No envio niguno codigo");
        }
        Ciudad ciudad = obtenerCiudad(codigoCiudad);

        if(ciudad == null){
            throw new Exception("La ciudad no existe");
        }
        ciudadRepo.delete(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception {

        Optional<Ciudad> ciudad = ciudadRepo.findById(codigoCiudad);

        if (ciudad.isEmpty()){
            throw new Exception("La ciudad no existe");
        }
        return ciudad.get();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {
        Optional<Ciudad> guardado = ciudadRepo.findById(ciudad.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("La ciudad no existe");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public List<InmVenta> listarInmuebleVentaCiudad(Integer codigo) {
        return adminRepo.listarInmuebleVentaCiudad(codigo);
    }

    @Override
    public List<InmArriendo> listarInmuebleArriendoCiudad(Integer codigo) {
        return adminRepo.listarInmuebleArriendoCiudad(codigo);
    }

    @Override
    public List<InmVenta> listarInmueblesVentaAntiguedad(Antiguedad antiguedad) {
        return adminRepo.listarInmueblesVentaAntiguedad(antiguedad);
    }

    @Override
    public List<InmArriendo> listarInmuebleArriendoAntiguedad(Antiguedad antiguedad) {
        return adminRepo.listarInmuebleArriendoAntiguedad(antiguedad);
    }

    @Override
    public List<InmVenta> listarInmueblesVentaTipoInmueble(TipoInmueble tipoInmueble) {
        return adminRepo.listarInmueblesVentaTipoInmueble(tipoInmueble);
    }

    @Override
    public List<InmArriendo> listarInmuebleArriendoTipoInmueble(TipoInmueble tipoInmueble) {
        return adminRepo.listarInmuebleArriendoTipoInmueble(tipoInmueble);
    }


}
