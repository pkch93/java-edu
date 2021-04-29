package edu.pkch.typetoken;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TypeTokenTest {

    @Test
    void typeUnsafe() throws InstantiationException, IllegalAccessException {
        // given & when
        String actual = (String) createInstance(new String());

        // then
        assertThat(actual.getClass()).isEqualTo(String.class);
    }

    @Test
    void typeUnsafeFailed() {
        assertThatThrownBy(() -> {
            Integer instance = (Integer) createInstance(new String());
        }).isInstanceOf(ClassCastException.class);
    }

    private static Object createInstance(Object obj) throws InstantiationException, IllegalAccessException {
        return obj.getClass().newInstance();
    }

    @Test
    void typeToken() throws InstantiationException, IllegalAccessException {
        // given & when
        Object objectInstance = createInstance(Object.class);
        String stringInstance = createInstance(String.class);

        // then
        assertThat(objectInstance.getClass()).isEqualTo(Object.class);
        assertThat(stringInstance.getClass()).isEqualTo(String.class);
    }

    private static <T> T createInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}