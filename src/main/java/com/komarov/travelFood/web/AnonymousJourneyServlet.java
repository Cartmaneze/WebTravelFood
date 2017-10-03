package com.komarov.travelFood.web;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.controller.ControllerAnonimMeal;
import com.komarov.travelFood.controller.DynamicControllerAnonimMeal;
import com.komarov.travelFood.to.Journey;
import com.komarov.travelFood.to.MealWithWeight;
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

/**
 * Created by Никита on 03.09.2017.
 */

@Controller
@RequestMapping(value = "/journeyDays")
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

        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());

        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
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

        model.addAttribute("dayBreakfast", journey.getDayList().get(dayNumber).getBreakfast());
        model.addAttribute("dayDinner", journey.getDayList().get(dayNumber).getDinner());
        model.addAttribute("daySupper", journey.getDayList().get(dayNumber).getSupper());
        model.addAttribute("daySnack", journey.getDayList().get(dayNumber).getSnacks());
        model.addAttribute("dayNumber" , dayNumber+1);
        model.addAttribute("poolSize" , AnonymousClientPool.getClientPoolSize());
        model.addAttribute("journeyDayList", journey.getDayList());
        model.addAttribute("dayAllCalories", dayAllCalories);
        model.addAttribute("dayAllWeight", dayAllWeight);

        return "journeyDays";
    }

    @GetMapping("/newDayNumber")
    public String newDayNumber(HttpServletRequest request) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String nextDay = request.getParameter("nextDay");

        if (nextDay != null) {
            journey.setChosenDay(Integer.parseInt(nextDay) - 1);
            dayNumber = Integer.parseInt(nextDay) - 1;
        }

        return "redirect:/journeyDays";
    }

    @GetMapping("/deleteMeal")
    public String delete(HttpServletRequest request) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String menu = request.getParameter("menu");

        int hashCode = Integer.parseInt(request.getParameter("hashCode"));
        List<MealWithWeight> mealWithWeights = new ArrayList<>();
        MealWithWeight mealToDelete = null;
        if (menu.equals("breakfast")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getBreakfast();
        } else if (menu.equals("dinner")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getDinner();
        } else if (menu.equals("supper")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getSupper();
        } else if (menu.equals("snack")) {
            mealWithWeights = journey.getDayList().get(dayNumber).getSnacks();
        }

        for (MealWithWeight m : mealWithWeights) {
            if (m.hashCode() == hashCode) {
                        mealToDelete = m;
            }
        }

        mealWithWeights.remove(mealToDelete);

        return "redirect:/journeyDays";
    }

    @GetMapping("/newMeal")
    public String newMeal(HttpServletRequest request, Model model) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String menu = request.getParameter("menu");

        model.addAttribute("dayNumber", dayNumber);
        model.addAttribute("menu", menu);

        return "redirect:/meals";
    }

    @GetMapping("/updateMeal")
    public String updateMeal(HttpServletRequest request, Model model) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
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

        return "redirect:/meals";
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        Journey journey = ClientPool.getJourney(request.getRemoteAddr());
//        int dayNumber = 0;
//        if (journey.getChosenDay() > 0) {
//            dayNumber = journey.getChosenDay();
//        }
//
//        String nextDay = request.getParameter("nextDay");
//        String action = request.getParameter("action");
//        String menu = request.getParameter("menu");
//
//        if (action != null) {
//            if (action.equals("newMeal")) {
//                request.setAttribute("dayNumber", dayNumber);
//                request.setAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
//                request.setAttribute("menu", menu);
//                request.getRequestDispatcher("meals.jsp").forward(request, response);
//            } else if (action.equals("deleteMeal")) {
//                int hashCode = Integer.parseInt(request.getParameter("hashCode"));
//                List<MealWithWeight> mealWithWeights = new ArrayList<>();
//                MealWithWeight mealToDelete = null;
//                if (menu.equals("breakfast")) {
//                    mealWithWeights = journey.getDayList().get(dayNumber).getBreakfast();
//                } else if (menu.equals("dinner")) {
//                    mealWithWeights = journey.getDayList().get(dayNumber).getDinner();
//                } else if (menu.equals("supper")) {
//                    mealWithWeights = journey.getDayList().get(dayNumber).getSupper();
//                } else if (menu.equals("snack")) {
//                    mealWithWeights = journey.getDayList().get(dayNumber).getSnacks();
//                }
//
//                for (MealWithWeight m : mealWithWeights) {
//                    if (m.hashCode() == hashCode) {
//                        mealToDelete = m;
//                    }
//                }
//
//                mealWithWeights.remove(mealToDelete);
//
//            } else if (action.equals("updateMeal")) {
//                request.setAttribute("dayNumber", dayNumber);
//                request.setAttribute("meal", DynamicControllerAnonimMeal.getDinamicMealList());
//                request.setAttribute("choseMeal", request.getParameter("name"));
//                request.setAttribute("choseMealCcal", request.getParameter("calories"));
//                request.setAttribute("choseMealWeight", request.getParameter("weight"));
//                request.setAttribute("hashCode", request.getParameter("hashCode"));
//                request.setAttribute("isUpdating", "true");
//                request.setAttribute("menu", menu);
//                request.getRequestDispatcher("meals.jsp").forward(request, response);
//
//            } else if (action.equals("copyDayMenu")) {
//                List<MealWithWeight> breakfastCopyList = journey.getDayList().get(dayNumber).getBreakfast().stream().collect(Collectors.toList());
//                journey.getCopyDayMenu().put("breakfast", breakfastCopyList);
//
//                List<MealWithWeight> dinnerCopyList = journey.getDayList().get(dayNumber).getDinner().stream().collect(Collectors.toList());
//                journey.getCopyDayMenu().put("dinner", dinnerCopyList);
//
//                List<MealWithWeight> supperCopyList = journey.getDayList().get(dayNumber).getSupper().stream().collect(Collectors.toList());
//                journey.getCopyDayMenu().put("supper", supperCopyList);
//
//                List<MealWithWeight> snacksCopyList = journey.getDayList().get(dayNumber).getSnacks().stream().collect(Collectors.toList());
//                journey.getCopyDayMenu().put("snacks", snacksCopyList);
//
//            } else if (action.equals("pasteDayMenu")) {
//                if (!journey.getCopyDayMenu().isEmpty()) {
//                    journey.getDayList().get(dayNumber).getBreakfast().clear();
//                    journey.getDayList().get(dayNumber).getDinner().clear();
//                    journey.getDayList().get(dayNumber).getSupper().clear();
//                    journey.getDayList().get(dayNumber).getSnacks().clear();
//
//                    for (MealWithWeight m : journey.getCopyDayMenu().get("breakfast")) {
//                        journey.getDayList().get(dayNumber).getBreakfast().add(m);
//                    }
//                    for (MealWithWeight m : journey.getCopyDayMenu().get("dinner")) {
//                        journey.getDayList().get(dayNumber).getDinner().add(m);
//                    }
//                    for (MealWithWeight m : journey.getCopyDayMenu().get("supper")) {
//                        journey.getDayList().get(dayNumber).getSupper().add(m);
//                    }
//                    for (MealWithWeight m : journey.getCopyDayMenu().get("snacks")) {
//                        journey.getDayList().get(dayNumber).getSnacks().add(m);
//                    }
//                }
//            }
//        }
//
//        if (nextDay != null) {
//            journey.setChosenDay(Integer.parseInt(nextDay) - 1);
//            dayNumber = Integer.parseInt(nextDay) - 1;
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
//        request.setAttribute("dayNumber" , dayNumber+1);
//        request.setAttribute("poolSize" , ClientPool.getClientPoolSize());
//        request.setAttribute("journeyDayList", journey.getDayList());
//        request.setAttribute("dayAllCalories", dayAllCalories);
//        request.setAttribute("dayAllWeight", dayAllWeight);
//        request.getRequestDispatcher("journeyDays.jsp").forward(request, response);
//    }

}
