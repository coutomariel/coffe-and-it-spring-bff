package br.com.coffeandit.transactionbff.domain;

import br.com.coffeandit.transactionbff.dto.RequestTransactionDto;
import br.com.coffeandit.transactionbff.dto.TransactionDto;
import br.com.coffeandit.transactionbff.redis.TransactionRedisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRedisRepository transactionRedisRepository;

    public TransactionService(TransactionRedisRepository transactionRedisRepository) {
        this.transactionRedisRepository = transactionRedisRepository;
    }

    @Transactional
    public Optional<TransactionDto> save(final RequestTransactionDto requestTransactionDto){
        return Optional.of(transactionRedisRepository.save(requestTransactionDto));
    }

    public Optional<TransactionDto> findById(String uuid) {
        return Optional.of(transactionRedisRepository.findById(uuid)).get();
    }
}
