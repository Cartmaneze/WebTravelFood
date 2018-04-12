package com.komarov.travelFood.web.autorizedUserMeals;

import com.komarov.travelFood.AnonymousClientPool;
import com.komarov.travelFood.AutorizedClientPool;
import com.komarov.travelFood.model.anonymous.AnonimJourney;
import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.web.autorizedUserMeals.abstractControllers.AbstractUserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Никита on 14.09.2017.
 */
@Controller
@RequestMapping
public class AutorizedClient extends AbstractUserController {

    @PostMapping("/loginPassword")
    public String enterLoginPassword(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = super.getByLoginPassword(login, password);

        if (user == null) {
            return "autorized/login";
        } else {
            AutorizedClientPool.setInClientPool(request.getRemoteAddr(), user);
            return "redirect:/autorized/daysAndMWW";
        }
    }

    @PostMapping("/newLoginPassword")
    public String newLoginPassword(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User newUser = new User(login, password);
        User user = super.save(newUser);

        if (user == null) {
            return "autorized/newLogin";
        } else {
            AutorizedClientPool.setInClientPool(request.getRemoteAddr(), user);
            return "redirect:/autorized/daysAndMWW";
        }
    }

    public void createNewJourney(String clientIp, int dayNumber, int peopleNumber) {
        AnonimJourney newJourney = new AnonimJourney(dayNumber, peopleNumber);
        AnonymousClientPool.setInClientPool(clientIp, newJourney);
    }
}
