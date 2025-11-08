package ui.selenoid;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

public class AllureLogsExtension implements AfterTestExecutionCallback, AfterEachCallback {

    public static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> attachmentsAttached = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
        attachmentsAttached.set(false);
    }

    public static void clearDriver() {
        driverThreadLocal.remove();
        attachmentsAttached.remove();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        // Выполняем только если тест упал
        if (context.getExecutionException().isPresent()) {
            System.out.println("AllureLogsExtension: Тест упал, прикрепляем attachments...");
            attachAll(driverThreadLocal.get());
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        // Дополнительная проверка на случай, если afterTestExecution не сработал
        Boolean attached = attachmentsAttached.get();
        if (context.getExecutionException().isPresent() && (attached == null || !attached)) {
            System.out.println("AllureLogsExtension: afterEach - прикрепляем attachments...");
            attachAll(driverThreadLocal.get());
        }
    }

    private void attachAll(WebDriver driver) {
        if (driver == null) {
            System.err.println("AllureLogsExtension: Driver is null, cannot attach");
            return;
        }

        Boolean attached = attachmentsAttached.get();
        if (attached != null && attached) {
            System.out.println("AllureLogsExtension: Attachments already attached, skipping");
            return;
        }

        try {
            System.out.println("AllureLogsExtension: Attaching page source...");
            attachPageSource(driver);
            System.out.println("AllureLogsExtension: Attaching screenshot...");
            attachPageScreen(driver);
            System.out.println("AllureLogsExtension: Attaching browser logs...");
            attachBrowserLogs(driver);
            attachmentsAttached.set(true);
            System.out.println("AllureLogsExtension: All attachments attached successfully");
        } catch (Exception e) {
            System.err.println("AllureLogsExtension: Ошибка при прикреплении attachments: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Attachment(value = "Page source", type = "text/plain")
    public byte[] attachPageSource(WebDriver driver) {
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Page screen", type = "image/png")
    public byte[] attachPageScreen(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0]; // Возвращаем пустой массив, если скриншот не поддерживается
    }

    @Attachment(value = "Browser logs", type = "text/plain")
    public byte[] attachBrowserLogs(WebDriver driver) {
        String logs = getLogs(driver);
        return logs.getBytes(StandardCharsets.UTF_8);
    }

    private String getLogs(WebDriver driver) {
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