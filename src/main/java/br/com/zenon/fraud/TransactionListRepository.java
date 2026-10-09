package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        this.transactions = List.copyOf(transactions);
    }

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        for (Transaction transaction : transactions) {
            if (transaction.origin().name().equals(originName)) {
                return Optional.of(transaction);
            }
        }
        return Optional.empty();
    }
}
