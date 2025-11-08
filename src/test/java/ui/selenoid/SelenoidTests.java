package ui.selenoid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@ExtendWith(AllureLogsExtension.class)
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
        // Настраиваем ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("128.0");

        // Включаем логирование браузера для получения логов в Allure
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", loggingPreferences);

        // Используем W3C синтаксис для Selenoid (требуется для Selenium 4.25.0+)
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        options.setCapability("selenoid:options", selenoidOptions);

        /*Чтобы работало, см чтобы контейнеры были запущены и видео и браузера и selenoid
         * aerokube/selenoid:1.11.2
         * selenoid/video-recorder:latest (или latest-release)
         * selenoid/chrome:128.0 */

        // Подключаемся к Selenoid
        URL selenoidUrl = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(selenoidUrl, options);
        AllureLogsExtension.setDriver(driver);

        try {
            // Тест
            driver.get("https://vk.com");
            System.out.println("Title: " + driver.getTitle());
            Assertions.assertEquals(3, 2, "Специально падает тест для проверки вывода в аллюр");
        } catch (AssertionError | Exception e) {
            // Прикрепляем attachments напрямую в catch блоке как запасной вариант
            System.out.println("Тест упал, прикрепляем attachments напрямую...");
            try {
                AllureLogsAttachment.setDriver(driver);
                AllureLogsAttachment.pageSource();
                AllureLogsAttachment.pageScreen();
                AllureLogsAttachment.browserLogs();
            } catch (Exception attachException) {
                System.err.println("Ошибка при прямом прикреплении attachments: " + attachException.getMessage());
            }
            // Extension также попытается прикрепить attachments
            throw e;
        } finally {
            // Закрываем driver только после того, как Extension успел прикрепить attachments
            // Extension вызывается автоматически JUnit перед finally блоком
            if (driver != null) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    System.err.println("Ошибка при закрытии driver: " + e.getMessage());
                }
            }
            AllureLogsExtension.clearDriver();
        }
    }

    @Test
    public void selenoidFirefoxTest() throws Exception {
        // Настраиваем FirefoxOptions
        FirefoxOptions options = new FirefoxOptions();
        options.setBrowserVersion("123.0");

        // Используем W3C синтаксис для Selenoid (требуется для Selenium 4.25.0+)
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        options.setCapability("selenoid:options", selenoidOptions);

        // Подключаемся к Selenoid
        URL selenoidUrl = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(selenoidUrl, options);
        AllureLogsExtension.setDriver(driver);

        try {
            // Тест
            driver.get("https://vk.com");
            System.out.println("Title: " + driver.getTitle());
        } finally {
            driver.quit();
            AllureLogsExtension.clearDriver();
        }
    }
//для allure логов и видео

    @Test
    public void selenoidAllureTest() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("128.0");

        // Используем W3C синтаксис для Selenoid (требуется для Selenium 4.25.0+)
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        options.setCapability("selenoid:options", selenoidOptions);

        // Подключаемся к Selenoid
        URL selenoidUrl = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(selenoidUrl, options);
        AllureLogsExtension.setDriver(driver); // Сохраняем для Extension

        try {
            driver.get("https://vk.com");
            System.out.println("Title: " + driver.getTitle());
            // Можно вызвать исключение для проверки работы Extension
            // throw new RuntimeException("Тестовая ошибка для проверки Allure attachments");
        } finally {
            driver.quit();
            AllureLogsExtension.clearDriver();
        }
    }
}