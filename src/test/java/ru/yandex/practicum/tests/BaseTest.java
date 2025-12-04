package ru.yandex.practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import ru.yandex.practicum.utils.Constants;
import ru.yandex.practicum.utils.DriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getNewDriver();
        driver.get(Constants.HOST);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


