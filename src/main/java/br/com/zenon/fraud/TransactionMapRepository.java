package br.com.zenon.fraud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> byOriginName;

    public TransactionMapRepository(List<Transaction> transactions) {
        Map<String, Transaction> index = new HashMap<>();
        for (Transaction transaction : transactions) {
            index.putIfAbsent(transaction.origin().name(), transaction);
        }
        this.byOriginName = Map.copyOf(index);
    }

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        return Optional.ofNullable(byOriginName.get(originName));
    }
}
