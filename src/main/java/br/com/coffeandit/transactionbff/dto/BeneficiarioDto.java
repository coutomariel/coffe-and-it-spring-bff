package br.com.coffeandit.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class BeneficiarioDto {

    @Schema(description = "CPF do beneficiario")
    @NotNull(message = "Informar nome do beneficiario")
    private Long cpf;

    @Schema(description = "Código do banco de destino")
    @NotNull(message = "Informar código do banco de destino")
    private Long codigoBanco;

    @Schema(description = "Agência de destino")
    @NotNull(message = "Informar agência de destino")
    private String agencia;

    @Schema(description = "Conta de destino")
    @NotNull(message = "Informar conta de destino")
    private String conta;

    @Schema(description = "Nome do favorecido")
    @NotNull(message = "Informar nome do favorecido")
    private String nomeFavorecido;

}
