package br.edu.ifpb.es.pweb3.main;

import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.enums.SituacaoExpedicao;
import br.edu.ifpb.es.pweb3.models.Amostra;
import br.edu.ifpb.es.pweb3.models.Coleta;
import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.ParticipacaoExpedicao;
import br.edu.ifpb.es.pweb3.models.ExpedicaoResumoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

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

            // Obter as amostras de uma coleta apenas quando o usuário abrir os seus detalhes;
            listarAmostrasColeta(em, 1L);

            // Consultar equipamentos disponíveis em determinada faixa de datas sem carregar todo o histórico de movimentações;

            // Baixar separadamente o mapa de segurança, a autorização ambiental ou o relatório final.
            baixarMapaSeguranca(em, 1L);
            baixarAutorizacaoAmbiental(em, 1L);
            baixarRelatorio(em, 1L);


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
            System.out.println(dto.codExpedicao() + " - " + dto.titulo() + " - " + dto.nomeCaverna() + " - " + dto.dataPrevistaInicio() + " - " + dto.situacao());
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

        try{
            Expedicao expedicao = em.createQuery(jpql, Expedicao.class)
            .setParameter("idExpedicao", idExpedicao)
            .getSingleResult();
    
            System.out.println("Detalhes de participação da Expedição: " + expedicao.getCodExpedicao() + " - " + expedicao.getTitulo() + " - " + expedicao.getObjetivo() + " - Início: " + expedicao.getDataPrevistaInicio() + "  - Término: " + expedicao.getDataPrevistaTermino() + " - " + expedicao.getOrcamentoAprovado());
            System.out.println("--------------------PARTICIPANTES--------------------");
    
            for (ParticipacaoExpedicao p : expedicao.getParticipacoes()) {
                System.out.println(p.getPessoa().getNome() + " - " + p.getPapelDesempenhado());
            }
        }catch (NoResultException e){
            System.out.println("Nenhuma expedição encontrada para a expedição ID: " + idExpedicao);
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

        try{
            Expedicao expedicao = em.createQuery(jpql, Expedicao.class)
            .setParameter("idExpedicao", idExpedicao)
            .getSingleResult();
    
            System.out.println("Detalhes de Coletas da Expedição " +  expedicao.getTitulo() + ':');
            System.out.println("--------------------RESULTADOS--------------------");
    
            for (Coleta c : expedicao.getColetas()) {
                System.out.println(c.getDescricaoPonto() + " - " + c.getPesquisador().getNome() + " - " + c.getSetorPesquisa().getDenominacao());
            }
        }catch (NoResultException e){
            System.out.println("Nenhuma expedição encontrada para a expedição ID: " + idExpedicao);
        }
    }

    private static void listarAmostrasColeta(EntityManager em, Long idColeta) {
        String jpql = """
            SELECT c
            FROM Coleta c
            LEFT JOIN FETCH c.amostras a
            WHERE c.id = :idColeta
        """;

        try{
            Coleta coleta = em.createQuery(jpql, Coleta.class)
            .setParameter("idColeta", idColeta)
            .getSingleResult();
    
            System.out.println("Amostras da coleta: " + coleta.getDescricaoPonto() + ':');
            System.out.println("--------------------RESULTADOS--------------------");
    
            for (Amostra a : coleta.getAmostras()) {
                
                System.out.println("Código: " + a.getCodAmostra() + " - " + a.getCategoriaAmostra() + " - " + a.getCondicaoPreservacao() + " - " + "Perigoso = " + a.getMaterialPerigoso());
            }
        }catch (NoResultException e){
            System.out.println("Nenhuma coleta encontrada para a coleta ID: " + idColeta);
        }
    }

    private static void baixarMapaSeguranca(EntityManager em, Long idExpedicao) {
        String jpql = """
            SELECT p.mapaRota
            FROM PlanoSeguranca p
            WHERE p.expedicao.id = :idExpedicao
        """;
        
        try{
            byte[] mapa = em.createQuery(jpql, byte[].class)
            .setParameter("idExpedicao", idExpedicao)
            .getSingleResult();
                            
            System.out.println("Tamanho do mapa baixado: " + mapa.length + " bytes");
        }catch (NoResultException e){
            System.out.println("Nenhum mapa encontrado para a expedição ID: " + idExpedicao);
        }
    }

    private static void baixarAutorizacaoAmbiental(EntityManager em, Long idAutorizacao) {
        String jpql = """
            SELECT a.arqPDFAssinado
            FROM AutorizacaoAmbiental a
            WHERE a.id = :idAutorizacao
        """;
        
        try{
            byte[] arquivo = em.createQuery(jpql, byte[].class)
                            .setParameter("idAutorizacao", idAutorizacao)
                            .getSingleResult();
            
            System.out.println("Tamanho da autorização baixada: " + arquivo.length + " bytes");
        }catch (NoResultException e){
            System.out.println("Nenhuma autorização ambiental encontrada para a autorização ID: " + idAutorizacao);
        }
    }

    private static void baixarRelatorio(EntityManager em, Long idExpedicao) {
        String jpql = """
            SELECT r.arquivo
            FROM Relatorio r
            WHERE r.expedicao.id = :idExpedicao
        """;
        
        try{
            byte[] arquivo = em.createQuery(jpql, byte[].class)
                            .setParameter("idExpedicao", idExpedicao)
                            .getSingleResult();
            
            System.out.println("Tamanho do relatório baixado: " + arquivo.length + " bytes");
        }catch (NoResultException e){
            System.out.println("Nenhum relatório encontrado para o expedição ID: " + idExpedicao);
        }
    }
}
