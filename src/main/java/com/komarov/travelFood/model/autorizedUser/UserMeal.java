package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Никита on 11.10.2017.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET_ALL, query = "SELECT m FROM UserMeal m WHERE m.user.id=:userId")
})
@Entity
@Table(name = "USER_MEALS")
public class UserMeal extends NamedEntity {
    public static final String DELETE = "UserMeal.delete";
    public static final String GET_ALL = "UserMeal.getAll";

    @Column(name = "calories", nullable = false)
    @Range(min = 10, max = 5000)
    private int calories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public UserMeal() {
    }

    public UserMeal(int id, String name, int calories, User user) {
        super(id, name);
        this.calories = calories;
        this.user = user;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
