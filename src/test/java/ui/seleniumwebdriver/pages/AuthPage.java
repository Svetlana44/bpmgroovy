package ui.seleniumwebdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthPage extends BasePage {
    private final By loginField = By.xpath("//input[@id='loginEdit-el']");
    private final By passField = By.id("passwordEdit-el");
    private final By authBtn = By.xpath("//span[@id='t-comp14-textEl']");


    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void authBasic(String login, String pass) {
        // Ожидаем загрузки и видимости элементов перед взаимодействием
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField)).sendKeys(login);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passField)).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(authBtn)).click();
    }
}
