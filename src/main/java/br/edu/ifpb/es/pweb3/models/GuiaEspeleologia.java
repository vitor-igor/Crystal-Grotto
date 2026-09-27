package br.edu.ifpb.es.pweb3.models;

import java.time.LocalDate;

import br.edu.ifpb.es.pweb3.models.enums.NivelCertificacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tb_guia_espeleologia")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class GuiaEspeleologia extends Pessoa {

    @Column(name = "num_credenciamento", unique = true)
    private Long numCredenciamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_certificacao", nullable = false)
    private NivelCertificacao nivelCertificacao; 

    @Column(name = "data_validade_certificacao")
    private LocalDate dataValidadeCertificacao;

    @Column(name = "qtd_expedicoes_concluidas")
    private Integer qtdExpedicoesConcluidas;
}
