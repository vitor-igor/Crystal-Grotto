package br.edu.ifpb.es.pweb3.models;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoAtiva;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public abstract class Pessoa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_ativa", nullable = false)
    private SituacaoAtiva situacaoAtiva;

    @Embedded 
    private Endereco endereco;

    @OneToMany(
        mappedBy = "pessoa", 
        fetch = FetchType.LAZY, 
        orphanRemoval = true
    )
    private List<ParticipacaoExpedicao> participacoesExpedicoes;

}
