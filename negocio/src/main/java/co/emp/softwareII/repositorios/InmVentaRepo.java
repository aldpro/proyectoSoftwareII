package co.emp.softwareII.repositorios;

import co.emp.softwareII.entidades.InmVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmVentaRepo extends JpaRepository<InmVenta, Integer>  {
}
