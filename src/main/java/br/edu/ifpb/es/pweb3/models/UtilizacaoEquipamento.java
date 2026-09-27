package br.edu.ifpb.es.pweb3.models;

import br.edu.ifpb.es.pweb3.models.enums.EstadoEquipamento;
import jakarta.persistence.Column;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_utilizacao_equipamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilizacaoEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizacao")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expedicao", nullable = false)
    private Expedicao expedicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipamento", nullable = false)
    private Equipamento equipamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa_retirada", nullable = false)
    private Pessoa pessoaRetirada;

    @Column(name = "data_hora_retirada", nullable = false)
    private LocalDateTime dataHoraRetirada;

    @Column(name = "previsao_devolucao", nullable = false)
    private LocalDateTime previsaoDevolucao;

    @Column(name = "data_hora_efetiva_devolucao")
    private LocalDateTime dataHoraEfetivaDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_saida", nullable = false)
    private EstadoEquipamento estadoSaida;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_retorno")
    private EstadoEquipamento estadoRetorno;

    @Column(name = "custo_avaria", precision = 10, scale = 2)
    private BigDecimal custoAvaria;
    
}