package com.komarov.travelFood.web;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.controller.DynamicControllerAnonimMeal;
import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.to.Journey;
import com.komarov.travelFood.to.MealWithWeight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Никита on 14.09.2017.
 */
@Controller
@RequestMapping(value = "/meals")
public class AnonymousMealServlet extends HttpServlet {

    @GetMapping
    public String show(HttpServletRequest request, Model model) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());

        int dayNumber = Integer.parseInt(request.getParameter("dayNumber"));
        String menu = request.getParameter("menu");

        request.setAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
        request.setAttribute("dayNumber", dayNumber);
        request.setAttribute("menu", menu);

        return "meals";
    }

    @GetMapping(value = "/clickMeal")
    public String clickMeal(HttpServletRequest request, Model model) {

        String choseMeal = request.getParameter("choseMeal");
        String choseMealCcal = request.getParameter("choceMealCcal");

        request.setAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
        request.setAttribute("choseMeal", choseMeal);
        request.setAttribute("choseMealCcal", choseMealCcal);
        request.setAttribute("choseMealWeight", 0);

        return "meals";
    }

    @PostMapping(value = "/clickMeal")
    public String clickdMeal(HttpServletRequest request, Model model) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String menu = request.getParameter("menu");
        String mealHashCode = request.getParameter("hashCode");
        String isUpdating = request.getParameter("isUpdating");

        String name = "еда без названия";
        if (!request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }

        int calories = 0;
        if (request.getParameter("calories").matches("[0-9]+")) {
            calories = Integer.parseInt(request.getParameter("calories"));
        }

        int weight = 0;
        if (request.getParameter("weight").matches("[0-9]+")) {
            weight = Integer.parseInt(request.getParameter("weight"));
        }

        Meal meal = new Meal();
        meal.setCalories(calories);
        meal.setName(name);
        MealWithWeight newMealWithWeight = new MealWithWeight(meal, weight);

        if (isUpdating.equals("true")) {
            List<MealWithWeight> mealWithWeightsToDeleteList = null;
            if (menu.equals("breakfast")) {
                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getBreakfast();
            } else if (menu.equals("dinner")) {
                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getDinner();
            } else if (menu.equals("supper")) {
                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getSupper();
            } else if (menu.equals("snack")) {
                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getSnacks();
            }
            int mealHashCodeInt = Integer.parseInt(mealHashCode);
            MealWithWeight mealWithWeightToDelete = null;
            for (MealWithWeight m : mealWithWeightsToDeleteList) {
                if (m.hashCode() == mealHashCodeInt) {
                    mealWithWeightToDelete = m;
                }
            }

            if (mealWithWeightToDelete != null) {
                mealWithWeightsToDeleteList.remove(mealWithWeightToDelete);
            }
        }

        if (menu.equals("breakfast")) {
            journey.getDayList().get(dayNumber).getBreakfast().add(newMealWithWeight);
        } else if (menu.equals("dinner")) {
            journey.getDayList().get(dayNumber).getDinner().add(newMealWithWeight);
        } else if (menu.equals("supper")) {
            journey.getDayList().get(dayNumber).getSupper().add(newMealWithWeight);
        } else if (menu.equals("snack")) {
            journey.getDayList().get(dayNumber).getSnacks().add(newMealWithWeight);
        }

        String dayAllCalories = "";
        String dayAllWeight = "";

        double dayAllCaloriesCount = 0;
        int dayAllWeightCount = 0;
        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getBreakfast()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getDinner()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getSupper()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getSnacks()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        dayAllCalories = String.valueOf(dayAllCaloriesCount);
        dayAllWeight = String.valueOf(dayAllWeightCount);

        request.setAttribute("dayBreakfast", journey.getDayList().get(dayNumber).getBreakfast());
        request.setAttribute("dayDinner", journey.getDayList().get(dayNumber).getDinner());
        request.setAttribute("daySupper", journey.getDayList().get(dayNumber).getSupper());
        request.setAttribute("daySnack", journey.getDayList().get(dayNumber).getSnacks());
        request.setAttribute("journeyDayList", journey.getDayList());
        request.setAttribute("dayAllCalories", dayAllCalories);
        request.setAttribute("dayAllWeight", dayAllWeight);

        return "redirect:/journeyDays";
    }






//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
//
//        String choseMeal = request.getParameter("choseMeal");
//        String choseMealCcal = request.getParameter("choceMealCcal");
//        String updatingMealHashCode = request.getParameter("updatingMealHashCode");
//        String isUpdating = request.getParameter("isUpdating");
//        int dayNumber = Integer.parseInt(request.getParameter("dayNumber"));
//        String menu = request.getParameter("menu");
//
//        if (choseMeal != null) {
//            request.setAttribute("dayNumber", dayNumber);
//            request.setAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
//            request.setAttribute("choseMeal", choseMeal);
//            request.setAttribute("choseMealCcal", choseMealCcal);
//            request.setAttribute("choseMealWeight", 0);
//            if (isUpdating.equals("true")) {
//                request.setAttribute("isUpdating", isUpdating);
//                request.setAttribute("hashCode", updatingMealHashCode);
//            }
//            request.setAttribute("menu", menu);
//            request.getRequestDispatcher("meals.jsp").forward(request, response);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
//        int dayNumber = 0;
//        if (journey.getChosenDay() > 0) {
//            dayNumber = journey.getChosenDay();
//        }
//
//        String menu = request.getParameter("menu");
//        String mealHashCode = request.getParameter("hashCode");
//        String isUpdating = request.getParameter("isUpdating");
//
//        String name = "еда без названия";
//        if (!request.getParameter("name").isEmpty()) {
//            name = request.getParameter("name");
//        }
//
//        int calories = 0;
//        if (request.getParameter("calories").matches("[0-9]+")) {
//            calories = Integer.parseInt(request.getParameter("calories"));
//        }
//
//        int weight = 0;
//        if (request.getParameter("weight").matches("[0-9]+")) {
//            weight = Integer.parseInt(request.getParameter("weight"));
//        }
//
//        Meal meal = new Meal();
//        meal.setCalories(calories);
//        meal.setName(name);
//        MealWithWeight newMealWithWeight = new MealWithWeight(meal, weight);
//
//        if (isUpdating.equals("true")) {
//            List<MealWithWeight> mealWithWeightsToDeleteList = null;
//            if (menu.equals("breakfast")) {
//                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getBreakfast();
//            } else if (menu.equals("dinner")) {
//                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getDinner();
//            } else if (menu.equals("supper")) {
//                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getSupper();
//            } else if (menu.equals("snack")) {
//                mealWithWeightsToDeleteList = journey.getDayList().get(dayNumber).getSnacks();
//            }
//            int mealHashCodeInt = Integer.parseInt(mealHashCode);
//            MealWithWeight mealWithWeightToDelete = null;
//            for (MealWithWeight m : mealWithWeightsToDeleteList) {
//                if (m.hashCode() == mealHashCodeInt) {
//                    mealWithWeightToDelete = m;
//                }
//            }
//
//            if (mealWithWeightToDelete != null) {
//                mealWithWeightsToDeleteList.remove(mealWithWeightToDelete);
//            }
//        }
//
//        if (menu.equals("breakfast")) {
//            journey.getDayList().get(dayNumber).getBreakfast().add(newMealWithWeight);
//        } else if (menu.equals("dinner")) {
//            journey.getDayList().get(dayNumber).getDinner().add(newMealWithWeight);
//        } else if (menu.equals("supper")) {
//            journey.getDayList().get(dayNumber).getSupper().add(newMealWithWeight);
//        } else if (menu.equals("snack")) {
//            journey.getDayList().get(dayNumber).getSnacks().add(newMealWithWeight);
//        }
//
//        String dayAllCalories = "";
//        String dayAllWeight = "";
//
//        double dayAllCaloriesCount = 0;
//        int dayAllWeightCount = 0;
//        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getBreakfast()) {
//            dayAllWeightCount += meals.getWeight();
//            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
//        }
//        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getDinner()) {
//            dayAllWeightCount += meals.getWeight();
//            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
//        }
//        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getSupper()) {
//            dayAllWeightCount += meals.getWeight();
//            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
//        }
//        for (MealWithWeight meals : journey.getDayList().get(dayNumber).getSnacks()) {
//            dayAllWeightCount += meals.getWeight();
//            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
//        }
//        dayAllCalories = String.valueOf(dayAllCaloriesCount);
//        dayAllWeight = String.valueOf(dayAllWeightCount);
//
//        request.setAttribute("dayBreakfast", journey.getDayList().get(dayNumber).getBreakfast());
//        request.setAttribute("dayDinner", journey.getDayList().get(dayNumber).getDinner());
//        request.setAttribute("daySupper", journey.getDayList().get(dayNumber).getSupper());
//        request.setAttribute("daySnack", journey.getDayList().get(dayNumber).getSnacks());
//        request.setAttribute("journeyDayList", journey.getDayList());
//        request.setAttribute("dayAllCalories", dayAllCalories);
//        request.setAttribute("dayAllWeight", dayAllWeight);
//        request.getRequestDispatcher("journeyDays.jsp").forward(request, response);
//    }
}
