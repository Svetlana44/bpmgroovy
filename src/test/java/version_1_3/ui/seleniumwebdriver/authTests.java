package version_1_3.ui.seleniumwebdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class authTests {
    private final String downloaderFolder = System.getProperty("user.dir") + File.separator + "build" + File.separator + "downloadFiles";
    private final String login = "Supervisor";
    private final String pass = "BPMAdmin123!";
    private final String testStand = "mkoliismsql02.rnd.omnichannel.ru";
    private final String url = "https://" + login + ":" + pass + ":" + "@" + testStand;
    private WebDriver driver;

    @BeforeAll
    public static void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("Проверочный тест, проверяет сборку проекта")
    public void SimpleTest() {
//        Assertions.assertEquals(4, 5, "4 Не равно 5 !=");
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        Map<String, String> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloaderFolder);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
    }

    @AfterEach
    public void ternDown() {
        //     driver.quit();
        driver.close();
    }

    @Test
    public void authBasicTest() {
        driver.get(url);
    }

    @Test
    public void startPage() throws InterruptedException {
        driver.get("https://" + testStand);
        driver.findElement(By.xpath("//input[@id='loginEdit-el']")).sendKeys(login);
        driver.findElement(By.id("passwordEdit-el")).sendKeys(pass);
        driver.findElement(By.xpath("//span[@id='t-comp14-textEl']")).click();

        //      SoftAssertions softAssertions = new SoftAssertions();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        String expectedTitle = "Домашняя страница Продажи";
        WebElement title = driver.findElement(By.xpath("//div[@class='left-header-container-class']//label"));
        //   String actualTitle = title.getText();
//        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='left-header-container-class']//label")));
        Assertions.assertThat(expectedTitle.contains(title.getText())).as("Ошибка в title Домашняя страница Продажи");
//        softAssertions.assertThat(expectedTitle.contains(actualTitle));

        /*  может ошибка из-за прокрутки? */
        WebElement projects = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sidebar-item-text-15']")));
        System.out.println(projects.getText());
        org.junit.jupiter.api.Assertions.assertTrue(projects.getText().contains("Проекты"), "ошибка в Проекты");
        //      softAssertions.assertThat(projects.getText().contains("Проекты")).as("ошибка в Проекты");


        WebElement homePage = driver.findElement(By.id("sidebar-item-text-0"));
        Assertions.assertThat(homePage.getText().contains("Домашняя страница")).as("ошибка в Домашняя страница");
        //      softAssertions.assertThat(homePage.getText().contains("Домашняя страница")).as("ошибка в Домашняя страница");
        System.out.println(homePage.getText());

        WebElement itogi = driver.findElement(By.id("sidebar-item-text-1"));
        Assertions.assertThat(itogi.getText().contains("Итоги")).as("ошибка в Итоги");
        //      softAssertions.assertThat(itogi.getText().contains("Итоги")).as("ошибка в Итоги");
        System.out.println(itogi.getText());

        WebElement lenta = driver.findElement(By.id("sidebar-item-text-2"));
        Assertions.assertThat(lenta.getText().contains("Лента")).as("ошибка в Лента");
        //     softAssertions.assertThat(lenta.getText().contains("Лента")).as("ошибка в Лента");
        System.out.println(lenta.getText());

        WebElement lids = driver.findElement(By.id("sidebar-item-text-3"));
        Assertions.assertThat(lids.getText().contains("Лиды")).as("ошибка в Лиды");
        //     softAssertions.assertThat(lids.getText().contains("Лиды")).as("ошибка в Лиды");
        System.out.println(lids.getText());

        WebElement kontragents = driver.findElement(By.id("sidebar-item-text-4"));
        Assertions.assertThat(kontragents.getText().contains("Контрагенты")).as("ошибка в Контрагенты");
        //      softAssertions.assertThat(kontragents.getText().contains("Контрагенты")).as("ошибка в Контрагенты");
        System.out.println(kontragents.getText());

        WebElement contacts = driver.findElement(By.id("sidebar-item-text-5"));
        Assertions.assertThat(contacts.getText().contains("Контакты")).as("ошибка в Контакты");
        //      softAssertions.assertThat(contacts.getText().contains("Контакты")).as("ошибка в Контакты");
        System.out.println(contacts.getText());

        WebElement activities = driver.findElement(By.id("sidebar-item-text-6"));
        Assertions.assertThat(activities.getText().contains("Активности")).as("ошибка в Активности");
        //      softAssertions.assertThat(activities.getText().contains("Активности")).as("ошибка в Активности");
        System.out.println(activities.getText());

        WebElement sales = driver.findElement(By.id("sidebar-item-text-7"));
        Assertions.assertThat(sales.getText().contains("Продажи")).as("ошибка в Продажи");
        //      softAssertions.assertThat(sales.getText().contains("Продажи")).as("ошибка в Продажи");
        System.out.println(sales.getText());

        WebElement docs = driver.findElement(By.id("sidebar-item-text-8"));
        Assertions.assertThat(docs.getText().contains("Документы")).as("ошибка в Документы");
        //     softAssertions.assertThat(docs.getText().contains("Документы")).as("ошибка в Документы");
        System.out.println(docs.getText());

        WebElement orders = driver.findElement(By.id("sidebar-item-text-9"));
        Assertions.assertThat(orders.getText().contains("Заказы")).as("ошибка в Заказы");
        //     softAssertions.assertThat(orders.getText().contains("Заказы")).as("ошибка в Заказы");
        System.out.println(orders.getText());

        WebElement contracts = driver.findElement(By.id("sidebar-item-text-10"));
        Assertions.assertThat(contracts.getText().contains("Договоры")).as("ошибка в Договоры");
        //     softAssertions.assertThat(contracts.getText().contains("Договоры")).as("ошибка в Договоры");
        System.out.println(contracts.getText());

        WebElement products = driver.findElement(By.id("sidebar-item-text-11"));
        Assertions.assertThat(products.getText().contains("Продукты")).as("ошибка в Продукты");
        //    softAssertions.assertThat(products.getText().contains("Продукты")).as("ошибка в Продукты");
        System.out.println(products.getText());

        WebElement accounts = driver.findElement(By.id("sidebar-item-text-12"));
        Assertions.assertThat(accounts.getText().contains("Счета")).as("ошибка в Счета");
        //     softAssertions.assertThat(accounts.getText().contains("Счета")).as("ошибка в Счета");
        System.out.println(accounts.getText());

        WebElement knowledgeBase = driver.findElement(By.id("sidebar-item-text-13"));
        Assertions.assertThat(knowledgeBase.getText().contains("База знаний")).as("ошибка в База знаний");
        //      softAssertions.assertThat(knowledgeBase.getText().contains("База знаний")).as("ошибка в База знаний");
        System.out.println(knowledgeBase.getText());

        WebElement chats = driver.findElement(By.id("sidebar-item-text-14"));
        Assertions.assertThat(chats.getText().contains("Чаты")).as("ошибка в Чаты");
        //     softAssertions.assertThat(chats.getText().contains("Чаты")).as("ошибка в Чаты");
        System.out.println(chats.getText());

    /*    WebElement projects = driver.findElement(By.xpath("//div[@id='sidebar-item-text-15']"));
        System.out.println(projects.getText());
        org.junit.jupiter.api.Assertions.assertTrue(projects.getText().contains("Проекты"),"ошибка в Проекты");
        //      softAssertions.assertThat(projects.getText().contains("Проекты")).as("ошибка в Проекты");   */

        WebElement planning = driver.findElement(By.id("sidebar-item-text-16"));
        Assertions.assertThat(planning.getText().contains("Планирование")).as("ошибка в Планирование");
        //    softAssertions.assertThat(planning.getText().contains("Планирование")).as("ошибка в Планирование");
        System.out.println(planning.getText());

        WebElement partnerships = driver.findElement(By.id("sidebar-item-text-17"));
        //     softAssertions.assertThat(partnerships.getText().contains("Партнерства")).as("ошибка в Партнерства");
        Assertions.assertThat(partnerships.getText().contains("Партнерства")).as("ошибка в Партнерства");
        System.out.println(partnerships.getText());

        //  softAssertions.assertAll();
    }

}
