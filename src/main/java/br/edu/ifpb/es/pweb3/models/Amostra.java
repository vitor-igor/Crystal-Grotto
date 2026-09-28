package br.edu.ifpb.es.pweb3.models;

import br.edu.ifpb.es.pweb3.models.enums.CategoriaAmostra;
import br.edu.ifpb.es.pweb3.models.enums.UnidadeMedida;
import br.edu.ifpb.es.pweb3.models.enums.CondicaoPreservacao;
import jakarta.persistence.Basic;
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
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "tb_amostra")
@org.hibernate.annotations.Check(constraints = "massa IS NOT NULL OR volume IS NOT NULL")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Amostra {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amostra")
    private Long id;

    @Column(name = "cod_amostra", unique = true, nullable = false)
    private Long codAmostra;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_amostra", nullable = false)
    private CategoriaAmostra categoriaAmostra;

    @Column(name = "massa", precision = 8, scale = 3)
    private BigDecimal massa;

    @Column(name = "volume", precision = 8, scale = 3)
    private BigDecimal volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(name = "data_acondicionamento", nullable = false)
    private LocalDateTime dataAcondicionamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_preservacao", nullable = false)
    private CondicaoPreservacao condicaoPreservacao;

    @Column(name = "material_perigoso", nullable = false)
    private Boolean materialPerigoso;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "fotografia", nullable = false)
    private byte[] fotografia;

    @ElementCollection 
    @CollectionTable(
        name = "tb_amostras_observacoes",
        joinColumns = @JoinColumn(name = "id_amostra")
    )
    @Column(name = "observacao")
    private List<String> observacoes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coleta", nullable = false)
    private Coleta coleta;
    
}