package co.emp.prisig.repositorios;

import co.emp.prisig.entidades.InmArriendo;
import co.emp.prisig.entidades.InmVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmVentaRepo extends JpaRepository<InmVenta, Integer>  {
}
