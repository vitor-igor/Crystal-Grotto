package br.edu.ifpb.es.pweb3.models;

import java.math.BigDecimal;

import br.edu.ifpb.es.pweb3.models.enums.Titulacao;
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
@Table(name = "tb_pesquisador")
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Pesquisador extends Pessoa {

    @Column(name = "num_registro_institucional", unique = true)
    private Long numRegistroInstitucional;

    @Column(name = "area_pesquisa", length = 100)
    private String areaPesquisa;

    @Enumerated(EnumType.STRING)
    @Column(name = "titulacao", nullable = false)
    private Titulacao titulacao;

    @Column(name = "valor_diario_bolsa", precision = 10, scale = 2)
    private BigDecimal valorDiarioBolsa;
}
