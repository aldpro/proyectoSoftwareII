package co.emp.prisig.repositorios;

import co.emp.prisig.entidades.InmArriendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmArriendoRepo  extends JpaRepository<InmArriendo, Integer> {

}
