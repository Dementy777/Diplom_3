package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import ru.yandex.practicum.pageObject.HomePage;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class SwitchingSectionsTest extends BaseTest {
    HomePage homePage;

    @Test
    @DisplayName("Проверка: раздел «Булки» активен при загрузке страницы")
    @Description("При открытии главной страницы раздел «Булки» должен быть выбран по умолчанию. Проверяется наличие активного CSS-класса у вкладки.")
    public void bunsSectionIsActiveOnLoad() {
        homePage = new HomePage(driver);
        homePage.waitForEnterAccountButton();

        Assert.assertThat(homePage.getClassNameBuns(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переключение на раздел «Соусы»")
    @Description("Пользователь может переключиться с раздела «Булки» на раздел «Соусы». Проверяется, что вкладка «Соусы» становится активной после клика.")
    public void switchToSauces() {
        homePage = new HomePage(driver);
        homePage.waitForEnterAccountButton();
        Assert.assertThat(homePage.getClassNameSauces(), CoreMatchers.not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));
        homePage.clickSaucesLink();
        homePage.waitForSaucesActive(10);
        Assert.assertThat(homePage.getClassNameSauces(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переключение на раздел «Начинки»")
    @Description("Пользователь может переключиться на раздел «Начинки». Проверяется, что вкладка «Начинки» становится активной после клика.")
    public void switchToFillings() {
        homePage = new HomePage(driver);
        homePage.waitForEnterAccountButton();
        Assert.assertThat(homePage.getClassNameFillings(), CoreMatchers.not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));
        homePage.clickFillingsLink();
        homePage.waitForFillingsActive(10);
        Assert.assertThat(homePage.getClassNameFillings(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

}