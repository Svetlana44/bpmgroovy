package version_1_3.ui.seleniumwebdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage {
    private final By loginField = By.xpath("//input[@id='loginEdit-el']");
    private final By passField = By.id("passwordEdit-el");
    private final By authBtn = By.xpath("//span[@id='t-comp14-textEl']");
    private final WebDriver driver;
    private final String login = "Supervisor";
    private final String pass = "BPMAdmin123!";

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void authBasic() {
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(authBtn).click();

    }
}
