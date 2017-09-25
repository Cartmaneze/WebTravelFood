package com.komarov.travelFood.web;

import com.komarov.travelFood.ClientPool;
import com.komarov.travelFood.to.Day;
import com.komarov.travelFood.to.Journey;
import com.komarov.travelFood.to.MealWithWeight;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Никита on 20.09.2017.
 */
public class FoodCountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Journey journey = ClientPool.getJourney(request.getRemoteAddr());
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
        request.getRequestDispatcher("allFood.jsp").forward(request, response);
    }
}
