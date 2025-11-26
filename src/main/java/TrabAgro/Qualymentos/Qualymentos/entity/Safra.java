package TrabAgro.Qualymentos.Qualymentos.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Safra {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String lote;

    private LocalDate dataPlantio;
    private LocalDate dataColheita;

    private String quantidadeColhida;
    private String qualidade;

    @ManyToOne
    @JoinColumn(name = "grao_id")
    private Grao grao;

    @ManyToOne
    @JoinColumn(name = "propriedade_id")
    private Propriedade propriedade;

    @ManyToOne
    @JoinColumn(name = "transporte_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Transporte transporte;
}


