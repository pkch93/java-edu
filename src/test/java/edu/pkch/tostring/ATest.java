package edu.pkch.tostring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class ATest {

    @Test
    void name() {
        B b = new B();
        A a = new A(b);

        System.out.println(b);
    }
}
