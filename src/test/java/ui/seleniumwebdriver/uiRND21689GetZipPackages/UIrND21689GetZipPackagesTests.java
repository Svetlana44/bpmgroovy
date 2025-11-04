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
        // Проверяем только, что пункт недоступен (без правки DOM)
        verifyExportMenuItemDisabled();
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

    @Step("Проверяем, что пункт меню 'Экспортировать/Export' не активен")
    void verifyExportMenuItemDisabled() {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(exportButton));
        String ariaDisabled = item.getAttribute("aria-disabled");
        String classAttr = item.getAttribute("class");
        boolean isDisabled = (ariaDisabled != null && ariaDisabled.equalsIgnoreCase("true"))
                || (classAttr != null && (classAttr.contains("mat-disabled") || classAttr.contains("mat-button-disabled")));
        Assertions.assertTrue(isDisabled, "Пункт меню 'Экспортировать/Export' должен быть неактивен");
    }


}
