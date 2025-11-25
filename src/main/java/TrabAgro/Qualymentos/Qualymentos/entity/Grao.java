package TrabAgro.Qualymentos.Qualymentos.entity;

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

    @Column(name = "subtipo_grao")
    private String subtipoGrao;

    @ManyToOne
    @JoinColumn(name = "propriedade_id")
    private Propriedade propriedade;

    @OneToMany(mappedBy = "grao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Safra> safras;
    
}
