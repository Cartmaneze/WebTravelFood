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
public class Meal extends BaseEntity {
    public static final String DELETE = "Meal.delete";
    public static final String GET_ALL = "Meal.getAll";

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "calories")
    private int calories;

    public Meal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
