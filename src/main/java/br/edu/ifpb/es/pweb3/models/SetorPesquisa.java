package br.edu.ifpb.es.pweb3.models;

import java.math.BigDecimal;

import br.edu.ifpb.es.pweb3.models.enums.NivelDificuldade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_setor_pesquisa")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class SetorPesquisa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_setor_pesquisa")
    private Long id;

    @Column(name = "denominacao", nullable = false, length = 100)
    private String denominacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_dificuldade", nullable = false)
    private NivelDificuldade nivelDificuldade;

    @Column(name = "profundidade_maxima", precision = 10, scale = 2)
    private BigDecimal profundidadeMaxima;

    @Column(name = "extensao_aproximada", precision = 10, scale = 2)
    private BigDecimal extensaoAproximada;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "risco_inundacao", precision = 5, scale = 2)
    private BigDecimal riscoInundacao;

    @Column(name = "condicao_corrente", length = 100)
    private String condicaoCorrente;

    @ManyToOne 
    @JoinColumn(name = "caverna_id", nullable = false)
    private Caverna caverna;
}