package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.BaseEntity;
import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.model.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Никита on 11.10.2017.
 */
@NamedQueries({
        @NamedQuery(name = UserMealWithWeight.DELETE, query = "DELETE FROM UserMealWithWeight m WHERE m.id=:id AND m.day.id=:dayId"),
        @NamedQuery(name = UserMealWithWeight.GET_ALL, query = "SELECT m FROM UserMealWithWeight m WHERE m.day.id=:dayId AND m.menuName=:menuName")
})
@Entity
@Table(name = "MEALS_WITH_WEIGHT")
public class UserMealWithWeight extends BaseEntity {
    public static final String DELETE = "UserMealWithWeight.delete";
    public static final String GET_ALL = "UserMealWithWeight.getAll";

    @Column(name = "weight")
    private int weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Day day;

    @Column(name = "menu_name")
    private String menuName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private UserMeal meal;

    public UserMealWithWeight() {
    }

    public UserMealWithWeight(int id, int weight, String menuName, UserMeal meal, Day day) {
        super(id);
        this.weight = weight;
        this.menuName = menuName;
        this.meal = meal;
        this.day = day;
    }

    public UserMeal getMeal() {
        return meal;
    }

    public void setMeal(UserMeal meal) {
        this.meal = meal;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "UserMealWithWeight{" +
                "weight=" + weight +
                ", day=" + day +
                ", menuName=" + menuName +
                ", meal=" + meal +
                '}';
    }
}
