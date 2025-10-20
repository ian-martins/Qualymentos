package TrabAgro.Qualymentos.Qualymentos.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Column(name = "codigo_rural", nullable = false, unique = false)
    private String codigoRural;

    @Column(name = "area", nullable = false)
    private String areaTotal;

    @Column(name = "tipo_solo", nullable = false)
    private String tipoSolo;

    @Column(name = "tipo_producao", nullable = false)
    private String tipoProducao;

    @Column(name = "tipo_cultura")
    private String tipoCultura;

    @Column(name = "fone")
    private String fone;
    
    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
   
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "propriedade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grao> graos = new ArrayList<>();

}
