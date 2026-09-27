package br.edu.ifpb.es.pweb3.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoExpedicao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tb_expedicao")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Expedicao {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expedicao")
    private Long id;

    @Column(name = "cod_expedicao", nullable = false, unique = true)
    private Long codExpedicao;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "objetivo", columnDefinition = "TEXT")
    private String objetivo;

    @Column(name = "data_prevista_inicio", nullable = false)
    private LocalDateTime dataPrevistaInicio;

    @Column(name = "data_prevista_termino", nullable = false)
    private LocalDateTime dataPrevistaTermino;

    @Column(name = "orcamento_aprovado", precision = 10, scale = 2, nullable = false)
    private BigDecimal orcamentoAprovado;

    @Column(name = "custo_realizado", precision = 10, scale = 2)
    private BigDecimal custoRealizado;

    @Column(
        name = "qtd_maxima_participantes",
        columnDefinition = "INTEGER CHECK (qtd_maxima_participantes > 0)",
        nullable = false
    )
    private Integer qtdMaximaParticipantes;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private SituacaoExpedicao situacao;

    @Column(name = "cancelamentoEmergencial", nullable = false)
    private Boolean cancelamentoEmergencial;

    @ManyToOne 
    @JoinColumn(name = "caverna_id", nullable = false)
    private Caverna caverna;

    @ManyToMany
    @JoinTable(
        name = "tb_expedicao_setor",
        joinColumns = @JoinColumn(name = "id_expedicao"),
        inverseJoinColumns = @JoinColumn(name = "id_setor_pesquisa")
    )
    private Set<SetorPesquisa> setoresPesquisa;

    @OneToMany(
        mappedBy = "expedicao",
        fetch = FetchType.LAZY
    )
    private AutorizacaoAmbiental autorizacaoAmbiental;

    @OneToOne(
        mappedBy = "expedicao",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private PlanoSeguranca planoSeguranca;

}
