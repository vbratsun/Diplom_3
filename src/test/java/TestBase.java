import org.junit.After;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.api.clients.AuthClient;
import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.pages.*;
import ru.yandex.practicum.ui.utils.WebDriverStarts;

public class TestBase {

    protected WebDriver driver;

    public RegisterPage registerPage;
    public MainPage mainPage;
    public LoginPage loginPage;
    public LkPage lkPage;
    public RecoveryPage recoveryPage;

    public void initDriver(String browserType) {
        driver = WebDriverStarts.createDriver(browserType);
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        lkPage = new LkPage(driver);
        recoveryPage = new RecoveryPage(driver);
    }

    protected void openBaseUrl() {
        driver.get(Urls.HOME_PAGE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (mainPage.getCurrentUserToken() != null) {
            AuthClient authClient = new AuthClient(Urls.BASE_URI);
            authClient.deleteUser(mainPage.getCurrentUserToken())
                    .then()
                    .statusCode(202);
        }
    }
}
