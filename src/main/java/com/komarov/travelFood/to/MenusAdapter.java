package com.komarov.travelFood.to;

import com.komarov.travelFood.model.Menus;

/**
 * Created by Никита on 22.11.2017.
 */
public class MenusAdapter {
    public static String getMenu(Enum enumMenu) {
        if (enumMenu == Menus.BREAKFAST) {
            return "завтрак";
        } else if (enumMenu == Menus.DINNER) {
            return "обед";
        } else if (enumMenu == Menus.SUPPER) {
            return "ужин";
        } else if (enumMenu == Menus.SNACKS) {
            return "перекусы";
        } else {
            return "";
        }
    }
}
