package ui.seleniumwebdriver.pagesTests;

import api.rND21689GetZipPackages.models.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import listener.CustomTpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilies.PropertiesReader;
import utilies.UserFactory;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Level;

public class BaseTests {
    protected static final Random random = new Random();
    protected static final PropertiesReader propertiesReader = PropertiesReader.getInstance();
    /*Логгер для использования в статических методах (@BeforeAll, @AfterAll)*/
    protected static final Logger staticLOG = LoggerFactory.getLogger(BaseTests.class);
    /*Плюсы:
                В логах будет имя конкретного потомка (UserService.class).
                Работает с любыми наследниками.
    ❗ Минус:
                Логгер не static, поэтому создаётся при каждом экземпляре (обычно не критично для тестов).*/
    protected static final Logger LOG = LoggerFactory.getLogger(BaseTests.class);
    public static User supervisor1;
    public static User svetuser2;
    protected static String loginSupervisor;
    protected static String passSupervisor;
    protected static String url;
    //  private final String testStand = "mkoliismsql02.rnd.omnichannel.ru";
//    private final String url = "https://" + testStand;
    /*   for  Page Object */
    /* ChromDriver нужно складывать в папку resouces, чтобы проект работал не только на локальном компе */
    protected WebDriver driver;

    /* этот метод загрузки нужного webDriver
    можно хром, можно firefox, adge,IE...
     нужно прописать,   чтобы руками не скачивать нужный webDriver,  ставим эту библиотеку
    testImplementation 'io.github.bonigarcia:webdrivermanager:5.6.3'*/
    @BeforeAll
    //   public static void downloadDriver() {
    @Step("Задание кастомного логера" +
            "Загрузка stands.properties и System.setProperty(\"webdriver.chrome.driver\"" +
            "Cоздание пользователей системы")
    public static void oneTimeSetUp() {
        // Подавляем предупреждения Selenium о CDP версиях
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        java.util.logging.Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        
        RestAssured.filters(
                new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , CustomTpl.customLogFilter().withCustomTemplates());
        //Кастомный логер Aluure. статичный - см третий в списке.

     /*   RestAssured.filters(
                new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , new AllureRestAssured());*/
   /*     WebDriverManager.edgedriver().setup();
        WebDriverManager.iedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.safaridriver().setup();   */
        /*  //      WebDriverManager.chromedriver().setup();
        /* теперь это не нужно, библиотека webdrivermanager сама закачает нужный драйвер
         * webdrivermanager см в дочерних тестах */
        /*    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe"); */
        // Автоматическое определение версии Chrome и скачивание совместимого драйвера
        WebDriverManager.chromedriver().setup();

        /*  Использование singleton для чтения properties
         справку см. в пакете  aPatterns/PropertiesReader.md */
        loginSupervisor = propertiesReader.getProperty("login");
        passSupervisor = propertiesReader.getProperty("password");
         //.replaceAll("^\"|\"$", "")  - удаляет кавычки в начале и конце строки
        url = propertiesReader.getProperty("url");
        staticLOG.info("Загрузка проперти.");
        staticLOG.info("Загружен URL из properties: " + url);
        
        // Проверяем, что URL загружен корректно
        if (url == null || url.isEmpty() || url.equals("url")) {
            staticLOG.error("ОШИБКА: URL не загружен из properties! Значение: '" + url + "'");
            throw new IllegalStateException("URL не может быть пустым или равным 'url'. Проверьте файл configs/stands.properties");
        }

        /* Использование TestUserFactory для создания стандартных тестовых пользователей
         Преимущества: централизация, использование values из properties, улучшенная читаемость */
        supervisor1 = UserFactory.createSupervisor();
        svetuser2 = UserFactory.createSvetUser();
        LOG.info("Созданы тестовые пользователи: " + supervisor1.name + "и " + svetuser2.toString());

    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Принимаем невалидные/самоподписанные сертификаты, чтобы не показывалась страница Privacy error
        options.setAcceptInsecureCerts(true);
        // Отключаем CDP для устранения предупреждений о несовместимости версий
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "enable-logging"});
        // Отключаем CDP полностью для устранения предупреждений о несовместимости версий Chrome 142
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage"); // Избегаем проблем с /dev/shm
        options.addArguments("--no-sandbox"); // Для стабильности в некоторых окружениях
        options.addArguments("--disable-gpu"); // Отключаем GPU для стабильности
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        // Отключаем логирование CDP чтобы уменьшить предупреждения
        System.setProperty("webdriver.chrome.silentOutput", "true");
        // Подавляем предупреждения о CDP версиях
        System.setProperty("webdriver.chrome.verboseLogging", "false");
        //для allure логов
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", loggingPreferences);
        //FullHD
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
        // Проверяем, что URL инициализирован
        if (url == null || url.isEmpty() || url.equals("url")) {
            LOG.error("URL не инициализирован! Проверьте файл configs/stands.properties");
            throw new IllegalStateException("URL не может быть пустым или равным 'url'. Проверьте настройки в properties файле.");
        }
        LOG.info("Открытие URL: " + url);
        driver.get(url);

        // Автопропуск interstitial страницы Chrome ("Your connection is not private") при редких кейсах
        try {
            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            if ((title != null && (
                    title.toLowerCase().contains("your connection is not private") ||
                    title.toLowerCase().contains("privacy error") ||
                    title.toLowerCase().contains("подключение не является частным")
            )) || (currentUrl != null && (
                    currentUrl.contains("interstitial") ||
                    currentUrl.contains("ssl") ||
                    currentUrl.startsWith("chrome-error://")
            ))) {
                driver.findElement(By.tagName("body")).sendKeys("thisisunsafe");
            }
        } catch (Exception ignored) {
        }
    }

    @AfterEach
    public void ternDown() {
        driver.close();
    }
}
