package version_1_3.ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPageSelenide {
    private final SelenideElement loginField = $x("//input[@id='loginEdit-el']");
    private final SelenideElement passField = $("#passwordEdit-el");
    /*   private final SelenideElement authBtn = $x("//span[@id='t-comp14-textEl']");  */
    private final SelenideElement authBtn = $x("//div[@id='loginRememberContainer']/span[@data-item-marker=\"btnLogin\"]");
    private String login;
    private String pass;
    private String url;

    public AuthPageSelenide() {
        Properties properties = new Properties();
        try (InputStream in = AuthPageSelenide.class.getClassLoader().getResourceAsStream("configs/stands.properties")) {
            properties.load(in);
            this.login = properties.getProperty("login");
            this.pass = properties.getProperty("password");
            this.url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthPageSelenide authBasic() {
        Configuration.browserSize = "1920x1080";
        Selenide.open(url);
        loginField.should(Condition.enabled, Duration.ofSeconds(60));
        loginField.sendKeys(login);
        passField.sendKeys(pass);
        authBtn.should(Condition.enabled, Duration.ofSeconds(30));
        authBtn.click();
        return this;
    }

    public String getUrl() {
        return url;
    }
}
