package br.edu.ifpb.es.pweb3.models;

import br.edu.ifpb.es.pweb3.models.enums.PapelExpedicao;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "tb_participacao_expedicao",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_pessoa_expedicao",
            columnNames = {"id_pessoa", "id_expedicao"}
        )
    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipacaoExpedicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participacao_expedicao")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel_desempenhado", nullable = false)
    private PapelExpedicao papelDesempenhado;

    @Column(name = "data_confirmacao", nullable = false)
    private LocalDate dataConfirmacao;

    @Column(name = "valor_diaria", precision = 10, scale = 2,  nullable = false)
    private BigDecimal valorDiaria;

    @Column(
            name = "qtd_dias_previstos",
            columnDefinition = "INTEGER CHECK (qtd_dias_previstos > 0)",
            nullable = false
    )
    private Integer qtdDiasPrevistos;

    @Column(name = "presenca_confirmada", nullable = false)
    private Boolean presencaConfirmada;

    @ElementCollection
    @CollectionTable(
            name = "tb_participacao_expedicao_observacoes",
            joinColumns = @JoinColumn(name = "id_participacao_expedicao")
    )
    @Column(name = "observacao")
    private List<String> observacoes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_expedicao", nullable = false)
    private Expedicao expedicao;

}
