package ui.seleniumwebdriver.screenshots;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ui.seleniumwebdriver.pages.AuthPage;
import ui.seleniumwebdriver.pagesTests.BaseTests;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

/* можно и ч/з selenium и selenide */
public class ScreenshotsTests extends BaseTests {

    private static File outputDir;
    private String testName;
    private AuthPage authPageSelenium;

    @BeforeAll
    public static void initFolder() {
        outputDir = new File("build/screenshots");
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    }

    /*для TestNG
   @BeforeEach
        public void initTestName(ITestContext context) {
          //  вся инфа о тесте, берём имя запущенного теста
        testName = context.getName();
    }  */
    @BeforeEach
    public void initTestName(TestInfo info) {
        /*вся инфа о тесте, берём имя запущенного теста */
        testName = info.getTestMethod().get().getName();
    }

    @BeforeEach
    @DisplayName("Авторизация")
    public void authBasic() {
        //     Configuration.timeout = 60_000; /*   по умолчанию 30 сек  , м.б. мало*/
        /*  driver описан в базовом классе */
        AuthPage authPage = new AuthPage(driver);
        authPage.authBasic();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void web1080pTest() {
        /*учитываем анимацию и задержку*/
        // By ignoredPictureBlock = By.xpath("//div[@class='left_part']"); /*игнор объекта*/
        // assertFullScreen(ignoredPictureBlock);
        WebElement homePage = driver.findElement(By.xpath("//div[@class='left_part']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        assertFullScreen();
    }

    @Disabled
    @Test
    public void mobileIphoneXrTest() {
        // System.setProperty("chromeoptions.mobileEmulation", "deviceName=iPhone XR");
//        Map<String, String> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceName", "iPhone XR");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//        Configuration.browserCapabilities = chromeOptions;

        Configuration.browserSize = "414x896";
        //       Selenide.open("https://test.ru");
        assertFullScreen();
    }

    /* перегруженный метод (см.ниже) с аргументом игнорируемого объекта */
    @SneakyThrows
    private void assertFullScreen(By ignoredElement) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(6000))
                .addIgnoredElement(ignoredElement)
                .takeScreenshot(WebDriverRunner.getWebDriver());
        File actualScreen = new File(outputDir.getAbsolutePath() + "/" + testName + ".png");
        ImageIO.write(screenshot.getImage(), "png", actualScreen);

        File expectedScreen = new File(String.format("src/test/resources/references/%s.png", testName));
        if (!expectedScreen.exists()) {
            throw new RuntimeException("No reference image, download it from build/screenshots");
        }
        assertImages(actualScreen, expectedScreen);
    }

    @SneakyThrows
    private void assertFullScreen() {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(3000))
                //            .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(WebDriverRunner.getWebDriver());
        File actualScreen = new File(outputDir.getAbsolutePath() + "/" + testName + ".png");
        ImageIO.write(screenshot.getImage(), "png", actualScreen);

        File expectedScreen = new File(String.format("src/test/resources/references/%s.png", testName));
        if (!expectedScreen.exists()) {
            throw new RuntimeException("No reference image, download it from build/screenshots");
        }
        assertImages(actualScreen, expectedScreen);
    }

    private void assertImages(File actual, File expected) throws IOException {
        ImageDiff differ = new ImageDiffer()
                .makeDiff(ImageIO.read(actual), ImageIO.read(expected))
                .withDiffSizeTrigger(10); /*допустимое различие в пикселях*/
        /*для алюра*/
        if (differ.hasDiff()) {
            BufferedImage diffImage = differ.getMarkedImage();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(diffImage, "png", bos);
            byte[] image = bos.toByteArray();
            Allure.getLifecycle().addAttachment("diff", "image/png", "png", image);
            Assertions.assertFalse(differ.hasDiff());
        }
    }
}
