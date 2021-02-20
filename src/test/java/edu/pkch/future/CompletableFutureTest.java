package edu.pkch.future;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class CompletableFutureTest {

    @Test
    @DisplayName("[apply] CompletableFuture의 map 함수")
    void map() throws ExecutionException, InterruptedException {
        // given
        String lastname = "GyeongCheol";
        CompletableFuture<String> nameFuture = CompletableFuture.completedFuture(lastname);

        // when
        CompletableFuture<String> fullnameFuture = nameFuture.thenApply(
                name -> "Park" + name
        );

        // then
        String fullname = fullnameFuture.get();
        assertThat(fullname).isEqualTo("ParkGyeongCheol");
    }


    @Test
    @DisplayName("[combine] CompletableFuture의 flatMap 함수")
    void flatMap() throws ExecutionException, InterruptedException {
        // given
        String lastname = "GyeongCheol";
        CompletableFuture<String> nameFuture = CompletableFuture.completedFuture(lastname);

        // when
        CompletableFuture<String> fullnameFuture = nameFuture.thenCompose(
                name -> CompletableFuture.completedFuture("Park" + name)
        );

        // then
        String fullname = fullnameFuture.get();
        assertThat(fullname).isEqualTo("ParkGyeongCheol");
    }

    @Test
    @DisplayName("CompletableFuture의 Monad 왼쪽 항등법칙 검증")
    void leftIdentity() throws ExecutionException, InterruptedException {
        // given
        String name = "Park";
        String lastname = "GyeongChoel";
        CompletableFuture<String> monad = CompletableFuture.completedFuture(name);

        // when
        Function<String, CompletableFuture<String>> f = firstName -> CompletableFuture.completedFuture(firstName + lastname);
        CompletableFuture<String> actual = monad.thenCompose(f);
        CompletableFuture<String> actual2 = f.apply(name);

        // then
        assertThat(actual.get()).isEqualTo(actual2.get());
    }

    @Test
    @DisplayName("CompletableFuture의 Monad 오른쪽 항등법칙 검증")
    void rightIdentity() throws ExecutionException, InterruptedException {
        // given
        String name = "ParkGyeongCheol";
        CompletableFuture<String> monad = CompletableFuture.completedFuture(name);

        // when
        Function<String, CompletableFuture<String>> pure = CompletableFuture::completedFuture;
        CompletableFuture<String> actual = monad.thenCompose(pure);

        // then
        assertThat(actual.get()).isEqualTo(monad.get());
    }

    @Test
    @DisplayName("CompletableFuture의 Monad 결합법칙 검증")
    void associate() throws ExecutionException, InterruptedException {
        // given
        String firstname = "Park";
        String lastname = "GyeongCheol";
        CompletableFuture<String> monad = CompletableFuture.completedFuture(firstname);
        Function<String, CompletableFuture<String>> f = name -> CompletableFuture.completedFuture(name + lastname);
        Function<String, CompletableFuture<String>> g = name -> CompletableFuture.completedFuture("Hello! " + name);

        // when
        CompletableFuture<String> actual1 = monad.thenCompose(f).thenCompose(g);
        CompletableFuture<String> actual2 = monad.thenCompose(name -> f.apply(name).thenCompose(g));

        // then
        assertThat(actual1.get()).isEqualTo(actual2.get());
    }
}
