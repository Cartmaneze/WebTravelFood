package com.komarov.travelFood.web.anonimousMeals;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.model.anonimous.AnonimDay;
import com.komarov.travelFood.model.anonimous.AnonimJourney;
import com.komarov.travelFood.model.anonimous.AnonimMealWithWeight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Никита on 20.09.2017.
 */
@Controller
@RequestMapping(value = "/anonimous/allFood")
public class AnonymousFoodCountServlet {

    @GetMapping
    public String showAllFoodCount(HttpServletRequest request) {
        AnonimJourney journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int numb = journey.getPeople();

        Map<String, Integer> allFood = new HashMap<>();
        int allWeight = 0;

        for (AnonimDay day : journey.getDayList()) {
            for (AnonimMealWithWeight m : day.getBreakfast()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
            for (AnonimMealWithWeight m : day.getDinner()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
            for (AnonimMealWithWeight m : day.getSupper()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
            for (AnonimMealWithWeight m : day.getSnacks()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
        }

        request.setAttribute("numb" , numb);
        request.setAttribute("allFood", allFood);
        request.setAttribute("allWeight", allWeight);

        return "/anonimous/allFood";
    }
}
