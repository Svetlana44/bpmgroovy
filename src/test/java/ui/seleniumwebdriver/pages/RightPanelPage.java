package ui.seleniumwebdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class RightPanelPage extends BasePage {
    public final By configurationManagementLink =
            By.xpath("//a[contains(@data-item-marker, 'Управление конфигурацией')]");
    //  public By systemDesignerBtn = By.id("view-menu-button-system-designer-menuEl");//шестерёнка
    public By systemDesignerBtn = By.xpath("//*[starts-with(@id, 'view') and contains(@id, 'button-system-designer-wrapperEl')]");
    //public By openSystemDesigner = By.xpath("//li[@class='menu-item'][2]");//такое не работает, если у юзера нет прав, тогда в меню не два пункта
    public By openSystemDesigner = By.xpath("//li[@class='menu-item' and contains(text(),'Открыть дизайнер системы')]");


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
    }
}
