package com.komarov.travelFood.web;

import com.komarov.travelFood.ClientPool;
import com.komarov.travelFood.controller.ControllerMeal;
import com.komarov.travelFood.controller.DynamicControllerMeal;
import com.komarov.travelFood.to.Journey;
import com.komarov.travelFood.to.MealWithWeight;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Никита on 03.09.2017.
 */
public class JourneyServlet extends HttpServlet {

    ControllerMeal controllerMeal;
    private ConfigurableApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        controllerMeal = springContext.getBean(ControllerMeal.class);
        DynamicControllerMeal.addList(controllerMeal.getAll());
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Journey journey = ClientPool.getJourney(request.getRemoteAddr());
        int dayNumber = 0;
        if (journey.getChosenDay() > 0) {
            dayNumber = journey.getChosenDay();
        }

        String nextDay = request.getParameter("nextDay");
        String action = request.getParameter("action");
        String menu = request.getParameter("menu");

        if (action != null) {
            if (action.equals("newMeal")) {
                request.setAttribute("dayNumber", dayNumber);
                request.setAttribute("meal", DynamicControllerMeal.getDinamicMealList());
                request.setAttribute("menu", menu);
                request.getRequestDispatcher("meals.jsp").forward(request, response);
            } else if (action.equals("deleteMeal")) {
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

            } else if (action.equals("updateMeal")) {
                request.setAttribute("dayNumber", dayNumber);
                request.setAttribute("meal", DynamicControllerMeal.getDinamicMealList());
                request.setAttribute("choseMeal", request.getParameter("name"));
                request.setAttribute("choseMealCcal", request.getParameter("calories"));
                request.setAttribute("choseMealWeight", request.getParameter("weight"));
                request.setAttribute("hashCode", request.getParameter("hashCode"));
                request.setAttribute("isUpdating", "true");
                request.setAttribute("menu", menu);
                request.getRequestDispatcher("meals.jsp").forward(request, response);

            } else if (action.equals("copyDayMenu")) {
                List<MealWithWeight> breakfastCopyList = journey.getDayList().get(dayNumber).getBreakfast().stream().collect(Collectors.toList());
                journey.getCopyDayMenu().put("breakfast", breakfastCopyList);

                List<MealWithWeight> dinnerCopyList = journey.getDayList().get(dayNumber).getDinner().stream().collect(Collectors.toList());
                journey.getCopyDayMenu().put("dinner", dinnerCopyList);

                List<MealWithWeight> supperCopyList = journey.getDayList().get(dayNumber).getSupper().stream().collect(Collectors.toList());
                journey.getCopyDayMenu().put("supper", supperCopyList);

                List<MealWithWeight> snacksCopyList = journey.getDayList().get(dayNumber).getSnacks().stream().collect(Collectors.toList());
                journey.getCopyDayMenu().put("snacks", snacksCopyList);

            } else if (action.equals("pasteDayMenu")) {
                if (!journey.getCopyDayMenu().isEmpty()) {
                    journey.getDayList().get(dayNumber).getBreakfast().clear();
                    journey.getDayList().get(dayNumber).getDinner().clear();
                    journey.getDayList().get(dayNumber).getSupper().clear();
                    journey.getDayList().get(dayNumber).getSnacks().clear();

                    for (MealWithWeight m : journey.getCopyDayMenu().get("breakfast")) {
                        journey.getDayList().get(dayNumber).getBreakfast().add(m);
                    }
                    for (MealWithWeight m : journey.getCopyDayMenu().get("dinner")) {
                        journey.getDayList().get(dayNumber).getDinner().add(m);
                    }
                    for (MealWithWeight m : journey.getCopyDayMenu().get("supper")) {
                        journey.getDayList().get(dayNumber).getSupper().add(m);
                    }
                    for (MealWithWeight m : journey.getCopyDayMenu().get("snacks")) {
                        journey.getDayList().get(dayNumber).getSnacks().add(m);
                    }
                }
            }
        }

        if (nextDay != null) {
            journey.setChosenDay(Integer.parseInt(nextDay) - 1);
            dayNumber = Integer.parseInt(nextDay) - 1;
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
        request.setAttribute("dayNumber" , dayNumber+1);
        request.setAttribute("poolSize" , ClientPool.getClientPoolSize());
        request.setAttribute("journeyDayList", journey.getDayList());
        request.setAttribute("dayAllCalories", dayAllCalories);
        request.setAttribute("dayAllWeight", dayAllWeight);
        request.getRequestDispatcher("journeyDays.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
