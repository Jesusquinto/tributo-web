package co.tributo.cliente.repository;

import co.tributo.cliente.model.FrUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<FrUsuario, Long> {

    Optional<FrUsuario> findByEmail(String email);

    Boolean existsByEmail(String email);


}
