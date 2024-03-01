package version_1_3.ui.selenide.tests;

import org.junit.jupiter.api.Test;
import version_1_3.ui.selenide.pages.AuthPageSelenide;

public class AuthSelenideTests {
    @Test
    public void AuthBasicTest() {
        AuthPageSelenide authPage = new AuthPageSelenide();
        authPage.authBasic();
    }
}
