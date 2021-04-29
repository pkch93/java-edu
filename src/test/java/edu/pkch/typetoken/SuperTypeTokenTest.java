package edu.pkch.typetoken;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SuperTypeTokenTest {

    @Test
    void superTypeToken() {
        // given
        Favorites favorites = new Favorites();

        favorites.putFavorite(new SuperTypeToken<List<String>>(){}, Arrays.asList("딸기", "수박", "멜론"));
        favorites.putFavorite(new SuperTypeToken<List<Integer>>(){}, Arrays.asList(1, 3, 9));

        // when
        List<String> favoriteFruits = favorites.getFavorite(new SuperTypeToken<List<String>>(){});
        List<Integer> favoriteNumbers = favorites.getFavorite(new SuperTypeToken<List<Integer>>(){});

        // then
        assertThat(favoriteFruits).hasSize(3).containsExactly("딸기", "수박", "멜론");
        assertThat(favoriteNumbers).hasSize(3).containsExactly(1, 3, 9);
    }

    static class Favorites {
        private Map<SuperTypeToken<?>, Object> favorites = new HashMap<>();

        public <T> void putFavorite(SuperTypeToken<T> type, T instance) {
            favorites.put(Objects.requireNonNull(type), instance);
        }

        @SuppressWarnings("unchecked")
        public <T> T getFavorite(SuperTypeToken<T> superTypeToken) {
            if (superTypeToken.type instanceof Class<?>) {
                return ((Class<T>) superTypeToken.type).cast(favorites.get(superTypeToken));
            } else {
                return ((Class<T>)((ParameterizedType) superTypeToken.type).getRawType()).cast(favorites.get(superTypeToken));
            }
        }
    }

    static class SuperTypeToken<T> {
        Type type;

        public SuperTypeToken() {
            Type sType = getClass().getGenericSuperclass();
            if (sType instanceof ParameterizedType) {
                this.type = ((ParameterizedType) sType).getActualTypeArguments()[0];
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;
            SuperTypeToken<?> that = (SuperTypeToken<?>) o;
            return Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }
    }
}
