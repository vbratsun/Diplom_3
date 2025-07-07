import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.api.models.auth.UserRegisterRequest;
import ru.yandex.practicum.api.utils.DataHelper;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegisterTests extends TestBase {
    private final UserRegisterRequest user = new DataHelper().createRandomUser();
    private final String browserType;


    public RegisterTests(String browserType) {
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
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации через UI")
    public void testRegistrationSuccess() {
        registerPage.openRegisterUrl();
        registerPage.inputName(user.getName());
        registerPage.inputEmail(user.getEmail());
        registerPage.inputPassword(user.getPassword());
        registerPage.registerButtonClick();
    }

    @Test
    @DisplayName("Тест проверки некорректного пароля")
    @Description("Проверка некорректного ввода пароля")
    public void testIncorrectPasswordError() {
        registerPage.openRegisterUrl();
        registerPage.inputName(user.getName());
        registerPage.inputEmail(user.getEmail());
        String password = "12345";
        registerPage.inputPassword(password);
        registerPage.clickEmail();
        Assert.assertEquals(
                "Проверка ошибки, если пароль менее 6 символов",
                "Некорректный пароль",
                registerPage.errorIncorrectPassword()
        );
    }
}
