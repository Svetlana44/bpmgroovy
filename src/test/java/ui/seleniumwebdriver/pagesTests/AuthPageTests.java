package ui.seleniumwebdriver.pagesTests;

import org.junit.jupiter.api.Test;
import ui.seleniumwebdriver.pages.AuthPage;

public class AuthPageTests extends BaseTests {
    @Test
    public void authOnStartPage() {
        /*  driver описан в базовом классе */
        AuthPage authPage = new AuthPage(driver);
        LOG.info("авторизация по кредам");
        authPage.authBasic(loginSupervisor, passSupervisor);
    }
}
