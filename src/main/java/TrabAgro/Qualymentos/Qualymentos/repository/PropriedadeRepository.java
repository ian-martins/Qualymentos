package TrabAgro.Qualymentos.Qualymentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    
}
