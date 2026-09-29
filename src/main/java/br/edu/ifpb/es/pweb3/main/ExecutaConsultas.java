package br.edu.ifpb.es.pweb3.main;

import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoExpedicao;
import br.edu.ifpb.es.pweb3.models.Coleta;
import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.ParticipacaoExpedicao;
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
            listarDetalhesExpedicoes(em, 1L);

            // Listar as coletas de uma expedição com o setor e o pesquisador responsável;
            listarColetasExpedicao(em, 1L);

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
        System.out.println("--------------------RESULTADOS--------------------");
        resultado.forEach(dto -> {
            System.out.println(dto.titulo() + " - " + dto.nomeCaverna());
        });
    }

     private static void listarDetalhesExpedicoes(EntityManager em, Long idExpedicao) {
        String jpql = """
            SELECT e
            FROM Expedicao e
            LEFT JOIN FETCH e.participacoes p
            LEFT JOIN FETCH p.pessoa pe
            WHERE e.id = :idExpedicao
        """;

        Expedicao expedicao = em.createQuery(jpql, Expedicao.class)
        .setParameter("idExpedicao", idExpedicao)
        .getSingleResult();

        System.out.println("Detalhes de participação da Expedição " + expedicao.getTitulo());
        System.out.println("--------------------RESULTADOS--------------------");

        for (ParticipacaoExpedicao p : expedicao.getParticipacoes()) {
            System.out.println(p.getPessoa().getNome() + " - " + p.getPapelDesempenhado());
        }
    }

    private static void listarColetasExpedicao(EntityManager em, Long idExpedicao) {
        String jpql = """
            SELECT e
            FROM Expedicao e
            LEFT JOIN FETCH e.coletas c
            LEFT JOIN FETCH c.pesquisador
            LEFT JOIN FETCH c.setorPesquisa
            WHERE e.id = :idExpedicao
        """;

        Expedicao expedicao = em.createQuery(jpql, Expedicao.class)
        .setParameter("idExpedicao", idExpedicao)
        .getSingleResult();

        System.out.println("Detalhes de Coletas da Expedição " + expedicao.getTitulo() + ':');
        System.out.println("--------------------RESULTADOS--------------------");

        for (Coleta c : expedicao.getColetas()) {
            System.out.println(c.getDescricaoPonto() + " - " + c.getPesquisador().getNome() + " - " + c.getSetorPesquisa().getDenominacao());
        }
    }
}
