package ru.yandex.practicum.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {
    WebDriver webDriver;

    public AuthorizationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        waitForPageLoad();
    }

    private final By enterFormHeading = By.xpath("//*[contains(text(), 'Вход')]");

    private final By emailField = By.xpath(".//label[text()='Email']/../input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/../input");

    private final By enterProfileButton = By.xpath(".//button[text()='Войти']");

    private final By registrationLink = By.className("Auth_link__1fOlj");

    private final By recoverPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public void waitForPageLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(enterFormHeading));
    }

    public void userDataEntry(String email, String password) {
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(passwordField).sendKeys(password);
    }

    public void clickEnterButton() {
        webDriver.findElement(enterProfileButton).click();

    }

    public void clickRegistrationLink() {
        webDriver.findElement(registrationLink).click();
    }

    public void clickRecoverPasswordLink() {
        webDriver.findElement(recoverPasswordLink).click();
    }

}


