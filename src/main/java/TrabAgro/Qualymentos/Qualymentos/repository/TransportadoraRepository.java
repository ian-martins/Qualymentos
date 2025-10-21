package TrabAgro.Qualymentos.Qualymentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Transporte;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transporte, Long> {
    List<Transporte> findByUsuario(Usuario usuario);
}
