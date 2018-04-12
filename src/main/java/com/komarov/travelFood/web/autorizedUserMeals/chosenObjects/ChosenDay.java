package com.komarov.travelFood.web.autorizedUserMeals.chosenObjects;

import com.komarov.travelFood.model.autorizedUser.Day;
import org.springframework.stereotype.Component;

/**
 * Created by Никита on 27.11.2017.
 */
@Component
public class ChosenDay {
    private Day day;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
