package ui.selenoid;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SelenoidTests {
 /*   @Test
    @Disabled
    public void selenoidWihSelenidTest() {
        Configuration.remote("http://localhost:4444/wd/hub");
        Configuration.browser = "chrome";
        Selenide.open("https://vk.com");
    }*/

    @Test
    public void selenoidDockerTest() throws Exception {
        // Настраиваем capabilities (браузер, версия, опции и т.п.)
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        //      capabilities.setVersion("latest"); // или конкретную, например "120.0"
        capabilities.setVersion("128");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        // Подключаемся к Selenoid
        URL selenoidUrl = new URL("http://localhost:4444/wd/hub");
        WebDriver driver = new RemoteWebDriver(selenoidUrl, capabilities);

        // Тест
        driver.get("https://vk.com");
        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }

}
