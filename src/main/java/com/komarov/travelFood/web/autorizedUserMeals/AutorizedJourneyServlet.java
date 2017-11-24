package com.komarov.travelFood.web.autorizedUserMeals;

import com.komarov.travelFood.AutorizedClientPool;
import com.komarov.travelFood.model.autorizedUser.User;
import com.komarov.travelFood.web.autorizedUserMeals.abstractControllers.AbstractJourneyController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Никита on 20.11.2017.
 */
@Controller
@RequestMapping(value = "/autorized/autorJourneyDays")
public class AutorizedJourneyServlet extends AbstractJourneyController {

    @GetMapping
    public String showJourneyMeals(HttpServletRequest request, Model model) {
        User user = AutorizedClientPool.getClient(request.getRemoteAddr());

        model.addAttribute("id" , user.getId());

        return "autorized/autorJourneyDays";
    }

}
