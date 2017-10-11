package com.komarov.travelFood.model.anonimous;

import java.util.*;

/**
 * Created by Никита on 06.09.2017.
 */

public class AnonimJourney {
    int day;
    int people;
    int chosenDay = -1;

    private List<AnonimDay> dayList;

    private Map<String, List<AnonimMealWithWeight>> copyDayMenu;

    public AnonimJourney(int day, int people) {
        this.day = day;
        this.people = people;

        copyDayMenu = new HashMap<>();
        initDayList(day);
    }

    private void initDayList(int dayCount) {
        dayList = new ArrayList<>();

        for (int i = 0; i < dayCount; i++) {
            AnonimDay day = new AnonimDay(i + 1, dayCount);
            dayList.add(day);
        }
    }

    public Map<String, List<AnonimMealWithWeight>> getCopyDayMenu() {
        return copyDayMenu;
    }

    public int getPeople() {
        return people;
    }

    public int getDay() {
        return day;
    }

    public List<AnonimDay> getDayList() {
        return dayList;
    }

    public int getChosenDay() {
        return chosenDay;
    }

    public void setChosenDay(int chosenDay) {
        this.chosenDay = chosenDay;
    }
}
