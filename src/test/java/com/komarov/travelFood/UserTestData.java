package com.komarov.travelFood;


import com.komarov.travelFood.matcher.BeanMatcher;
import com.komarov.travelFood.model.autorizedUser.User;

import java.util.Objects;

import static com.komarov.travelFood.model.BaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_1_ID = START_SEQ;
    public static final int USER_2_ID = START_SEQ + 1;

    public static final User USER_1 = new User(USER_1_ID, "cartmaneze", "slipknot");
    public static final User USER_2 = new User(USER_2_ID, "user", "password");

    public static final BeanMatcher<User> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getLogin(), actual.getLogin())
                            && Objects.equals(expected.getPassword(), actual.getPassword())
                    )
    );
}
