package com.komarov.travelFood.to;

import com.komarov.travelFood.model.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 06.09.2017.
 */
public class Day {
    String name;
    List<MealWithWeight> breakfast = new ArrayList<>();
    List<MealWithWeight> dinner = new ArrayList<>();
    List<MealWithWeight> supper = new ArrayList<>();
    List<MealWithWeight> snacks = new ArrayList<>();

    public Day(int name, int allDaysCount) {
        this.name = String.valueOf(name);
        initDayMenu(name, allDaysCount);
    }

    private void initDayMenu(int dayNumber, int allDaysCount) {
        Meal grechka = new Meal();
        grechka.setCalories(330);
        grechka.setName("гречка");
        Meal kukKrupa = new Meal();
        kukKrupa.setCalories(337);
        kukKrupa.setName("кукурузная крупа");
        Meal sushMeat = new Meal();
        sushMeat.setCalories(500);
        sushMeat.setName("сушеное мясо");
        Meal sugar = new Meal();
        sugar.setCalories(398);
        sugar.setName("сахар");
        Meal coffe = new Meal();
        coffe.setCalories(0);
        coffe.setName("кофе");
        Meal tea = new Meal();
        tea.setCalories(0);
        tea.setName("чай");
        Meal pripravа = new Meal();
        pripravа.setCalories(150);
        pripravа.setName("приправа");
        Meal bread = new Meal();
        bread.setCalories(320);
        bread.setName("хлебцы");
        Meal sausage = new Meal();
        sausage.setCalories(450);
        sausage.setName("колбаса");
        Meal candy = new Meal();
        candy.setCalories(360);
        candy.setName("конфеты");
        Meal kozinaky = new Meal();
        kozinaky.setCalories(576);
        kozinaky.setName("козинаки");
        Meal salt = new Meal();
        salt.setCalories(0);
        salt.setName("соль");
        Meal snikers = new Meal();
        snikers.setCalories(507);
        snikers.setName("сникерс");

        if (dayNumber > 1) {
            breakfast.add(new MealWithWeight(grechka, 90));
            breakfast.add(new MealWithWeight(sushMeat, 40));
            breakfast.add(new MealWithWeight(pripravа, 2));
            breakfast.add(new MealWithWeight(salt, 3));
            breakfast.add(new MealWithWeight(coffe, 10));
            breakfast.add(new MealWithWeight(sugar, 10));
        }

        dinner.add(new MealWithWeight(bread, 120));
        dinner.add(new MealWithWeight(sausage, 60));
        dinner.add(new MealWithWeight(snikers, 40));

        if (dayNumber < allDaysCount) {
            supper.add(new MealWithWeight(kukKrupa, 100));
            supper.add(new MealWithWeight(sushMeat, 40));
            supper.add(new MealWithWeight(pripravа, 2));
            supper.add(new MealWithWeight(salt, 3));
            supper.add(new MealWithWeight(tea, 5));
            supper.add(new MealWithWeight(sugar, 10));
            supper.add(new MealWithWeight(kozinaky, 80));
        }

        snacks.add(new MealWithWeight(candy, 35));
    }

    public String getName() {
        return name;
    }

    public List<MealWithWeight> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<MealWithWeight> breakfast) {
        this.breakfast = breakfast;
    }

    public List<MealWithWeight> getDinner() {
        return dinner;
    }

    public void setDinner(List<MealWithWeight> dinner) {
        this.dinner = dinner;
    }

    public List<MealWithWeight> getSupper() {
        return supper;
    }

    public void setSupper(List<MealWithWeight> supper) {
        this.supper = supper;
    }

    public List<MealWithWeight> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<MealWithWeight> snacks) {
        this.snacks = snacks;
    }
}
