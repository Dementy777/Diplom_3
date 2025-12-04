package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practicum.utils.Constants;
import ru.yandex.practicum.pageObject.HomePage;
import ru.yandex.practicum.pageObject.AuthorizationPage;
import ru.yandex.practicum.pageObject.ProfilePage;
import ru.yandex.practicum.pageObject.RegistrationPage;
import org.junit.Assert;
import org.junit.Test;


public class RegistrationTest extends BaseTest {
    HomePage homePage;
    AuthorizationPage authorizationPage;
    RegistrationPage registrationPage;
    ProfilePage profilePage;

    // Генерируем случайные данные
    String name = RandomStringUtils.randomAlphanumeric(10);
    String email = RandomStringUtils.randomAlphanumeric(7) + "@gufum.com";
    String correctPassword = RandomStringUtils.randomAlphanumeric(6);
    String wrongPassword = RandomStringUtils.randomAlphanumeric(5);

    @Test
    @DisplayName("позитивный тест на успешную регистрацию пользователя")
    @Description("Позитивный тест на создание пользователя с заполненными полями")
    public void successfulRegistrationTest() {
        //вход на главную пейджу и клик личный кабинет
        homePage = new HomePage(driver);
        homePage.waitForPersonalAccountButton();
        homePage.enterPersonalAccountButton();
        //переход по кнопке зарегистрироваться
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.waitForPageLoad();
        authorizationPage.clickRegistrationLink();
        //заполнение формы регистрации
        registrationPage = new RegistrationPage(driver);
        registrationPage.waitForPageLoad();
        registrationPage.fillInRegistrationForm(name, email, correctPassword);
        //ждем перехода на главную после регистрации
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.waitForPageLoad();
        Assert.assertEquals(Constants.LOGIN_PAGE, driver.getCurrentUrl());
        //вводим данные с которыми зарегистрировались
        authorizationPage.userDataEntry(email, correctPassword);
        authorizationPage.clickEnterButton();
        homePage.waitForPersonalAccountButton();
        //переходим в профиль
        homePage.enterPersonalAccountButton();
        profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageLoad();
        //проверяем данные
        Assert.assertEquals(name, profilePage.getNameText());
        Assert.assertEquals("Email не совпадает (игнорируя регистр)", email.toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("Проверка на невозможность создание пользователя с некорректным паролем")
    @Description("Негативный тест на невозможность создания пользователя с 5 значным паролем")
    public void errorShortPasswordTest() {
        homePage = new HomePage(driver);
        homePage.waitForPersonalAccountButton();
        homePage.enterPersonalAccountButton();
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.waitForPageLoad();
        authorizationPage.clickRegistrationLink();
        registrationPage = new RegistrationPage(driver);
        registrationPage.waitForPageLoad();
        registrationPage.fillInRegistrationForm(name, email, wrongPassword);
        Assert.assertEquals("Некорректный пароль", registrationPage.getPasswordFieldErrorText());
        Assert.assertEquals(Constants.REGISTER_PAGE, driver.getCurrentUrl());
    }

}