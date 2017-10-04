package com.komarov.travelFood.web;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.to.Day;
import com.komarov.travelFood.to.Journey;
import com.komarov.travelFood.to.MealWithWeight;
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
@RequestMapping(value = "/allFood")
public class AnonymousFoodCountServlet {

    @GetMapping
    public String showAllFoodCount(HttpServletRequest request) {
        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
        int numb = journey.getPeople();

        Map<String, Integer> allFood = new HashMap<>();
        int allWeight = 0;

        for (Day day : journey.getDayList()) {
            for (MealWithWeight m : day.getBreakfast()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
            for (MealWithWeight m : day.getDinner()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
            for (MealWithWeight m : day.getSupper()) {
                if (allFood.containsKey(m.getMeal().getName())) {
                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
                } else {
                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
                }
                allWeight += m.getWeight() * numb;
            }
            for (MealWithWeight m : day.getSnacks()) {
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

        return "/allFood";
    }


//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        Journey journey = AnonymousClientPool.getJourney(request.getRemoteAddr());
//        int numb = journey.getPeople();
//
//        Map<String, Integer> allFood = new HashMap<>();
//        int allWeight = 0;
//
//        for (Day day : journey.getDayList()) {
//            for (MealWithWeight m : day.getBreakfast()) {
//                if (allFood.containsKey(m.getMeal().getName())) {
//                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
//                } else {
//                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
//                }
//                allWeight += m.getWeight() * numb;
//            }
//            for (MealWithWeight m : day.getDinner()) {
//                if (allFood.containsKey(m.getMeal().getName())) {
//                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
//                } else {
//                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
//                }
//                allWeight += m.getWeight() * numb;
//            }
//            for (MealWithWeight m : day.getSupper()) {
//                if (allFood.containsKey(m.getMeal().getName())) {
//                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
//                } else {
//                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
//                }
//                allWeight += m.getWeight() * numb;
//            }
//            for (MealWithWeight m : day.getSnacks()) {
//                if (allFood.containsKey(m.getMeal().getName())) {
//                    allFood.put(m.getMeal().getName(), allFood.get(m.getMeal().getName()) + m.getWeight() * numb);
//                } else {
//                    allFood.put(m.getMeal().getName(), m.getWeight() * numb);
//                }
//                allWeight += m.getWeight() * numb;
//            }
//        }
//
//        request.setAttribute("numb" , numb);
//        request.setAttribute("allFood", allFood);
//        request.setAttribute("allWeight", allWeight);
//        request.getRequestDispatcher("allFood.jsp").forward(request, response);
//    }
}
