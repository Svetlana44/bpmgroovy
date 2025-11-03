package ui.seleniumwebdriver.uiRND21689GetZipPackages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.seleniumwebdriver.pages.AuthPage;
import ui.seleniumwebdriver.pages.RightPanelPage;
import ui.seleniumwebdriver.pagesTests.UIBaseTests;

public class UIrND21689GetZipPackagesTests extends UIBaseTests {
    /*  driver описан в базовом классе */
    By exportButton = By.xpath("//button[contains(@class,'mat-focus-indicator actions-menu-button-with-icon mat-menu-item')]");

    @Test
    @Step("Supervisor проверка скачивания пакета")
    @DisplayName("Проверка кликабельности кнопки 'Экспортировать' и появления сообщения 'Http failer response'")
    @Description("Тест снимает блокировку кнопки, кликает по ней и проверяет появление модального окна с ошибкой.")
    public void SupervisorGetZipPackagesTest() {
        // Инициализация страниц после инициализации driver в @BeforeEach
        RightPanelPage rightPanelPage = new RightPanelPage(driver);
        AuthPage authPage = new AuthPage(driver);

        LOG.info("авторизация по кредам");
        authPage.authBasic(svetuser2.name, passSupervisor);
        driver.findElement(rightPanelPage.systemDesignerBtn).click();
        driver.findElement(rightPanelPage.openSystemDesigner).click();

        rightPanelPage.openConfigurationManagementMenu();
        verifyButtonInitiallyDisabled();
        enableButtonViaJS();
        clickExportButton();
        verifyModalAppears();
    }

    @Step("Проверяем, что кнопка 'Экспортировать' изначально заблокирована")
    void verifyButtonInitiallyDisabled() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(exportButton));
        Assertions.assertTrue(
                button.getAttribute("disabled") != null || button.getAttribute("aria-disabled") != null,
                "Кнопка должна быть заблокирована"
        );
    }

    @Step("Снимаем блокировку с кнопки через JavaScript")
    void enableButtonViaJS() {
        WebElement button = driver.findElement(exportButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('disabled'); arguments[0].removeAttribute('aria-disabled');",
                button
        );
        Assertions.assertNull(button.getAttribute("disabled"), "Атрибут disabled должен быть удалён");
    }

    @Step("Кликаем по кнопке 'Экспортировать'")
    void clickExportButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(exportButton));
        button.click();
    }

    @Step("Проверяем, что появилось модальное окно с текстом 'Http failer response'")
    void verifyModalAppears() {
        By modalText = By.xpath("//*[contains(text(), 'Http failer response')]");
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalText));
        Assertions.assertTrue(modal.isDisplayed(), "Модальное окно должно быть видно");
        Assertions.assertTrue(modal.getText().contains("Http failer response"), "Ожидается текст ошибки");
    }


}
