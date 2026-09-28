package br.edu.ifpb.es.pweb3.models;

import br.edu.ifpb.es.pweb3.models.enums.MetodoColeta;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoColeta;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity 
@Table(name = "tb_coleta")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Coleta {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coleta")
    private Long id;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_coleta", nullable = false)
    private MetodoColeta metodoColeta;

    @Column(name = "descricao_ponto", columnDefinition = "TEXT", nullable = false)
    private String descricaoPonto;

    @Column(name = "temperatura", precision = 5, scale = 2, nullable = false)
    private BigDecimal temperatura;

    @Column(name = "umidade_relativa", precision = 5, scale = 2, nullable = false)
    private BigDecimal umidadeRelativa;

    @Column(name = "profundidade", precision = 8, scale = 3, nullable = false)
    private BigDecimal profundidade;

    @ElementCollection 
    @CollectionTable(
        name = "tb_coletas_observacoes",
        joinColumns = @JoinColumn(name = "id_coleta")
    )
    @Column(name = "observacao")
    private List<String> observacoes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_coleta", nullable = false)
    private SituacaoColeta situacaoColeta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_setor_pesquisa", nullable = false)
    private SetorPesquisa setorPesquisa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pesquisador", nullable = false)
    private Pesquisador pesquisador;

    @OneToMany(
        mappedBy = "coleta",
        fetch = FetchType.LAZY
    )
    private Set<Amostra> amostras = new HashSet<>();

}