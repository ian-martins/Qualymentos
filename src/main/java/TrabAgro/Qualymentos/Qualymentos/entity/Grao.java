package TrabAgro.Qualymentos.Qualymentos.entity;

import java.time.LocalDate;

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
@Table(name = "grao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tipo_grao", nullable = false)
    private String tipoGrao;

    @Column(name = "data_plantio")
    private LocalDate dataPlantio;

    @Column(name = "data_colheita")
    private LocalDate dataColheita;

    @Column(name = "area_plantada")
    private String areaPlantada;

    @Column(name = "produtividade")
    private String produtividade;

    @ManyToOne
    @JoinColumn(name = "propriedade_id")
    private Propriedade propriedade;
}
