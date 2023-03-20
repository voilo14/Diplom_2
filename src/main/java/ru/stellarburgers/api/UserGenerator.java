package ru.stellarburgers.api;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class UserGenerator {

    public User getNewRandomUser() {
        return new User(RandomStringUtils.randomAlphanumeric(9) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(9),
                RandomStringUtils.randomAlphanumeric(9));
    }
}
