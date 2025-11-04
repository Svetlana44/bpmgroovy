package ui.seleniumwebdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class RightPanelPage extends BasePage {
    public final By configurationManagementLink =
            By.xpath("//a[(contains(@data-item-marker,'Управление конфигурацией') or contains(@data-item-marker,'Configuration management')) or contains(normalize-space(.),'Управление конфигурацией') or contains(normalize-space(.),'Configuration management')]");
    //  public By systemDesignerBtn = By.id("view-menu-button-system-designer-menuEl");//шестерёнка
    public By systemDesignerBtn = By.xpath("//*[starts-with(@id, 'view') and contains(@id, 'button-system-designer-wrapperEl')]");
    //public By openSystemDesigner = By.xpath("//li[@class='menu-item'][2]");//такое не работает, если у юзера нет прав, тогда в меню не два пункта
    public By openSystemDesigner = By.xpath("//li[@class='menu-item' and (contains(normalize-space(.),'Открыть дизайнер системы') or contains(normalize-space(.),'Open the system designer'))]");


    public RightPanelPage(WebDriver driver) {
        super(driver);
    }

    public void openConfigurationManagementMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(configurationManagementLink)).click();

        // ждём появления нового окна/вкладки
        wait.until(d -> d.getWindowHandles().size() > 1);

        // переключаемся на последнее (последний хэндл)
        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        String lastHandle = handles.get(handles.size() - 1);
        driver.switchTo().window(lastHandle);

        // ждём, пока инициализируется Angular Material UI на новой вкладке
        // как сигнал готовности ждём появления любых mat-кнопок
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'mat-focus-indicator')]")));
    }

    // Триггер меню действий (три точки) внутри строки пакета
    public By actionsMenuTrigger = By.cssSelector("div.mat-menu-trigger.actions-menu-trigger-wrapper");
    // Панель открытого mat-menu (по появлению проверяем, что меню открылось)
    public By matMenuPanel = By.cssSelector(".cdk-overlay-pane .mat-menu-panel");

    /**
     * Открыть меню действий (три точки) у первой видимой строки (если много одинаковых пунктов)
     */
    public void openFirstRowActionsMenu() {
        List<WebElement> triggers = wait.until(d -> d.findElements(actionsMenuTrigger));
        for (WebElement trigger : triggers) {
            if (trigger.isDisplayed() && trigger.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(trigger)).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(matMenuPanel));
                return;
            }
        }
        throw new IllegalStateException("Не найден кликабельный триггер меню действий");
    }

    /**
     * Открыть меню действий (три точки) для строки, содержащей указанный текст (например, имя пакета)
     */
    public void openRowActionsMenuByText(String rowText) {
        String xpath = "//*[contains(@class,'package-item') or contains(@class,'package-item-right') or contains(@class,'package-item-left')]" +
                "[.//*[contains(normalize-space(.), '" + rowText + "')]]//div[contains(@class,'actions-menu-trigger-wrapper')]";
        WebElement trigger = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        trigger.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(matMenuPanel));
    }
}
