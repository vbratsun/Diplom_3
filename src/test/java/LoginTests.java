import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginTests extends TestBase {

    private final String browserType;


    public LoginTests(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                //{"yandex"},
        });
    }

    @Before
    public void setUp() {
        initDriver(browserType);
    }

    @Test
    @DisplayName("Войти по кнопке - Войти в аккаунт на главной")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void testEnterButtonAccount() throws InterruptedException {
        mainPage.createUserViaApi();
        openBaseUrl();
        mainPage.clickEnterInAccount();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        Assert.assertEquals(
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации",
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText()
        );
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    @Description("Вход по кнопке через кнопку «Личный кабинет»")
    public void testEnterLKAccount() throws InterruptedException {
        mainPage.createUserViaApi();
        openBaseUrl();
        mainPage.clickEnterLK();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        Assert.assertEquals(
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации",
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText()
        );
    }

    @Test
    @DisplayName("вход через кнопку войти на форме «Регистрация»")
    @Description("вход через кнопку войти на форме «Регистрация»")
    public void testEnterRegisterAccount() throws InterruptedException {
        mainPage.createUserViaApi();
        registerPage.openRegisterUrl();
        registerPage.clickEnterRegisterButton();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        Assert.assertEquals(
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации",
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText()
        );
    }

    @Test
    @DisplayName("вход через кнопку войти на форме «Востановления пароля»")
    @Description("вход через кнопку войти на форме «Востановления пароля»")
    public void testEnterRecoveryAccount() throws InterruptedException {
        mainPage.createUserViaApi();
        recoveryPage.openForgotUrl();
        recoveryPage.clickEnterForgotButton();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        Assert.assertEquals(
                "Проверяем, что созданный пользователь отображается в интерфейсе после авторизации",
                mainPage.getCreatedUserEmail(),
                lkPage.getLkLoginText()
        );
    }
}
