package br.edu.ifpb.es.pweb3.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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

@Entity 
@Table(name = "tb_caverna")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Caverna {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caverna")
    private Long id;

    @Column(name = "nome_oficial", nullable = false, length = 150)
    private String nomeOficial;

    @Column(name = "cod_cadastro_ambiental", nullable = false, unique = true)
    private Long codCadastroAmbiental;

    @Column(name = "municipio", nullable = false, length = 100)
    private String municipio;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf; 

    @Embedded 
    private CoordenadaGeografica coordenadaGeografica;

    @Column(name = "altitude", precision = 8, scale = 2, nullable = false)
    private BigDecimal altitude;

    @Column(name = "extensao_conhecida", precision = 10, scale = 2, nullable = false)
    private BigDecimal extensaoConhecida;

    @Column(name = "data_ultima_inspecao", nullable = false)
    private LocalDate dataUltimaInspecao;

    @Column(name = "acesso_permitido", nullable = false)
    private Boolean acessoPermitido;

    @OneToMany(
        mappedBy = "caverna",
        fetch = FetchType.LAZY
    )
    private Set<SetorPesquisa> setoresPesquisa = new HashSet<>();
    
    @OneToMany(
        mappedBy = "caverna",
        fetch = FetchType.LAZY
    )
    private Set<Expedicao> expedicoes = new HashSet<>();
    
}
