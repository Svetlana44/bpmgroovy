package ui.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Data
public class StartPageSelenide {
    ElementsCollection leftPanelContentList = $$x("//div/ul[@class='ts-sidebar-list ts-box-sizing x-unselectable']/li");
    SelenideElement title = $x("//div[@class='left-header-container-class']//label");
    ElementsCollection headerRightImageContainer = $$x("//div[@id='header-right-image-container']");
    ElementsCollection headerCommandLineContainer = $$x("//div[@id='header-command-line-container']");

    public StartPageSelenide leftPanelContentListLoad() {
        //   SelenideElement homePage = leftPanelContentList.find(text("Домашняя страница"));
        SelenideElement homePage = $(byText("Домашняя страница"));
        homePage.should(Condition.enabled, Duration.ofSeconds(60));
        return this;
    }

//
//    SelenideElement homePage = $(By.id("sidebar-item-text-0"));
//    SelenideElement itogi = $(By.id("sidebar-item-text-1"));
//    SelenideElement lenta = $(By.id("sidebar-item-text-2"));
//    /*лиды*/
//    SelenideElement leads = $(By.id("sidebar-item-text-3"));
//    /*Контрагенты*/
//    SelenideElement kontragents = $(By.id("sidebar-item-text-4"));
//    /*контакты*/
//    SelenideElement contacts = $(By.id("sidebar-item-text-5"));
//    /*Активности*/
//    SelenideElement activities = $(By.id("sidebar-item-text-6"));
//    /*Продажи*/
//    SelenideElement sales = $(By.id("sidebar-item-text-7"));
//    /*Документы*/
//    SelenideElement docs = $(By.id("sidebar-item-text-8"));
//    /*Заказы*/
//    SelenideElement orders = $(By.id("sidebar-item-text-9"));
//    /*Договоры*/
//    SelenideElement contracts = $(By.id("sidebar-item-text-10"));
//    /*Продукты*/
//    SelenideElement products = $(By.id("sidebar-item-text-11"));
//    /*Счета*/
//    SelenideElement accounts = $(By.id("sidebar-item-text-12"));
//    /*База знаний*/
//    SelenideElement knowledgeBase = $(By.id("sidebar-item-text-13"));
//    /*Чаты*/
//    SelenideElement chats = $(By.id("sidebar-item-text-14"));
//    /*Проекты*/
//    SelenideElement projects = $(By.xpath("//div[@id='sidebar-item-text-15']"));
//    /*Планирование*/
//    SelenideElement planning = $(By.id("sidebar-item-text-16"));
//    /*Партнерства*/
//    SelenideElement partnerships = $(By.id("sidebar-item-text-17"));
}
