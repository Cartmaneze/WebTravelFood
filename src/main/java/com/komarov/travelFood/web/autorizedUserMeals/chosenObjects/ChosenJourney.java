package com.komarov.travelFood.web.autorizedUserMeals.chosenObjects;

import com.komarov.travelFood.model.autorizedUser.Journey;
import org.springframework.stereotype.Component;

/**
 * Created by Никита on 27.11.2017.
 */
@Component
public class ChosenJourney {
    private Journey journey;

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }
}
