package br.edu.ifpb.es.pweb3.models;

import br.edu.ifpb.es.pweb3.models.enums.TipoEquipamento;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoEquipamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity 
@Table(name = "tb_equipamento")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Equipamento {
        
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamento")
    private Long id;
    
    @Column(name = "cod_patrimonial", nullable = false, unique = true)
    private Long codPatrimonial;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEquipamento tipo;

    @Column(name = "fabricante", nullable = false, length = 150)
    private String fabricante;

    @Column(name = "valor_aquisicao", precision = 10, scale = 2,  nullable = false)
    private BigDecimal valorAquisicao;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column(name = "data_ultima_manutencao", nullable = false)
    private LocalDate dataUltimaManutencao;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_operacional", nullable = false)
    private SituacaoEquipamento situacaoOperacional;

    @Column(name = "exige_calibracao", nullable = false)
    private Boolean exigeCalibracao;

    @OneToMany(
        mappedBy = "equipamento", 
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<UtilizacaoEquipamento> historicoUtilizacoes;
}
