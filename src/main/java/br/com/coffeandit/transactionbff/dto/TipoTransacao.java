package br.com.coffeandit.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tipo de uma transação")
public enum TipoTransacao {
    PAGAMENTO_TRIBUTOS,
    PAGAMENTO_TITULOS,
    TED,
    DOC
}
