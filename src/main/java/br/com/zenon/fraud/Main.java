package br.com.zenon.fraud;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "data/PS_20174392719_1491204439457_log.csv";
        List<Transaction> transactions = new TransactionIngestor().ingest(fileName);

        TransactionRepository listRepository = new TransactionListRepository(transactions);
        printSearch(listRepository, "C12345");
        printSearch(listRepository, "C1231006815");

        String worstCase = transactions.getLast().origin().name();
        System.out.println("Busca na lista por " + worstCase + ": " + measure(listRepository, worstCase) + " ns");

        TransactionRepository mapRepository = new TransactionMapRepository(transactions);
        System.out.println("Busca no mapa por " + worstCase + ": " + measure(mapRepository, worstCase) + " ns");
    }

    private static void printSearch(TransactionRepository repository, String originName) {
        Optional<Transaction> transaction = repository.findByOriginName(originName);
        if (transaction.isPresent()) {
            System.out.println(transaction.get());
        } else {
            System.out.println("Transação não encontrada para o cliente " + originName);
        }
    }

    private static long measure(TransactionRepository repository, String originName) {
        long start = System.nanoTime();
        repository.findByOriginName(originName);
        return System.nanoTime() - start;
    }
}
