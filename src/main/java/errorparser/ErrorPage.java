package errorparser;

import com.codeborne.selenide.*;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class ErrorPage {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorPage.class.getName());
    /*  шестерёнка  */
    private final SelenideElement systemDesignerMenuWrapBtn = $x("//span[@id='view-menu-button-system-designer-menuWrapEl' and @class='t-btn-menuWrap']");
    /* меню  Открыть дизайнер системы  */
    private final SelenideElement systemDesignerMenuWrapMenu = $x("//li[@data-item-marker='Открыть дизайнер системы']");
    /* Дизайнер системы -> Управление конфигурацией  //a[@data-item-marker='Управление конфигурацией']  */
    private final SelenideElement configurationDesigner = $x("//a[@data-item-marker='Управление конфигурацией']");
    /*    статус сортировка   */
    private final SelenideElement statusSort = $x("//div[@class='mat-sort-header-arrow ng-trigger ng-trigger-arrowPosition ng-tns-c292-13 ng-star-inserted']");
    /*  private final SelenideElement statusSort = $x("//div[@class='mat-sort-header-container mat-focus-indicator ng-tns-c292-13 mat-sort-header-sorted' and @role='button']");  */
    /* Найти элемент с текстом "Пример текста"  $x("//*[contains(text(), 'Статус')]");   */
    /*  SelenideElement statusSort = $x("//thead/tr/th[4]");   */
    /*   фильтр по статусу   */
    private final SelenideElement statusFiltr = $x("//span[contains(text(),'Фильтры')]");
    /* общий элемент
находит как раз только с ошибками
//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*

там где крестик
//ts-workspace-item-status-cell/div/div/mat-icon

/*  находит ячейку Название   */
    //ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*/td[2]   */

    /*   ячейка тип  (Данные/SQL сценарий)   */
//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*/td[5]

    /*   ячейка Объект (бывает пусто)   */
//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*/td[6]
    /* меню выбора фильтра "С ошибкой"  */
    private final SelenideElement errorMenu = $x("//span[contains(text(),'С ошибкой')]");
    /*    ячейка Пакет */
//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*/td[8]   */
    private final ElementsCollection packegeCell = $$x("//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*/td[8]");
    private final SelenideElement loginField = $x("//input[@id='loginEdit-el']");
    private final SelenideElement passField = $("#passwordEdit-el");
    private final SelenideElement authBtn = $x("//div[@id='loginRememberContainer']/span[@data-item-marker=\"btnLogin\"]");
    private final Properties properties = new Properties();

    public ErrorPage() {

        try (InputStream in = ErrorPage.class.getClassLoader().getResourceAsStream("errorpage.properties")) {
            properties.load(in);
            Configuration.browser = "firefox";
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();/* отключить алерты в браузере  */
            options.addPreference("dom.webnotifications.enabled", false); /* нашел для файфокса)   *//* отключить алерты в браузере  */
            options.addPreference("dom.webnotifications.enabled", false); // отключить всплывающие блокировщики
            options.addPreference("signon.rememberSignons", false); // отключить окно сохранения пароля
            Configuration.browserCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);


//            Configuration.browser = "chrome"; // Устанавливаем браузер, который будет использоваться
//            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//            ChromeOptions options = new ChromeOptions();/* отключить алерты в браузере  */
//            options.addArguments("--disable-notifications");
//            /*  Отключить всплывающие блокировщики: */
//            options.addArguments("--disable-popup-blocking");
//            /*    Отключить окно сохранения пароля: */
//            options.addArguments("--disable-save-password-bubble");
//            Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

            Configuration.browserSize = "1920x1080";
            Selenide.open(this.properties.getProperty("url"));
            this.loginField.should(Condition.enabled, Duration.ofSeconds(60));
            this.loginField.sendKeys(this.properties.getProperty("login"));
            this.passField.sendKeys(this.properties.getProperty("password"));
            this.authBtn.should(Condition.enabled, Duration.ofSeconds(30));
            this.authBtn.click();
        } catch (IOException e) {
            System.out.println("Спасите, помогите: проперти не грузятся.");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ErrorPage errorPage = new ErrorPage();
        errorPage.parseErrorAndNeedInstallDataAndSQLscripts();
    }

    public ErrorPage parseNeedInstallDataAndSQLscripts() {
        LOG.info("Создание экземпляра класса ErrorPage. ");

        this.systemDesignerMenuWrapBtn.should(Condition.enabled, Duration.ofSeconds(30));
        this.systemDesignerMenuWrapBtn.click();
        LOG.info("Нажатие на шестерёнку.");

        //    errorPage.isAlertPresent();

//        WebDriver driver = WebDriverRunner.getWebDriver();
//        /* это чат gpt советует, чтобы на алерте не падало */
//        if (driver instanceof JavascriptExecutor) {
//            ((JavascriptExecutor) driver).executeScript("if (typeof alert !== 'undefined') alert('Моя алерта');");
//        }
        //     errorPage.isAlertPresent();

        this.systemDesignerMenuWrapMenu.should(Condition.enabled, Duration.ofSeconds(30));
        this.systemDesignerMenuWrapMenu.click();
        LOG.info("Выбор второго пункта меню");

        /*  Этот код выполнит JavaScript, который прокрутит страницу вниз до самого конца. проскролить страницу до конца с использованием Selenide.  */
        //  Thread.sleep(5000); /* Подождать 5 секунд перед скроллингом страницы  */
//        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
//        LOG.info("1 раз Скролинг страницы вниз до конца.");

        this.configurationDesigner.should(Condition.enabled, Duration.ofSeconds(30));
        this.configurationDesigner.click();
        LOG.info("Нажатие на Управление конфигурацией.");

        sleep(5000);
        /* Получаем список всех открытых вкладок браузера   */
        List<String> tabs = new ArrayList<>(WebDriverRunner.getWebDriver().getWindowHandles());
        LOG.info("Получаем список всех открытых вкладок браузера. Всего вкладок: " + (tabs.size()));
        System.out.println(tabs);

        switchTo().window(tabs.get(tabs.size() - 1), Duration.ofSeconds(30));
        /* Переключаемся на вторую вкладку   */
        //      WebDriverRunner.getWebDriver().switchTo().window(tabs.get(tabs.size() - 1));
        LOG.info("Переключаемся на последнюю вкладку. При падении теста, посмотреть, сколько вкладок посчитано.");

        //     Selenide.sleep(30000);

        /* Находим элемент и кликаем на него   */
        this.statusSort.should(Condition.enabled, Duration.ofSeconds(30));
        this.statusSort.click();
        LOG.info("Нажатие на сортировку по статусу.");
        this.statusSort.click();
        LOG.info("Ещё раз Нажатие на сортировку по статусу.");

        ElementsCollection pakcagies = this.packegeCell;
        Set<String> pakcagiesName = new HashSet<>();
        this.packegeCell.forEach(p -> pakcagiesName.add(p.getText()));
        System.out.println(pakcagiesName);
        //     errorPage.PackegeCell.forEach(e -> System.out.println(e.getText()));

        /* общий элемент находит как раз только с ошибками*/
        ElementsCollection rowsErr = $$x("//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*");
        this.parseErrString(rowsErr).forEach((k, v) -> {
            System.out.println(k + "===============================");
            v.forEach(System.out::println);
        });
        return this;
    }

    public ErrorPage parseErrorAndNeedInstallDataAndSQLscripts() {
        LOG.info("Создание экземпляра класса ErrorPage. ");

        this.systemDesignerMenuWrapBtn.should(Condition.enabled, Duration.ofSeconds(30));
        this.systemDesignerMenuWrapBtn.click();
        LOG.info("Нажатие на шестерёнку.");

//        WebDriver driver = WebDriverRunner.getWebDriver();
//        /* это чат gpt советует, чтобы на алерте не падало */
//        if (driver instanceof JavascriptExecutor) {
//            ((JavascriptExecutor) driver).executeScript("if (typeof alert !== 'undefined') alert('Моя алерта');");
//        }

        this.systemDesignerMenuWrapMenu.should(Condition.enabled, Duration.ofSeconds(30));
        this.systemDesignerMenuWrapMenu.click();
        LOG.info("Выбор второго пункта меню");

        this.configurationDesigner.should(Condition.enabled, Duration.ofSeconds(30));
        this.configurationDesigner.click();
        LOG.info("Нажатие на Управление конфигурацией.");

        Selenide.sleep(5000);
        /* Получаем список всех открытых вкладок браузера   */
        List<String> tabs = new ArrayList<>(WebDriverRunner.getWebDriver().getWindowHandles());
        LOG.info("Получаем список всех открытых вкладок браузера. Всего вкладок: " + (tabs.size()));
        System.out.println(tabs);

        Selenide.switchTo().window(tabs.get(tabs.size() - 1), Duration.ofSeconds(30));
        /* Переключаемся на вторую вкладку   */
        //      WebDriverRunner.getWebDriver().switchTo().window(tabs.get(tabs.size() - 1));
        LOG.info("Переключаемся на последнюю вкладку. При падении теста, посмотреть, сколько вкладок посчитано.");

        //     Selenide.sleep(30000);

        /* Находим элемент и кликаем на него   */
        this.statusFiltr.should(Condition.enabled, Duration.ofSeconds(30));
        this.statusFiltr.click();
        LOG.info("Выбор фильтра по статусу.");

        this.errorMenu.should(Condition.enabled, Duration.ofSeconds(30));
        this.errorMenu.click();
        LOG.info("Выбор меню фильтрации \"С ошибкой\".");

        ElementsCollection pakcagies = this.packegeCell;
        Set<String> pakcagiesName = new HashSet<>();
        this.packegeCell.forEach(p -> pakcagiesName.add(p.getText()));
        System.out.println(pakcagiesName);
        //     errorPage.PackegeCell.forEach(e -> System.out.println(e.getText()));

        /* общий элемент
находит как раз только с ошибками*/
        ElementsCollection rowsErr = $$x("//ts-workspace-item-status-cell/div/div/mat-icon/parent::div/parent::div/ancestor::td/parent::*");
        this.parseErrString(rowsErr).forEach((k, v) -> {
            System.out.println(k + "===============================");
            v.forEach(System.out::println);
        });
        return this;
    }

    public Map<String, List<String>> parseErrString(ElementsCollection rowsErr) {
        Map<String, List<String>> packageErrors = new HashMap<>();
        for (SelenideElement selEl : rowsErr) {
            String[] strs = selEl.getText().split("\\s+|\\n");
            if (packageErrors.containsKey(strs[strs.length - 1])) {
                List<String> strList = packageErrors.get(strs[strs.length - 1]);
                strList.add(Arrays.toString(strs));
                packageErrors.put(strs[strs.length - 1], strList);
            } else {
                List<String> strList = new ArrayList<>();
                strList.add(Arrays.toString(strs));
                packageErrors.put(strs[strs.length - 1], strList);
            }
        }

        return packageErrors;
    }

    public boolean isAlertPresent() {
        try {
            Selenide.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }
}
