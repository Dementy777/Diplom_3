package ru.yandex.practicum.pageObject;

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
    private final By saucesLink = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By bunsLink = By.xpath(".//span[text()='Булки']/parent::div");
    private final By fillingLink = By.xpath(".//*[text()='Начинки']/parent::div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEnterAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(enterAccountButton));
        driver.findElement(enterAccountButton).click();
    }

    public void waitForEnterAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
    }

    public void waitCheckoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }

    public void waitForPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
    }

    public void enterPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickBunsLink() {
        driver.findElement(bunsLink).click();
    }

    public void clickSaucesLink() {
        driver.findElement(saucesLink).click();
    }

    public void clickFillingsLink() {
        driver.findElement(fillingLink).click();
    }

    public String getClassNameBuns() {
        return driver.findElement(bunsLink).getAttribute("class");
    }

    public String getClassNameSauces() {
        return driver.findElement(saucesLink).getAttribute("class");
    }

    public String getClassNameFillings() {
        return driver.findElement(fillingLink).getAttribute("class");
    }

    public void waitForClassToBe(By locator, String expectedClass, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.attributeContains(locator, "class", expectedClass));
    }

    // Методы для ожидания изменения класса у элементов
    public void waitForSaucesActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(saucesLink, "class", "tab_tab_type_current__2BEPc"));
    }

    public void waitForFillingsActive(long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.attributeContains(fillingLink, "class", "tab_tab_type_current__2BEPc"));
    }
}

