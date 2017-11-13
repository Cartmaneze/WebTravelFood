package com.komarov.travelFood.web.anonimousMeals;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.controller.anonimous.ControllerAnonimMeal;
import com.komarov.travelFood.controller.anonimous.DynamicControllerAnonimMeal;
import com.komarov.travelFood.model.anonimous.AnonimJourney;
import com.komarov.travelFood.model.anonimous.AnonimMealWithWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Никита on 03.09.2017.
 */

@Controller
@RequestMapping(value = "/anonimous/journeyDays")
public class AnonymousJourneyServlet {

    @Autowired
    private ConfigurableApplicationContext springContext;
    private ControllerAnonimMeal controllerAnonimMeal;

    @PostConstruct
    public void init() {
        controllerAnonimMeal = springContext.getBean(ControllerAnonimMeal.class);
        DynamicControllerAnonimMeal.addList(controllerAnonimMeal.getAll());
    }

    @GetMapping
    public String showJourneyMeals(HttpServletRequest request, Model model) {

        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());

        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
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
        model.addAttribute("dayNumber" , dayNumber+1);
        model.addAttribute("poolSize" , AnonymousClientPool.getClientPoolSize());
        model.addAttribute("journeyDayList", journey.getDayList());
        model.addAttribute("dayAllCalories", dayAllCalories);
        model.addAttribute("dayAllWeight", dayAllWeight);

        return "anonimous/journeyDays";
    }

    @GetMapping("/newDayNumber")
    public String newDayNumber(HttpServletRequest request) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String nextDay = request.getParameter("nextDay");

        if (nextDay != null) {
            journey.setChosenDay(Integer.parseInt(nextDay) - 1);
            dayNumber = Integer.parseInt(nextDay) - 1;
        }

        return "redirect:/anonimous/journeyDays";
    }

    @GetMapping("/deleteMeal")
    public String delete(HttpServletRequest request) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String menu = request.getParameter("menu");

        int hashCode = Integer.parseInt(request.getParameter("hashCode"));
        List<AnonimMealWithWeight> mealWithWeights = new ArrayList<>();
        AnonimMealWithWeight mealToDelete = null;
        if (menu.equals("breakfast")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getBreakfast();
        } else if (menu.equals("dinner")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getDinner();
        } else if (menu.equals("supper")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getSupper();
        } else if (menu.equals("snack")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getSnacks();
        }

        for (AnonimMealWithWeight m : mealWithWeights) {
            if (m.hashCode() == hashCode) {
                        mealToDelete = m;
            }
        }

        mealWithWeights.remove(mealToDelete);

        return "redirect:/anonimous/journeyDays";
    }

    @GetMapping("/newMeal")
    public String newMeal(HttpServletRequest request, Model model) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String menu = request.getParameter("menu");

        model.addAttribute("dayNumber", dayNumber);
        model.addAttribute("menu", menu);

        return "redirect:/anonimous/meals";
    }

    @GetMapping("/updateMeal")
    public String updateMeal(HttpServletRequest request, Model model) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }
        String menu = request.getParameter("menu");

        model.addAttribute("dayNumber", dayNumber);
        model.addAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
        model.addAttribute("choseMeal", request.getParameter("name"));
        model.addAttribute("choseMealCcal", request.getParameter("calories"));
        model.addAttribute("choseMealWeight", request.getParameter("weight"));
        model.addAttribute("hashCode", request.getParameter("hashCode"));
        model.addAttribute("isUpdating", "true");
        model.addAttribute("menu", menu);

        return "redirect:/anonimous/meals";
    }

    @GetMapping("/copyDayMenu")
    public String copyDayMenu(HttpServletRequest request, Model model) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        List<AnonimMealWithWeight> breakfastCopyList = journey.getDayList().get(dayNumber).getBreakfast().stream().collect(Collectors.toList());
        journey.getCopyDayMenu().put("breakfast", breakfastCopyList);

        List<AnonimMealWithWeight> dinnerCopyList = journey.getDayList().get(dayNumber).getDinner().stream().collect(Collectors.toList());
        journey.getCopyDayMenu().put("dinner", dinnerCopyList);

        List<AnonimMealWithWeight> supperCopyList = journey.getDayList().get(dayNumber).getSupper().stream().collect(Collectors.toList());
        journey.getCopyDayMenu().put("supper", supperCopyList);

        List<AnonimMealWithWeight> snacksCopyList = journey.getDayList().get(dayNumber).getSnacks().stream().collect(Collectors.toList());
        journey.getCopyDayMenu().put("snacks", snacksCopyList);

        return "redirect:/anonimous/journeyDays";
    }

    @GetMapping("/pasteDayMenu")
    public String pasteDayMenu(HttpServletRequest request, Model model) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }
        if (!journey.getCopyDayMenu().isEmpty()) {
            journey.getDayList().get(dayNumber).getBreakfast().clear();
            journey.getDayList().get(dayNumber).getDinner().clear();
            journey.getDayList().get(dayNumber).getSupper().clear();
            journey.getDayList().get(dayNumber).getSnacks().clear();

            for (AnonimMealWithWeight m : journey.getCopyDayMenu().get("breakfast")) {
                journey.getDayList().get(dayNumber).getBreakfast().add(m);
            }
            for (AnonimMealWithWeight m : journey.getCopyDayMenu().get("dinner")) {
                journey.getDayList().get(dayNumber).getDinner().add(m);
            }
            for (AnonimMealWithWeight m : journey.getCopyDayMenu().get("supper")) {
                journey.getDayList().get(dayNumber).getSupper().add(m);
            }
            for (AnonimMealWithWeight m : journey.getCopyDayMenu().get("snacks")) {
                journey.getDayList().get(dayNumber).getSnacks().add(m);
            }
        }
        return "redirect:/anonimous/journeyDays";
    }
}
