package ru.yandex.practicum.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.constants.Urls;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LkPage {

    private final WebDriver webDriver;


    private final By loginInput = By.xpath(".//label[text()='Логин']/following-sibling::input");
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public LkPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу авторизации")
    public void openAutorizeLkUrl() {
        webDriver.get(Urls.LOGIN_PAGE_URL);
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    @Step("Нажать на кнопку выйти")
    public LkPage clickExitButton() {
        WebElement element = waitForElementToBeClickable(exitButton);
        webDriver.findElement(exitButton).click();
        return this;
    }


    @Step("Получить текст поля логин")
    public String getLkLoginText() {
        WebElement element = waitForElementToBeVisible(loginInput);
        return element.getAttribute("value");
    }

    @Step("Получить URL текущей страницы и проверить его")
    public String verifyLkURL() {
        WebElement element = waitForElementToBeClickable(exitButton);
        String actualUrl = webDriver.getCurrentUrl();
        assertEquals("Ссылка ведёт не на страницу личного кабинета", Urls.LK_PAGE_URL, actualUrl);
        return actualUrl;
    }
}
