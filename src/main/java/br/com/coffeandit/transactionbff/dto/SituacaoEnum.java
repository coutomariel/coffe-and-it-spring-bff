package br.com.coffeandit.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Situações possíveis para uma transação")
public enum SituacaoEnum {
    ANALISADA,
    NAO_ANALISADA,
    EM_ANALISE_HUMANA,
    EM_ANALISE_SUSPEITA,
    RISCO_CONFIRMADO;
}
