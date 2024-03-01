package version_1_3.ui.selenide.screenshots;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import version_1_3.ui.selenide.pages.AuthPageSelenide;
import version_1_3.ui.selenide.pages.StartPageSelenide;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/* можно и ч/з selenium и selenide */
public class ScreenshotsTests {

    private static File outputDir;
    private String testName;
    private AuthPageSelenide authPageSelenide;

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
        authPageSelenide = new AuthPageSelenide();
        authPageSelenide.authBasic();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWindow();
        Configuration.browserCapabilities = new SelenideConfig().browserCapabilities();
    }

    @Test
    public void web1080pTest() {
        //  Configuration.browserSize = "1920x1080";
        /*учитываем анимацию и задержку*/
        // By ignoredPictureBlock = By.xpath("//div[@class='left_part']"); /*игнор объекта*/
        // assertFullScreen(ignoredPictureBlock);
        new StartPageSelenide().leftPanelContentListLoad();
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
                .shootingStrategy(ShootingStrategies.viewportPasting(30000))
                .coordsProvider(new WebDriverCoordsProvider())
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
