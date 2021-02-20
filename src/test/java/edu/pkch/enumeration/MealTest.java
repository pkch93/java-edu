package edu.pkch.enumeration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * EnumMap 학습용
 *
 * @see java.util.EnumMap
 */
class MealTest {

    @Test
    void enumMap() {
        // given
        List<Meal> eaten = Arrays.asList(
                new Meal("토스트", Meal.Type.BREAKFAST),
                new Meal("참치김밥", Meal.Type.LUNCH),
                new Meal("떡볶이", Meal.Type.LUNCH),
                new Meal("모듬초밥", Meal.Type.DINNER)
        );

        // when
        Map<Meal.Type, List<Meal>> meals = new EnumMap<>(
                eaten.stream().collect(Collectors.groupingBy(Meal::getType))
        );

        // then
        assertThat(meals).hasSize(3);
        assertThat(meals.get(Meal.Type.BREAKFAST)).hasSize(1)
                .containsOnly(new Meal("토스트", Meal.Type.BREAKFAST));
        assertThat(meals.get(Meal.Type.LUNCH)).hasSize(2)
                .containsOnly(new Meal("참치김밥", Meal.Type.LUNCH), new Meal("떡볶이", Meal.Type.LUNCH));
        assertThat(meals.get(Meal.Type.DINNER)).hasSize(1)
                .containsOnly(new Meal("모듬초밥", Meal.Type.DINNER));
    }

    @Test
    void enumMap2() {
        // given
        List<Meal> eaten = Arrays.asList(
                new Meal("토스트", Meal.Type.BREAKFAST),
                new Meal("참치김밥", Meal.Type.LUNCH),
                new Meal("떡볶이", Meal.Type.LUNCH),
                new Meal("모듬초밥", Meal.Type.DINNER)
        );

        // when
        EnumMap<Meal.Type, List<Meal>> meals = eaten.stream()
                .collect(Collectors.groupingBy(Meal::getType, () -> new EnumMap<>(Meal.Type.class), Collectors.toList()));

        // then
        assertThat(meals).hasSize(3);
        assertThat(meals.get(Meal.Type.BREAKFAST)).hasSize(1)
                .containsOnly(new Meal("토스트", Meal.Type.BREAKFAST));
        assertThat(meals.get(Meal.Type.LUNCH)).hasSize(2)
                .containsOnly(new Meal("참치김밥", Meal.Type.LUNCH), new Meal("떡볶이", Meal.Type.LUNCH));
        assertThat(meals.get(Meal.Type.DINNER)).hasSize(1)
                .containsOnly(new Meal("모듬초밥", Meal.Type.DINNER));
    }
}