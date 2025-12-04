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

}