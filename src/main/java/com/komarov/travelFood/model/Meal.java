package com.komarov.travelFood.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by Никита on 09.05.2017.
 */
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id"),
        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM Meal m")
})
@Entity
@Table(name = "MEALS")
public class Meal extends NamedEntity {
    public static final String DELETE = "Meal.delete";
    public static final String GET_ALL = "Meal.getAll";

    @Column(name = "calories")
    private int calories;

    public Meal() {
    }

    public Meal(int id, String name, int calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
