package com.komarov.travelFood.to;

import java.util.*;

/**
 * Created by Никита on 06.09.2017.
 */

public class Journey {
    int day;
    int people;
    int chosenDay = -1;

    private List<Day> dayList;

    private Map<String, List<MealWithWeight>> copyDayMenu;

    public Journey(int day, int people) {
        this.day = day;
        this.people = people;

        copyDayMenu = new HashMap<>();
        initDayList(day);
    }

    private void initDayList(int dayCount) {
        dayList = new ArrayList<>();

        for (int i = 0; i < dayCount; i++) {
            Day day = new Day(i + 1, dayCount);
            dayList.add(day);
        }
    }

    public Map<String, List<MealWithWeight>> getCopyDayMenu() {
        return copyDayMenu;
    }

    public int getPeople() {
        return people;
    }

    public int getDay() {
        return day;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public int getChosenDay() {
        return chosenDay;
    }

    public void setChosenDay(int chosenDay) {
        this.chosenDay = chosenDay;
    }
}
