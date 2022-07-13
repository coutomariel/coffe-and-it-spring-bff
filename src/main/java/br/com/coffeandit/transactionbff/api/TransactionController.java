package br.com.coffeandit.transactionbff.api;

import br.com.coffeandit.transactionbff.domain.TransactionService;
import br.com.coffeandit.transactionbff.dto.RequestTransactionDto;
import br.com.coffeandit.transactionbff.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@Tag(name = "/transaction", description = "Grupo de apis para manipulação de transações financeiras")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "API para criar uma transação financeira")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorno Ok com transação criada."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa api."),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa api"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> enviarTransacao(@Valid @RequestBody final RequestTransactionDto requestTransactionDTO){
        requestTransactionDTO.setData(LocalDateTime.now());
        Optional<TransactionDto> transactionDto = transactionService.save(requestTransactionDTO);
        if (transactionDto.isPresent()){
            return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @Operation(description = "API para buscar as treansações persistidas por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno Ok da lista de transações."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa api."),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa api"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @GetMapping(value ="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> buscarTransacao(@PathVariable("id") final String uuid){
        Optional<TransactionDto> transactionDto = transactionService.findById(uuid);
        if (transactionDto.isPresent()){
            return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @Operation(description = "API para remoção de treansações persistidas por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Retorno Ok da lista da remoção de transações."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa api."),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa api"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @DeleteMapping(value ="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> deletarTransacao(@PathVariable("id") final String uuid){
        return Mono.empty();
    }

    @Operation(description = "API para confirmar treansações financeiras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno Ok para confirmação de uma transação financeira."),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa api."),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa api"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @PatchMapping(value ="/{id}/confirmar" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> confirmarTransacao(@PathVariable("id") final String uuid){
        return Mono.empty();
    }


}
