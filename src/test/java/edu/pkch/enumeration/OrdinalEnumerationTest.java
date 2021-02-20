package edu.pkch.enumeration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrdinalEnumerationTest {

    @Test
    void ordinal() {
        assertThat(OrdinalEnumeration.ZERO.ordinal()).isEqualTo(0);
        assertThat(OrdinalEnumeration.ONE.ordinal()).isEqualTo(1);
        assertThat(OrdinalEnumeration.TWO.ordinal()).isEqualTo(2);
        assertThat(OrdinalEnumeration.THREE.ordinal()).isEqualTo(3);
        assertThat(OrdinalEnumeration.FOUR.ordinal()).isEqualTo(4);
        assertThat(OrdinalEnumeration.FIVE.ordinal()).isEqualTo(5);
    }
}