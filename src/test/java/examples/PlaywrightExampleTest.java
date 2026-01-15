package examples;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Пример использования Playwright для UI тестирования
 * 
 * Playwright - современная альтернатива Selenium с лучшей производительностью
 * и встроенными возможностями (автоматические ожидания, скриншоты, видео).
 * 
 * Преимущества Playwright:
 * - Автоматическое ожидание элементов
 * - Встроенная поддержка скриншотов и видео
 * - Быстрее Selenium
 * - Поддержка нескольких браузеров (Chromium, Firefox, WebKit)
 * - Автоматическая генерация кода
 */
public class PlaywrightExampleTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        // Инициализируем Playwright
        playwright = Playwright.create();
        
        // Запускаем браузер (можно выбрать: chromium, firefox, webkit)
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)  // true для headless режима
                .setSlowMo(1000));   // Замедление для визуализации
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContext() {
        // Создаем новый контекст для каждого теста (изоляция)
        context = browser.newContext();
        
        // Настраиваем опции контекста
        context.setViewportSize(1920, 1080);
        
        // Создаем новую страницу
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    /**
     * Пример 1: Базовый тест - открытие страницы и проверка заголовка
     */
    @Test
    void testBasicNavigation() {
        // Открываем страницу
        page.navigate("https://example.com");
        
        // Проверяем заголовок страницы
        assertEquals("Example Domain", page.title());
        
        // Проверяем URL
        assertTrue(page.url().contains("example.com"));
    }

    /**
     * Пример 2: Работа с элементами (клик, ввод текста)
     */
    @Test
    void testInteractions() {
        page.navigate("https://example.com");
        
        // Находим элемент по тексту и кликаем
        page.getByText("More information...").click();
        
        // Вводим текст в поле (если бы было поле ввода)
        // page.getByPlaceholder("Search").fill("test query");
        
        // Проверяем, что элемент виден
        assertTrue(page.getByText("More information...").isVisible());
    }

    /**
     * Пример 3: Работа с формами
     */
    @Test
    void testFormFilling() {
        // Пример для формы (замените на реальный URL)
        // page.navigate("https://example.com/form");
        
        // Заполнение полей формы
        // page.getByLabel("Name").fill("John Doe");
        // page.getByLabel("Email").fill("john@example.com");
        // page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        
        // Проверка результата
        // assertTrue(page.getByText("Success").isVisible());
    }

    /**
     * Пример 4: Ожидания (waits) - автоматические в Playwright
     */
    @Test
    void testAutomaticWaits() {
        page.navigate("https://example.com");
        
        // Playwright автоматически ждет, пока элемент появится
        // Не нужно явно указывать WebDriverWait
        page.getByText("More information...").click();
        
        // Можно использовать явные ожидания для кастомных условий
        page.waitForSelector("h1", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    /**
     * Пример 5: Скриншоты
     */
    @Test
    void testScreenshots() {
        page.navigate("https://example.com");
        
        // Полный скриншот страницы
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(java.nio.file.Paths.get("screenshot-full.png"))
                .setFullPage(true));
        
        // Скриншот элемента
        // page.getByText("Example Domain").screenshot(new Locator.ScreenshotOptions()
        //         .setPath(java.nio.file.Paths.get("screenshot-element.png")));
    }

    /**
     * Пример 6: Работа с несколькими вкладками
     */
    @Test
    void testMultipleTabs() {
        page.navigate("https://example.com");
        
        // Открываем новую вкладку
        Page newPage = context.newPage();
        newPage.navigate("https://example.org");
        
        assertEquals("Example Domain", page.title());
        assertEquals("Example Domain", newPage.title());
        
        newPage.close();
    }

    /**
     * Пример 7: Перехват сетевых запросов
     */
    @Test
    void testNetworkInterception() {
        // Перехватываем запросы
        page.route("**/*", route -> {
            System.out.println("Request: " + route.request().url());
            route.resume();
        });
        
        page.navigate("https://example.com");
    }

    /**
     * Пример 8: Работа с cookies
     */
    @Test
    void testCookies() {
        page.navigate("https://example.com");
        
        // Устанавливаем cookie
        context.addCookies(java.util.Arrays.asList(
                new BrowserContext.AddCookieOptions("test", "value")
                        .setDomain("example.com")
                        .setPath("/")
        ));
        
        // Получаем все cookies
        java.util.List<Cookie> cookies = context.cookies();
        assertFalse(cookies.isEmpty());
    }

    /**
     * Пример 9: JavaScript выполнение
     */
    @Test
    void testJavaScriptExecution() {
        page.navigate("https://example.com");
        
        // Выполняем JavaScript на странице
        Object result = page.evaluate("() => document.title");
        assertEquals("Example Domain", result);
    }

    /**
     * Пример 10: Проверка текста и элементов
     */
    @Test
    void testTextAssertions() {
        page.navigate("https://example.com");
        
        // Проверка текста
        assertTrue(page.getByText("Example Domain").isVisible());
        
        // Проверка количества элементов
        int count = page.locator("h1").count();
        assertTrue(count > 0);
        
        // Проверка атрибутов
        String href = page.getByText("More information...").getAttribute("href");
        assertNotNull(href);
    }
}
