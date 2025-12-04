package ru.yandex.practicum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    private final By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By checkoutButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By personalAccountButton = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText') and contains(@class, 'ml-2') and text()='Личный Кабинет']");
    private final By saucesLink = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Соусы']]");
    private final By bunsLink = By.xpath("//div[contains(@class, 'tab_tab') and .//span[text()='Булки']]");
    private final By fillingLink = By.xpath("//div[contains(@class, 'tab_tab') and .//*[text()='Начинки']]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickEnterAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(enterAccountButton));
        driver.findElement(enterAccountButton).click();
    }

    @Step
    public void waitForEnterAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
    }

    @Step
    public void waitCheckoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }

    @Step
    public void waitForPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
    }

    @Step
    public void enterPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }



    @Step
    public void clickBunsLink() {
        driver.findElement(bunsLink).click();
    }

    @Step
    public void clickSaucesLink() {
        driver.findElement(saucesLink).click();
    }

    @Step
    public void clickFillingsLink() {
        driver.findElement(fillingLink).click();
    }

    @Step
    public String getClassNameBuns() {
        return driver.findElement(bunsLink).getAttribute("class");
    }

    @Step
    public String getClassNameSauces() {
        return driver.findElement(saucesLink).getAttribute("class");
    }

    @Step
    public String getClassNameFillings() {
        return driver.findElement(fillingLink).getAttribute("class");
    }

    // Методы для ожидания изменения класса у элементов
    @Step
    public void waitForBunsActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(bunsLink, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step
    public void waitForSaucesActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(saucesLink, "class", "tab_tab_type_current__2BEPc"));
    }

    @Step
    public void waitForFillingsActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(fillingLink, "class", "tab_tab_type_current__2BEPc"));
    }
}

