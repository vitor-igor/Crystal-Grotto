package br.edu.ifpb.es.pweb3.models;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tb_plano_seguranca")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class PlanoSeguranca {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano_seguranca")
    private Long id;

    @ElementCollection 
    @CollectionTable(
        name = "tb_plano_seguranca_procedimentos_evacuacao",
        joinColumns = @JoinColumn(name = "id_plano_seguranca")
    )
    @Column(name = "procedimento_evacuacao")
    private List<String> procedimentosEvacuacao;

    @Column(name = "ponto_externo_encontro", nullable = false, length = 200)
    private String pontoExternoEncontro;

    @Column(name = "tempo_maximo_sem_comunicacao", nullable = false)
    private Integer tempoMaximoSemComunicacao;

    @Column(name = "telefone_emergencia", nullable = false, length = 20)
    private String telefoneEmergencia;

    @Column(name = "necessita_equipe_medica", nullable = false)
    private Boolean necessitaEquipeMedica;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "mapa_rota", nullable = false)
    private byte[] mapaRota;

    @OneToOne
    @JoinColumn(
        name = "expedicao_id",
        nullable = false,
        unique = true
    )
    private Expedicao expedicao;

}
