package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.NamedEntity;

import javax.persistence.Table;

/**
 * Created by Никита on 11.10.2017.
 */
//@NamedQueries({
//        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
//        @NamedQuery(name = UserMeal.GET_ALL, query = "SELECT m FROM UserMeal m WHERE m.user.id=:userId")
//})
//@Entity
@Table(name = "USER_MEALS")
public class UserMeal extends NamedEntity {
    public static final String DELETE = "Meal.delete";
    public static final String GET_ALL = "Meal.getAll";
}
