package co.emp.prisig.repositorios;

import co.emp.prisig.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {
}
