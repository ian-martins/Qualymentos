package TrabAgro.Qualymentos.Qualymentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "propriedade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propriedade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo_rural", nullable = false, unique = true)
    private String codigoRural;

    @Column(name = "area", nullable = false)
    private String areaTotal;
    
    @Column(name = "municipio", nullable = false)
    private String municipio;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "tipo_solo", nullable = false)
    private String tipoSolo;

    @Column(name = "tipo_producao", nullable = false)
    private String tipoProducao;
    
    @Column(name = "tipo_cultura")
    private String tipoCultura;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
