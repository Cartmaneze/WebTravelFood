package com.komarov.travelFood.web.autorizedUserMeals;

import com.komarov.travelFood.AutorizedClientPool;
import com.komarov.travelFood.model.autorizedUser.Day;
import com.komarov.travelFood.model.autorizedUser.Journey;
import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.model.autorizedUser.UserMealWithWeight;
import com.komarov.travelFood.web.autorizedUserMeals.chosenObjects.ChosenDay;
import com.komarov.travelFood.web.autorizedUserMeals.chosenObjects.ChosenJourney;
import com.komarov.travelFood.web.autorizedUserMeals.extControllers.DayController;
import com.komarov.travelFood.web.autorizedUserMeals.extControllers.JourneyController;
import com.komarov.travelFood.web.autorizedUserMeals.extControllers.MealsWithWeightController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Никита on 27.11.2017.
 */
@Controller
@RequestMapping(value = "/autorized/daysAndMWW")
public class AutorizedJourneyDaysMWW {

    @Autowired
    DayController dayController;

    @Autowired
    MealsWithWeightController mWWController;

    @Autowired
    JourneyController journeyController;

    @Autowired
    ChosenJourney chosenJourney;

    @Autowired
    ChosenDay chosenDay;

    @GetMapping
    public String showJourneyMeals(HttpServletRequest request, Model model) {
        User user = AutorizedClientPool.getClient(request.getRemoteAddr());

        if (chosenJourney.getJourney() == null) {
            List<Journey> journeyList = journeyController.getAll(user.getId());
            if (journeyList.isEmpty()) {
                chosenJourney.setJourney(journeyController.save(makeDefaultJourney(user), user.getId()));
            } else {
                chosenJourney.setJourney(journeyList.get(0));
            }
        }


        List<Day> dayList = dayController.getAll(chosenJourney.getJourney().getId());
        if (dayList.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                dayController.save(makeDefaultDay(chosenJourney.getJourney()), chosenJourney.getJourney().getId());
            }
        }
        dayList = dayController.getAll(chosenJourney.getJourney().getId());
        chosenDay.setDay(dayList.get(0));

        List<UserMealWithWeight> mealWithWeights = mWWController.getAll(chosenDay.getDay().getId(), "завтрак");


        model.addAttribute("login" , user.getLogin());
        model.addAttribute("days" , dayList);
        model.addAttribute("dayBreakfast", mealWithWeights);

        return "autorized/daysAndMWW";
    }

    @GetMapping("/deleteDay")
    public String deleteDay(HttpServletRequest request) {
        int dayId = Integer.parseInt(request.getParameter("dayId"));
        int journeyId = Integer.parseInt(request.getParameter("journeyId"));

        dayController.delete(dayId, journeyId);

        return "redirect:/autorized/daysAndMWW";
    }

    @GetMapping("/newDay")
    public String newDay(HttpServletRequest request) {

        dayController.save(makeDefaultDay(chosenJourney.getJourney()), chosenJourney.getJourney().getId());

        return "redirect:/autorized/daysAndMWW";
    }




    public Journey makeDefaultJourney(User user) {
        return new Journey("no Name", user);
    }

    public Day makeDefaultDay(Journey journey) {
        return new Day("day", journey);
    }

}
