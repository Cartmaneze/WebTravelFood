package com.komarov.travelFood.web;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.to.Journey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Никита on 14.09.2017.
 */
@Controller
@RequestMapping(value = "/client")
public class AnonymousClientServlet {

    @PostMapping
    public String newJourney(HttpServletRequest request) {
        String dayNumberString = request.getParameter("dayNumber");
        String peopleNumberString = request.getParameter("peopleNumber");
        int dayNumber;
        int peopleNumber;

        if (dayNumberString.matches("[0-9]+") && Integer.parseInt(dayNumberString) > 0) {
            dayNumber = Integer.parseInt(dayNumberString);
        } else {
            dayNumber = 1;
        }

        if (peopleNumberString.matches("[0-9]+") && Integer.parseInt(peopleNumberString) > 0) {
            peopleNumber = Integer.parseInt(peopleNumberString);
        } else {
            peopleNumber = 1;
        }

        String clientIp = request.getRemoteAddr();
        createNewJourney(clientIp, dayNumber, peopleNumber);

        return "redirect:journeyDays";
    }


    public void createNewJourney(String clientIp, int dayNumber, int peopleNumber) {
        Journey newJourney = new Journey(dayNumber, peopleNumber);
        AnonymousClientPool.setInClientPool(clientIp, newJourney);
    }
}
