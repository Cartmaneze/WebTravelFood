package com.komarov.travelFood.web.anonymousMeals;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.model.Meal;
import com.komarov.travelFood.model.anonymous.AnonimJourney;
import com.komarov.travelFood.model.anonymous.AnonimMealWithWeight;
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
@RequestMapping(value = "/anonymous/meals")
public class AnonymousMealServlet extends HttpServlet {

    @GetMapping
    public String show(HttpServletRequest request, Model model) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());

        String choseMeal = request.getParameter("choseMeal");
        String choseMealCcal = request.getParameter("choseMealCcal");
        String mealCcal = request.getParameter("mealCcal");
        String updatingMealHashCode = request.getParameter("hashCode");
        String isUpdating = request.getParameter("isUpdating");
        String menu = request.getParameter("menu");
        String weight = request.getParameter("choseMealWeight");

        request.setAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
        request.setAttribute("menu", menu);

        if (choseMeal != null) {
            request.setAttribute("dayNumber", journey.getChosenDay()+1);
            request.setAttribute("choseMeal", choseMeal);
            request.setAttribute("choseMealCcal", mealCcal);
            request.setAttribute("choseMealWeight", 0);
        }
        if (isUpdating!=null && isUpdating.equals("true")) {
            request.setAttribute("isUpdating", isUpdating);
            request.setAttribute("hashCode", updatingMealHashCode);
            if (mealCcal == null) {
                request.setAttribute("choseMealCcal", choseMealCcal);
            }
            if (weight != null) {
                request.setAttribute("choseMealWeight", weight);
            } else {
                request.setAttribute("choseMealWeight", 0);
            }

        }

        return "anonymous/meals";
    }

    @PostMapping
    public String clickdMeal(HttpServletRequest request, Model model) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
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
        AnonimMealWithWeight newMealWithWeight = new AnonimMealWithWeight(meal, weight);

        if (isUpdating.equals("true")) {
            List<AnonimMealWithWeight> mealWithWeightsToDeleteList = null;
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
            AnonimMealWithWeight mealWithWeightToDelete = null;
            for (AnonimMealWithWeight m : mealWithWeightsToDeleteList) {
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
        for (AnonimMealWithWeight meals : journey.getDayList().get(dayNumber).getBreakfast()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        for (AnonimMealWithWeight meals : journey.getDayList().get(dayNumber).getDinner()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        for (AnonimMealWithWeight meals : journey.getDayList().get(dayNumber).getSupper()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        for (AnonimMealWithWeight meals : journey.getDayList().get(dayNumber).getSnacks()) {
            dayAllWeightCount += meals.getWeight();
            dayAllCaloriesCount += meals.getMeal().getCalories() * meals.getWeight() / 100;
        }
        dayAllCalories = String.valueOf(dayAllCaloriesCount);
        dayAllWeight = String.valueOf(dayAllWeightCount);

        model.addAttribute("dayBreakfast", journey.getDayList().get(dayNumber).getBreakfast());
        model.addAttribute("dayDinner", journey.getDayList().get(dayNumber).getDinner());
        model.addAttribute("daySupper", journey.getDayList().get(dayNumber).getSupper());
        model.addAttribute("daySnack", journey.getDayList().get(dayNumber).getSnacks());
        model.addAttribute("journeyDayList", journey.getDayList());
        model.addAttribute("dayAllCalories", dayAllCalories);
        model.addAttribute("dayAllWeight", dayAllWeight);

        return "redirect:/anonymous/journeyDays";
    }
}
