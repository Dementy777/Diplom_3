package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import ru.yandex.practicum.page.object.HomePage;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class SwitchingSectionsTest extends BaseTest {
    HomePage homePage;

    @Test
    @DisplayName("Проверка: переключение на раздел «Булки» с раздела «Соусы»")
    @Description("При открытии главной страницы на разделе «Булки» переход на раздел «Соусы» и обратно. Проверяется наличие активного CSS-класса у вкладки.")
    public void bunsActiveOnLoadTest() {
        homePage = new HomePage(driver);
        homePage.waitForEnterAccountButton();
        homePage.clickSaucesLink();
        homePage.waitForSaucesActive(10);
        homePage.clickBunsLink();
        homePage.waitForBunsActive(10);

        Assert.assertThat(homePage.getClassNameBuns(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переключение на раздел «Соусы»")
    @Description("Пользователь может переключиться с раздела «Булки» на раздел «Соусы». Проверяется, что вкладка «Соусы» становится активной после клика.")
    public void switchSaucesTest() {
        homePage = new HomePage(driver);
        homePage.waitForEnterAccountButton();
        homePage.clickSaucesLink();
        homePage.waitForSaucesActive(10);
        Assert.assertThat(homePage.getClassNameSauces(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Переключение на раздел «Начинки»")
    @Description("Пользователь может переключиться на раздел «Начинки». Проверяется, что вкладка «Начинки» становится активной после клика.")
    public void switchFillingsTest() {
        homePage = new HomePage(driver);
        homePage.waitForEnterAccountButton();
        homePage.clickFillingsLink();
        homePage.waitForFillingsActive(10);
        Assert.assertThat(homePage.getClassNameFillings(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }
}










