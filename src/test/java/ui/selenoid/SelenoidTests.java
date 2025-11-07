package ui.selenoid;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
        // Настраиваем ChromeOptions (W3C синтаксис)
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("128.0");

        // Настраиваем опции Selenoid через selenoid:options (W3C синтаксис)
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);

        options.setCapability("selenoid:options", selenoidOptions);
        /*Чтобы работало, см чтобы контейнеры были запущены и видео и браузера и selenoid
         * aerokube/selenoid:1.11.2
         * selenoid/video-recorder:latest-release
         * selenoid/chrome:128.0 */
        // Подключаемся к Selenoid
        URL selenoidUrl = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(selenoidUrl, options);

        // Тест
        driver.get("https://vk.com");
        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }

    @Test
    public void selenoidFirefoxTest() throws Exception {
        // Настраиваем FirefoxOptions (W3C синтаксис)
        FirefoxOptions options = new FirefoxOptions();
        options.setBrowserVersion("123.0");

        // Настраиваем опции Selenoid через selenoid:options (W3C синтаксис)
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        options.setCapability("selenoid:options", selenoidOptions);

        // Подключаемся к Selenoid
        URL selenoidUrl = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(selenoidUrl, options);

        // Тест
        driver.get("https://vk.com");
        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }

}
