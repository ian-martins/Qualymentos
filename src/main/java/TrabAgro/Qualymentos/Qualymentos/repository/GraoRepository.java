package TrabAgro.Qualymentos.Qualymentos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;

@Repository
public interface GraoRepository extends JpaRepository<Grao, Long> {
    List<Grao> findByPropriedade(Propriedade propriedade);

}
