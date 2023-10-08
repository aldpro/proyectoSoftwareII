package co.emp.softwareII.repositorios;

import co.emp.softwareII.entidades.InmArriendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmArriendoRepo  extends JpaRepository<InmArriendo, Integer> {

}
