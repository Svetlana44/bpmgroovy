package ui.selenoid;

import io.qameta.allure.Attachment;
import lombok.Setter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

public class AllureLogsAttachment {
    @Setter
    private static WebDriver driver;

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver не установлен. Вызовите setDriver() перед использованием.");
        }
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Page screen", type = "image/png")
    public static byte[] pageScreen() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver не установлен. Вызовите setDriver() перед использованием.");
        }
        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalStateException("WebDriver не поддерживает создание скриншотов. Драйвер должен реализовывать TakesScreenshot.");
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Browser logs", type = "text/plain")
    public static byte[] browserLogs() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver не установлен. Вызовите setDriver() перед использованием.");
        }
        String logs = getLogs();
        return logs.getBytes(StandardCharsets.UTF_8);
    }

    private static String getLogs() {
        if (!(driver instanceof RemoteWebDriver remoteDriver)) {
            return "WebDriver не является RemoteWebDriver. Логи браузера недоступны.";
        }

        String browser = remoteDriver.getCapabilities().getBrowserName();

        if (browser != null && browser.equalsIgnoreCase("chrome")) {
            try {
                StringBuilder logsBuilder = new StringBuilder();
                driver.manage().logs().get(LogType.BROWSER).forEach(logEntry -> {
                    logsBuilder.append("[").append(logEntry.getLevel()).append("] ")
                            .append(logEntry.getTimestamp()).append(": ")
                            .append(logEntry.getMessage())
                            .append("\n");
                });
                return logsBuilder.length() > 0 ? logsBuilder.toString() : "Логи браузера пусты.";
            } catch (Exception e) {
                return "Ошибка при получении логов браузера: " + e.getMessage();
            }
        }

        return "Логи браузера доступны только для Chrome. Текущий браузер: " + browser;
    }
}