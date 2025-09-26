package TrabAgro.Qualymentos.Qualymentos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository <Usuario , String> {
    Optional<Usuario> findByEmail(String email);   
}
