package br.edu.ifpb.es.pweb3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Endereco {
    
    @Column(name = "logradouro", nullable = false, length = 150)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "complemento", length = 50)
    private String complemento;

    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf; 

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;
    
}
