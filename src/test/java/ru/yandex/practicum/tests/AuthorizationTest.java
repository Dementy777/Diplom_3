package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import ru.yandex.practicum.page.object.*;
import ru.yandex.practicum.user.User;
import ru.yandex.practicum.user.UserStep;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.utils.Constants.HOST;

public class AuthorizationTest extends BaseTest {

    private HomePage homePage;
    private AuthorizationPage authorizationPage;
    private ProfilePage profilePage;
    private RegistrationPage registrationPage;
    private RecoverPasswordPage recoverPasswordPage;

    private User user;
    private UserStep userStep = new UserStep();
    private String accessToken;

    @Before
    public void setUp() {
        super.setUp();

        String name = RandomStringUtils.randomAlphanumeric(6);
        String email = RandomStringUtils.randomAlphanumeric(7) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphanumeric(8);
        user = new User(email, password, name, ""); // Создали объект пользователя

        // Регистрация пользователя через API
        ValidatableResponse createResponse = userStep.createUser(user);
        createResponse.assertThat().statusCode(SC_OK); // Проверка успешности регистрации

        accessToken = userStep.extractAccessToken(createResponse); // Получаем токен
        user.setAccessToken(accessToken); // Привязываем токен к пользователю

        driver.get(HOST);
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("позитивный тест на вход по кнопке «Войти в аккаунт» на главной")
    @Description("Позитивный тест на вход через регистрацию по API, логина пользователя и проверки по email")
    public void enterAccountButtonTest() {
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.userDataEntry(user.getEmail(), user.getPassword());
        authorizationPage.clickEnterButton();
        homePage.waitCheckoutButton();
        homePage.enterPersonalAccountButton();
        profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageLoad();
        assertEquals("Email в профиле не совпадает с зарегистрированным (игнорируем регистр)",
                user.getEmail().toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("позитивный тест на вход по кнопке «Личный кабинет»")
    @Description("Позитивный тест на вход через регистрацию по API, логина пользователя и проверки по email")
    public void enterProfileButtonTest() {
        homePage.waitForPersonalAccountButton();
        homePage.enterPersonalAccountButton();
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.waitForPageLoad();
        authorizationPage.userDataEntry(user.getEmail(), user.getPassword());
        authorizationPage.clickEnterButton();
        homePage.waitCheckoutButton();
        homePage.enterPersonalAccountButton();
        profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageLoad();
        assertEquals("Email в профиле не совпадает с зарегистрированным (игнорируем регистр)",
                user.getEmail().toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("позитивный тест на вход по кнопке в форме регистрации")
    @Description("Позитивный тест на вход через регистрацию по API, логина пользователя и проверки по email")
    public void enterLinkInRegistrationFormTest() {
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.waitForPageLoad();
        authorizationPage.clickRegistrationLink();
        registrationPage = new RegistrationPage(driver);
        registrationPage.waitForPageLoad();
        registrationPage.clickAlreadyRegisteredLink();
        authorizationPage.waitForPageLoad();
        authorizationPage.userDataEntry(user.getEmail(), user.getPassword());
        authorizationPage.clickEnterButton();
        homePage.waitCheckoutButton();
        homePage.enterPersonalAccountButton();
        profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageLoad();
        assertEquals("Email в профиле не совпадает с зарегистрированным (игнорируем регистр)",
                user.getEmail().toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("позитивный тест на вход по кнопке в форме восстановления пароля")
    @Description("Позитивный тест на вход через регистрацию по API, логина пользователя и проверки по email")
    public void enterLinkInRecoverPasswordTest() {
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();
        authorizationPage = new AuthorizationPage(driver);
        authorizationPage.waitForPageLoad();
        authorizationPage.clickRecoverPasswordLink();
        recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.waitForPageLoad();
        recoverPasswordPage.clickRememberedPassword();
        authorizationPage.waitForPageLoad();
        authorizationPage.userDataEntry(user.getEmail(), user.getPassword());
        authorizationPage.clickEnterButton();
        homePage.waitCheckoutButton();
        homePage.enterPersonalAccountButton();
        profilePage = new ProfilePage(driver);
        profilePage.waitProfilePageLoad();
        assertEquals("Email в профиле не совпадает с зарегистрированным (игнорируем регистр)",
                user.getEmail().toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @After
    public void tearDown() {
        if (user != null && user.getAccessToken() != null) {
            userStep.deleteUser(user);
        }
        super.tearDown();
    }
}
