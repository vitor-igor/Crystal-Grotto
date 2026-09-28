package br.edu.ifpb.es.pweb3.main;

import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoExpedicao;
import br.edu.ifpb.es.pweb3.models.ExpedicaoResumoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ExecutaConsultas {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crystalPU");
        EntityManager em = emf.createEntityManager();
        try {
            // Listar expedições por período e situação, exibindo apenas código, título, caverna, datas e situação;
            listarExpedicoesPorPeriodo(em, LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2027, 12, 31, 23, 59), SituacaoExpedicao.PLANEJADA);

            // Carregar os detalhes de uma expedição selecionada, incluindo participantes e seus papéis, sem buscar arquivos binários;

        } finally {
            if (em.isOpen()) {
                em.close();
            }
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }

    private static void listarExpedicoesPorPeriodo(EntityManager em, LocalDateTime dataInicio, LocalDateTime dataFim, SituacaoExpedicao situacao) {
        String jpql = """
            SELECT new br.edu.ifpb.es.pweb3.models.ExpedicaoResumoDTO(
                e.codExpedicao, e.titulo, c.nomeOficial, e.dataPrevistaInicio, e.situacao
            )
            FROM Expedicao e 
            JOIN e.caverna c
            WHERE e.dataPrevistaInicio >= :inicio 
              AND e.dataPrevistaTermino <= :fim 
              AND e.situacao = :sit
        """;
        
        List<ExpedicaoResumoDTO> resultado = em.createQuery(jpql, ExpedicaoResumoDTO.class)
        .setParameter("inicio", dataInicio)
        .setParameter("fim", dataFim)
        .setParameter("sit", situacao)
        .getResultList();

        System.out.println("Expedições entre " + dataInicio + " e " + dataFim);
        resultado.forEach(dto -> {
            System.out.println("Expedição: " + dto.titulo() + " | Caverna: " + dto.nomeCaverna());
        });
    }
}