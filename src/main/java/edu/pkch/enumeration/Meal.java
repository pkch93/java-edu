package edu.pkch.enumeration;

import java.util.Objects;

public class Meal {
    enum Type { BREAKFAST, LUNCH, DINNER }

    private final String menu;
    private final Type type;

    public Meal(String menu, Type type) {
        this.menu = menu;
        this.type = type;
    }

    public String getMenu() {
        return menu;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(menu, meal.menu) && type == meal.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, type);
    }
}
