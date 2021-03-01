package edu.pkch.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {

    @Test
    @DisplayName("[Stream] Stream은 Monad의 flatMap이 있다.")
    void flatMap() {
        // given
        List<List<String>> strs = Arrays.asList(
                Arrays.asList("1", "2", "3"),
                Arrays.asList("4", "5", "6"),
                Arrays.asList("7", "8", "9", "10")
        );

        // when
        String actual = strs.stream()
                .flatMap(Collection::stream)
                .map(str -> str + " ")
                .collect(Collectors.joining());

        // then
        assertThat(actual).isEqualTo("1 2 3 4 5 6 7 8 9 10 ");
    }

    @Test
    @DisplayName("[IntStream] Stream은 Monad의 flatMap이 있다.")
    void flatMap2() {
        // given
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5, 6);
        List<Integer> c = Arrays.asList(7, 8, 9, 10);

        // when
        int actual = Stream.of(a.stream(), b.stream(), c.stream())
                .flatMapToInt(stream -> stream.mapToInt(Integer::intValue))
                .reduce(0, Integer::sum);

        // then
        assertThat(actual).isEqualTo(55);
    }

    @Test
    void foreach_stream() {
        // given
        Stream<Integer> numberStream = Stream.of(1, 2, 3);

        // when
        for (int number: (Iterable<Integer>) numberStream::iterator) {
            assertThat(number <= 3).isEqualTo(true);
        }
    }

    @Test
    void foreach_stream_adaptor() {
        // given
        Stream<Integer> numberStream = Stream.of(1, 2, 3);

        // when
        for (int number: getIterable(numberStream)) {
            assertThat(number <= 3).isEqualTo(true);
        }
    }

    private static <T> Iterable<T> getIterable(Stream<T> stream) {
        return stream::iterator;
    }

    @Test
    void stream_without_terminal_operation() {
        // given
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        // when
        stream.filter(number -> number < 3)
                .map(number -> {
                    System.out.println(number);
                    return number * 2;
                });
    }

    @Test
    void stream_with_terminal_operation() {
        // given
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        // when
        int actual = stream.filter(number -> number < 3)
                .map(number -> {
                    System.out.println(number);
                    return number * 2;
                })
                .reduce(Integer::sum)
                .get();

        // then
        assertThat(actual).isEqualTo(6);
    }

    @Test
    void non_directive() {
        // given
        List<Employee> employees = Arrays.asList(
                new Employee(Employee.Rank.CEO, 100_000_000),
                new Employee(Employee.Rank.MANAGER, 50_000_000),
                new Employee(Employee.Rank.STAFF, 30_000_000)
        );

        // when
        for (Employee employee : employees) {
            System.out.printf("%.2f\n", employee.calculateAfterTaxSalary());
        }
    }

    @Test
    void directive() {
        // given
        List<Employee> employees = Arrays.asList(
                new Employee(Employee.Rank.CEO, 100_000_000),
                new Employee(Employee.Rank.MANAGER, 50_000_000),
                new Employee(Employee.Rank.STAFF, 30_000_000)
        );

        // when
        employees.stream()
                .map(Employee::calculateAfterTaxSalary)
                .forEach(this::printSalary);
    }

    private void printSalary(double salary) {
        System.out.printf("%.2f\n", salary);
    }

    public static class Employee {
        private Rank rank;
        private long salary;

        public Employee(Rank rank, long salary) {
            this.rank = rank;
            this.salary = salary;
        }

        public double calculateAfterTaxSalary() {
            return salary - rank.calculateIncomeTax(salary);
        }

        enum Rank {
            CEO(18.0),
            MANAGER(12.5),
            STAFF(7.5);

            private final double taxRate;

            Rank(double taxRate) {
                this.taxRate = taxRate;
            }

            public double calculateIncomeTax(long salary) {
                return salary * this.taxRate / 100;
            }
        }
    }
}
