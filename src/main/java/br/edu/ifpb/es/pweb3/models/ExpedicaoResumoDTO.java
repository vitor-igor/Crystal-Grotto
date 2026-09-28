package br.edu.ifpb.es.pweb3.models;

import java.time.LocalDateTime;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoExpedicao;

public record ExpedicaoResumoDTO(
    Long codExpedicao,
    String titulo,
    String nomeCaverna,
    LocalDateTime dataPrevistaInicio,
    SituacaoExpedicao situacao
) {}