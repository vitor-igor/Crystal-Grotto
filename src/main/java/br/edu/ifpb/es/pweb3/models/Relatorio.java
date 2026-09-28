package br.edu.ifpb.es.pweb3.models;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoRelatorio;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity 
@Table(name = "tb_relatorio")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Relatorio {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relatorio")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "resumo", nullable = false, columnDefinition = "TEXT")
    private String resumo;

    @Column(name = "data_submissao", nullable = false)
    private LocalDate dataSubmissao;

    @Column(name = "qtd_paginas", nullable = false)
    private Integer qtdPaginas;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_aprovacao", nullable = false)
    private SituacaoRelatorio situacaoAprovacao;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "arquivo", nullable = false)
    private byte[] arquivo;

    @Column(name = "publicacao_autorizada", nullable = false)
    private Boolean publicacaoAutorizada;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expedicao", unique = true, nullable = false)
    private Expedicao expedicao;
}
