package ru.yandex.practicum.ui.pages;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.api.clients.AuthClient;
import ru.yandex.practicum.api.models.auth.UserLoginRequest;
import ru.yandex.practicum.api.models.auth.UserRegisterRequest;
import ru.yandex.practicum.constants.Urls;

public class RegisterPage {

    private final WebDriver webDriver;

    private final By nameTextEdit = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailTextEdit = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordTextEdit = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By linkEnter = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажать войти на форме регистрации")
    public RegisterPage clickEnterRegisterButton() {
        webDriver.findElement(linkEnter).click();
        return this;
    }

    @Step("Открыть страницу регистрации")
    public void openRegisterUrl() {
        webDriver.get(Urls.REGISTER_PAGE_URL);
    }

    @Step("Ввести имя")
    public RegisterPage inputName(String name) {
        webDriver.findElement(nameTextEdit).sendKeys(name);
        return this;
    }

    @Step("Ввести email")
    public RegisterPage inputEmail(String email) {
        webDriver.findElement(emailTextEdit).sendKeys(email);
        return this;
    }

    @Step("Кликнуть на email")
    public RegisterPage clickEmail() {
        webDriver.findElement(emailTextEdit).click();
        return this;
    }

    @Step("Ввести пароль")
    public RegisterPage inputPassword(String password) {
        webDriver.findElement(passwordTextEdit).sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку зарегестрироватся")
    public RegisterPage registerButtonClick() {
        webDriver.findElement(registerButton).click();
        return this;
    }

    @Step("Получить текст сообщения о некоректности пароля")
    public String errorIncorrectPassword() {
        String incorrectPassword = webDriver.findElement(errorIncorrectPassword).getText();
        return incorrectPassword;
    }

    @Step("Логин через API созданным пользователем")
    public String loginViaApi(UserRegisterRequest user) {
        AuthClient authClient = new AuthClient(Urls.BASE_URI);
        Response response = authClient.loginUser(new UserLoginRequest(user.getEmail(), user.getPassword()));
        response.then().statusCode(200);
        return authClient.getAccessToken(response);
    }
}
