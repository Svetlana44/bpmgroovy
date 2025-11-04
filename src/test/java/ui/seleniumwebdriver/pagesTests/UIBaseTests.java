package ui.seleniumwebdriver.pagesTests;

import api.rND21689GetZipPackages.models.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import listener.CustomTpl;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilies.UserService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import lombok.Builder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class UIBaseTests {

    protected static final Random random = new Random();
    protected static final Properties properties = new Properties();
    protected static final Logger LOG = LoggerFactory.getLogger(BaseTests.class);
    public static User supervisor1;
    public static User svetuser2;
    protected static String loginSupervisor;
    protected static String passSupervisor;
    protected static String url;
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeAll
    void oneTimeSetUp() {
        System.out.println("-> Инициализация WebDriverManager...");

        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                // Принимаем невалидные/самоподписанные сертификаты
                options.setAcceptInsecureCerts(true);
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                // Отключаем CDP и уменьшаем предупреждения о несовместимости версий
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "enable-logging"});
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-dev-shm-usage"); // Избегаем проблем с /dev/shm
                options.addArguments("--no-sandbox"); // Для стабильности в некоторых окружениях
                options.addArguments("--disable-gpu"); // Отключаем GPU для стабильности
                options.addArguments("--start-maximized");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--allow-insecure-localhost");
                // Отключаем логирование CDP чтобы уменьшить предупреждения
                System.setProperty("webdriver.chrome.silentOutput", "true");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1920, 1080));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));

        }

        //     wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("--> WebDriver запущен: " + ((RemoteWebDriver) driver).getCapabilities().getBrowserName());

        LOG.info("Задание кастомного логера" +
                "Загрузка stands.properties" +
                "Cоздание пользователей системы");
        RestAssured.filters(
                new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , CustomTpl.customLogFilter().withCustomTemplates());
        //Кастомный логер Aluure. статичный - см третий в списке.

     /*   RestAssured.filters(
                new RequestLoggingFilter()
                , new ResponseLoggingFilter()
                , new AllureRestAssured());
       WebDriverManager.edgedriver().setup();
        WebDriverManager.iedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.safaridriver().setup();   */

        try (InputStream in = UserService.class.getClassLoader().getResourceAsStream("configs/stands.properties")) {
            properties.load(in);
            loginSupervisor = properties.getProperty("login");
            passSupervisor = properties.getProperty("password");
            url = properties.getProperty("url");
            //.replaceAll("^\"|\"$", "")
        } catch (IOException e) {
            System.out.println("Спасите, помогите, проперти не грузятся.");
            e.printStackTrace();
        }
        LOG.info("Загрузка проперти.");

        supervisor1 = User.builder()
                .isCanManageSolution(true)
                .name("Supervisor")
                .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
        svetuser2 = User.builder()
                .isCanManageSolution(false)
                .name("SVETuser")
                .isCanViewConfiguration(true)
                .pass("BPMAdmin123!")
                .build();
        LOG.info(svetuser2.toString());
    }

    @BeforeEach
    void setUp() {
        // Инициализируем wait для явных ожиданий (увеличено для тяжёлых страниц System Designer)
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        // Открываем страницу перед каждым тестом
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
    void tearDownEach(TestInfo testInfo) {
        // Если тест упал, делаем скриншот и лог в Allure
        if (testInfo.getTags().contains("failed")) {
            captureFailureArtifacts(testInfo.getDisplayName());
        }
    }

    @AfterEach
    public void ternDown() {
        driver.close();
    }

    @AfterAll
    void oneTimeTearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("---> WebDriver закрыт.");
        }
    }
/*
    @Step("Открываем страницу: {url}")
    protected void openUrl(String url) {
        driver.get(url);
    }
 */

    @Attachment(value = "Скриншот при ошибке", type = "image/png")
    protected byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "HTML страницы", type = "text/html")
    protected String attachPageSource() {
        return driver.getPageSource();
    }

    protected void captureFailureArtifacts(String testName) {
        System.out.println("---> Сохраняем артефакты при падении теста: " + testName);
        try {
            attachScreenshot();
            Allure.addAttachment("Page Source",
                    "text/html",
                    Objects.requireNonNull(driver.getPageSource()), ".html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Делает скриншот и сохраняет локально (на случай отладки вне Allure)
     */
    protected void saveScreenshotLocal(String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void attachTextToAllure(String name, String content) {
        Allure.addAttachment(name, new ByteArrayInputStream(content.getBytes()));
    }
}
