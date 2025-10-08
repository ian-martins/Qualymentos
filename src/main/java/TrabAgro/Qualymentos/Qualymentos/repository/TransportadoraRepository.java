package TrabAgro.Qualymentos.Qualymentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TrabAgro.Qualymentos.Qualymentos.entity.Transporte;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transporte, Long> {
    
}
