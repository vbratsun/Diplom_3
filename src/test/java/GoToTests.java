import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GoToTests extends TestBase {
    private final String browserType;


    public GoToTests(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                //{"yandex"}, // пока не заработал яндекс браузер
        });
    }

    @Before
    public void setUp() {
        initDriver(browserType);
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    @Description("Проверка перехода по клику на «Личный кабинет»")
    public void testLKverify() throws InterruptedException {
        mainPage.createUserViaApi();
        lkPage.openAutorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        lkPage.verifyLkURL();
    }

    @Test
    @DisplayName("Переход по кнопке «Конструктор» из ЛК")
    @Description("Проверить, что при клике на «Конструктор» из ЛК происходит переход на главную страницу")
    public void testConstructorButtonRedirectsToMainPage() {
        mainPage.createUserViaApi();
        lkPage.openAutorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        mainPage.clickConstructorButton();
        mainPage.verifyMainPageUrlConstructor();
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers из ЛК")
    @Description("Проверить, что при клике на логотип из ЛК происходит переход на главную страницу")
    public void testLogoClickRedirectsToMainPage() {
        mainPage.createUserViaApi();
        lkPage.openAutorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        mainPage.clickLogoBurgers();
        mainPage.verifyMainPageUrlBurger();
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    @Description("Проверить что вышли из личного кабинета")
    public void testExitLK() throws InterruptedException {
        mainPage.createUserViaApi();
        lkPage.openAutorizeLkUrl();
        loginPage.inputEmail(mainPage.getCreatedUserEmail());
        loginPage.inputPassword(mainPage.getCreatedUserPassword());
        loginPage.enterButtonClick();
        mainPage.clickEnterLK();
        lkPage.clickExitButton();
        loginPage.verifyExitLkURL();
    }


    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    @Description("Проверка, что при клике на вкладку 'Соусы' она становится активной")
    public void testSaucesTabIsActive() {
        openBaseUrl();
        mainPage.clickSaucesTab();
        mainPage.assertSaucesTabIsActive();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    @Description("Проверка, что при клике на вкладку 'Начинки' она становится активной")
    public void testFillingsTabIsActive() {
        openBaseUrl();
        mainPage.clickFillingsTab();
        mainPage.assertFillingsTabIsActive();
    }

    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    @Description("Проверка, что при клике на вкладку 'Булки' она становится активной")
    public void testBunsTabIsActive() throws InterruptedException {
        openBaseUrl();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.assertBunsTabIsActive();
    }
}
