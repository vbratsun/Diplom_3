package ru.yandex.practicum.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.constants.Urls;

public class RecoveryPage {
    private final WebDriver webDriver;

    private final By linkEnter = By.xpath(".//a[text()='Войти']");

    public RecoveryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу восстановления пароля")
    public void openForgotUrl() {
        webDriver.get(Urls.RECOVERY_PAGE_URL);
    }

    @Step("Нажать войти на форме регистрации")
    public RecoveryPage clickEnterForgotButton() {
        webDriver.findElement(linkEnter).click();
        return this;
    }
}
