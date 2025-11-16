package TrabAgro.Qualymentos.Qualymentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    List<Propriedade> findByUsuario(Usuario usuario);

    List<Propriedade> findByUsuarioId(String usuarioId);
    
}
