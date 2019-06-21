package com.dataart.monads.future;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AsyncFutureServiceImpl {


    public static CompletableFuture<Integer> handleString(CompletableFuture<String> future) {
        return future.thenApply(s -> Integer.parseInt(s) * 2);
    }

    public static Integer handleException(CompletableFuture<String> future) throws ExecutionException, InterruptedException {
        return future.thenApply(s -> Integer.parseInt(s)).exceptionally(ex -> -1).get();
    }

    public static CompletableFuture<List<Worker>> handleFutures(CompletableFuture<Company> companyCompletableFuture, CompletableFuture<Profession> professionCompletableFuture) throws ExecutionException, InterruptedException {
        return companyCompletableFuture.thenCombine(professionCompletableFuture, (company, profession) ->
                company.getWorkers()
                        .flatMap(workers -> Optional.of(workers.stream()
                                .filter(worker -> profession.equals(worker.getProfession()))
                                .collect(Collectors.toList()))).get());
    }
}
