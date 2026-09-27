package br.edu.ifpb.es.pweb3.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoAutorizacaoAmbiental;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tb_autorizacao_ambiental")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class AutorizacaoAmbiental {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autorizacao_ambiental")
    private Long id;

    @Column(name = "numero", nullable = false, unique = true)
    private Long numero;

    @Column(name = "orgao_emissor", nullable = false, length = 150)
    private String orgaoEmissor;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private SituacaoAutorizacaoAmbiental situacao;

    @ElementCollection 
    @CollectionTable(
        name = "tb_autorizacao_ambiental_observacoes",
        joinColumns = @JoinColumn(name = "id_autorizacao_ambiental")
    )
    @Column(name = "observacao")
    private List<String> observacoes = new ArrayList<>();

    @Basic(fetch = FetchType.LAZY)
    @Lob 
    @Column(name = "arq_pdf_assinado")
    private byte[] arqPDFAssinado;

    @ManyToOne
    @JoinColumn(name = "id_expedicao", nullable = false)
    private Expedicao expedicao;

}
