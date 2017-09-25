package com.komarov.travelFood.web;

import com.komarov.travelFood.ClientPool;
import com.komarov.travelFood.to.Journey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Никита on 14.09.2017.
 */
public class ClientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        response.sendRedirect("journeyDays");
    }


    public void createNewJourney(String clientIp, int dayNumber, int peopleNumber) {
        Journey newJourney = new Journey(dayNumber, peopleNumber);
        ClientPool.setInClientPool(clientIp, newJourney);
    }
}
