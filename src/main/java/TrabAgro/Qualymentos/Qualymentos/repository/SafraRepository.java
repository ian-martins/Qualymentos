package TrabAgro.Qualymentos.Qualymentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Safra;

@Repository
public interface SafraRepository extends JpaRepository<Safra, String>{

    boolean existsByTransporteId(Long id);
    
}
