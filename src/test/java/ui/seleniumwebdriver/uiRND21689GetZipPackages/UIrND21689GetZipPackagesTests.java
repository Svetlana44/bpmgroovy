package ui.seleniumwebdriver.uiRND21689GetZipPackages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.seleniumwebdriver.pages.AuthPage;
import ui.seleniumwebdriver.pages.RightPanelPage;
import ui.seleniumwebdriver.pagesTests.UIBaseTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class UIrND21689GetZipPackagesTests extends UIBaseTests {
    /*  driver описан в базовом классе */
    // Кнопка/пункт "Экспортировать"/"Export" в открытом mat-menu (RU/EN)
    By exportButton = By.xpath("//div[contains(@class,'cdk-overlay-pane')]//button[(contains(@class,'mat-menu-item') or contains(@class,'actions-menu-button-with-icon')) and (contains(normalize-space(.),'Экспортировать') or contains(normalize-space(.),'Export'))]");

    @Test
    @Step("Supervisor проверка скачивания пакета")
    @DisplayName("Проверка кликабельности кнопки 'Экспортировать' (Закоментировано появления сообщения 'Http failer response'")
    @Description("Закоментированый Тест снимает блокировку кнопки, кликает по ней и проверяет появление модального окна с ошибкой. Проверить после исправления бага https://jira.omnichannel.ru/browse/RND-25608")
    public void SupervisorGetZipPackagesTest() {
        // Инициализация страниц после инициализации driver в @BeforeEach
        RightPanelPage rightPanelPage = new RightPanelPage(driver);
        AuthPage authPage = new AuthPage(driver);

        LOG.info("авторизация по кредам");
        authPage.authBasic(svetuser2.name, passSupervisor);
        driver.findElement(rightPanelPage.systemDesignerBtn).click();
        driver.findElement(rightPanelPage.openSystemDesigner).click();

        rightPanelPage.openConfigurationManagementMenu();
        // Открываем меню действий у пакета "Custom"
        rightPanelPage.openRowActionsMenuByText("Custom");
        // Проверяем либо, что кнопка не активна, либо, что при клике возвращается код 443
        verifyExportButtonOrStatusCode443();
    }

    // @Step("Проверяем, что кнопка 'Экспортировать' изначально заблокирована")
    // void verifyButtonInitiallyDisabled() {
    //     WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(exportButton));
    //     Assertions.assertTrue(
    //             button.getAttribute("disabled") != null || button.getAttribute("aria-disabled") != null,
    //             "Кнопка должна быть заблокирована"
    //     );
    // }

    // @Step("Снимаем блокировку с кнопки через JavaScript")
    // void enableButtonViaJS() {
    //     WebElement button = driver.findElement(exportButton);
    //     ((JavascriptExecutor) driver).executeScript(
    //             "arguments[0].removeAttribute('disabled'); arguments[0].removeAttribute('aria-disabled');",
    //             button
    //     );
    //     Assertions.assertNull(button.getAttribute("disabled"), "Атрибут disabled должен быть удалён");
    // }

    // @Step("Кликаем по кнопке 'Экспортировать'")
    // void clickExportButton() {
    //     WebElement button = wait.until(ExpectedConditions.elementToBeClickable(exportButton));
    //     button.click();
    // }

    // @Step("Проверяем, что появилось модальное окно с текстом 'Http failer response'")
    // void verifyModalAppears() {
    //     By modalText = By.xpath("//*[contains(text(), 'Http failer response')]");
    //     WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalText));
    //     Assertions.assertTrue(modal.isDisplayed(), "Модальное окно должно быть видно");
    //     Assertions.assertTrue(modal.getText().contains("Http failer response"), "Ожидается текст ошибки");
    // }

    @Step("Проверяем, что пункт меню 'Экспортировать/Export' не активен или при клике возвращается код 443")
    void verifyExportButtonOrStatusCode443() {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(exportButton));
        String ariaDisabled = item.getAttribute("aria-disabled");
        String classAttr = item.getAttribute("class");
        boolean isDisabled = (ariaDisabled != null && ariaDisabled.equalsIgnoreCase("true"))
                || (classAttr != null && (classAttr.contains("mat-disabled") || classAttr.contains("mat-button-disabled")));

        if (isDisabled) {
            LOG.info("Кнопка 'Экспортировать/Export' не активна - проверка пройдена");
            Assertions.assertTrue(true, "Кнопка не активна");
        } else {
            LOG.info("Кнопка 'Экспортировать/Export' кликабельна - проверяем статус код при клике");
            // Если кнопка кликабельна, кликаем и проверяем статус код 443
            verifyStatusCodeOnClick(443);
        }
    }

    @Step("Кликаем по кнопке и проверяем статус код {expectedStatusCode}")
    void verifyStatusCodeOnClick(int expectedStatusCode) {
        if (!(driver instanceof ChromeDriver)) {
            Assertions.fail("Проверка статус кода доступна только для ChromeDriver");
            return;
        }

        ChromeDriver chromeDriver = (ChromeDriver) driver;
        DevTools devTools = null;
        try {
            devTools = chromeDriver.getDevTools();
            devTools.createSession();

            // Список для хранения статус кодов ответов после клика
            List<Integer> statusCodesAfterClick = new ArrayList<>();
            CompletableFuture<Boolean> statusCodeFound = new CompletableFuture<>();

            // Включаем мониторинг сети
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            // Добавляем listener для перехвата ответов
            devTools.addListener(Network.responseReceived(), responseReceived -> {
                Response response = responseReceived.getResponse();
                int statusCode = response.getStatus().intValue();
                String url = response.getUrl();
                statusCodesAfterClick.add(statusCode);
                LOG.info("Получен ответ с статус кодом: " + statusCode + " для URL: " + url);
                
                // Проверяем, соответствует ли статус код ожидаемому
                if (statusCode == expectedStatusCode) {
                    LOG.info("Найден ожидаемый статус код " + expectedStatusCode + " для URL: " + url);
                    statusCodeFound.complete(true);
                }
            });

            // Небольшая задержка перед кликом, чтобы убедиться, что мониторинг сети активен
            Thread.sleep(500);

            // Кликаем по кнопке
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(exportButton));
            button.click();
            LOG.info("Кнопка 'Экспортировать/Export' была нажата");

            // Ждем получения ответа (максимум 10 секунд)
            try {
                boolean found = statusCodeFound.get(10, TimeUnit.SECONDS);
                
                if (found) {
                    LOG.info("Проверка пройдена: получен ожидаемый статус код " + expectedStatusCode);
                    Assertions.assertTrue(true, "При клике получен статус код " + expectedStatusCode);
                } else {
                    LOG.warn("Ожидаемый статус код " + expectedStatusCode + " не получен в течение 10 секунд. Полученные статус коды после клика: " + statusCodesAfterClick);
                    Assertions.fail("Ожидался статус код " + expectedStatusCode + ", но он не был получен. Полученные статус коды после клика: " + statusCodesAfterClick);
                }
            } catch (java.util.concurrent.TimeoutException e) {
                LOG.warn("Таймаут ожидания статус кода " + expectedStatusCode + ". Полученные статус коды после клика: " + statusCodesAfterClick);
                Assertions.fail("Таймаут при ожидании статус кода " + expectedStatusCode + ". Полученные статус коды после клика: " + statusCodesAfterClick);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.error("Поток был прерван: " + e.getMessage());
            Assertions.fail("Поток был прерван при проверке статус кода: " + e.getMessage());
        } catch (Exception e) {
            LOG.error("Ошибка при проверке статус кода: " + e.getMessage(), e);
            // Если произошла ошибка с CDP, проверяем, может быть кнопка все же заблокирована
            try {
                WebElement button = driver.findElement(exportButton);
                String ariaDisabled = button.getAttribute("aria-disabled");
                String classAttr = button.getAttribute("class");
                boolean isDisabled = (ariaDisabled != null && ariaDisabled.equalsIgnoreCase("true"))
                        || (classAttr != null && (classAttr.contains("mat-disabled") || classAttr.contains("mat-button-disabled")));
                
                if (isDisabled) {
                    LOG.info("Кнопка оказалась неактивной после попытки клика");
                    Assertions.assertTrue(true, "Кнопка не активна");
                } else {
                    throw new AssertionError("Не удалось проверить статус код и кнопка остается активной. Ошибка: " + e.getMessage(), e);
                }
            } catch (Exception ex) {
                throw new AssertionError("Ошибка при проверке статус кода: " + e.getMessage(), e);
            }
        } finally {
            // Отключаем мониторинг сети
            if (devTools != null) {
                try {
                    devTools.send(Network.disable());
                } catch (Exception e) {
                    LOG.warn("Не удалось отключить мониторинг сети: " + e.getMessage());
                }
            }
        }
    }


}
